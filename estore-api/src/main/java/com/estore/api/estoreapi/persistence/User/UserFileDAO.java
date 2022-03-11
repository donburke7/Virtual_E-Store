package com.estore.api.estoreapi.persistence.User;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.Users.Customer;
import com.estore.api.estoreapi.model.Users.User;
import com.estore.api.estoreapi.persistence.JsonUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.UserDataHandler;

/**
 * The file that manipulates the saved data that correlates specifically to the making and deletions of 
 * an entire user.
 * 
 * {@Literal @Component} is a spring annotation that indicates that this class is to be instantiated
 * and inserted into any class that needs it upon starting.
 * 
 * @author Alen Van
 */
@Component
public class UserFileDAO implements UserDAO {

    Map<String, User> users; // local data storage of the inventory
    // to object
    private String filename; // the file to read and write to
    private JsonUtilities jsonUtilities; //provides json conversions

    /**
     * Creates an Inventory File Data Access Object
     * 
     * @param filename     filename to read from and write to
     * @param objectMapper provides conversion between JSON files to object
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public UserFileDAO(@Value("${users.file}") String filename, JsonUtilities jsonUtilities) throws IOException {
        this.filename = filename;
        this.jsonUtilities = jsonUtilities;
        load(); // load the products from the file
    }

    /**
     * Loads all {@linkplain User users} that were in the file that was passed in
     * Deserialize all JSON products and saves it into a local storage for easy
     * access
     * 
     * @return a boolean indicating if the operation was successful
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        users = new TreeMap<>();

        // deserialize the JSON file into a list of users
        try {
            String inventoryJSONString = Files.readString(Path.of(filename));

            if (inventoryJSONString.length() > 0) {
                User[] userPassedIn = jsonUtilities.DeserializeObject(filename, User[].class);

                // add every user that was just recently deserialized to the local storage
                for (User i : userPassedIn) {
                    users.put(i.toString(), i);
                }
            }

        } catch (EOFException e) {
        }
        // set the nextId to be the next available id
        return true;

    }
    
    /**
     * saves the list of {@linkplain User users}
     *
     * @return a true indicating the save was successful
     *         An exception if an error occured
     */
    private boolean save() throws IOException {
        User[] userList = getUsers();

        jsonUtilities.SerializeObject(filename, userList);
        return true;

    }

    /**
     * creates and returns an array of all the {@linkplain User users} listed in the
     * system
     * 
     * @return an array of {@link User users}
     */
    public User[] getUsers() {
        //init
        ArrayList<User> userList = new ArrayList<>();
        
        //get all users saved in a local list
        for (User user : users.values()) {
            userList.add(user);
        }

        //transport the list into an array and return it
        User[] result = new User[userList.size()];
        userList.toArray(result);
        return result;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User addUser(String username) throws IOException {
        synchronized (users) {
            User newUser = new User(username);
            users.put(username, newUser);
            save();
            return newUser;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(String username) throws IOException {
        synchronized (users) {
            User currUser;
            if (users.containsKey(username)) {
                currUser = users.get(username);
            } else {
                currUser = addUser(username);
            }

            return currUser;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteUser(String username) throws IOException {
        synchronized (users) {
            User currUser = users.remove(username);
            save();
            return currUser != null;
        }
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean saveUsers() throws IOException {
        return this.save();
    }
    

}
