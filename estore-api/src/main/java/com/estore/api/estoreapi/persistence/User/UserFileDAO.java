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

@Component
public class UserFileDAO {

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
     * Loads all users that were in the file that was passed in
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
     * saves the list of users
     *
     * @return a true indicating the save was successful
     *          An exception if an error occured
     */
    private boolean save() throws IOException {
        User[] userList = getUsers();

        jsonUtilities.SerializeObject(filename, userList);
        return true;

    }

    /**
     * Adds a product to the user's shopping cart if the user is not the admin
     * 
     * @param user    the user to save a product to
     * @param product the product to add
     * @return the product that was added, null if otherwise
     */
    public Product addProduct(User user, Product product) {
        if (user.toString() != "admin") {
            Customer currUser = (Customer) user;
            return currUser.addProduct(product);
        } else {
            return null;
        }
    }

    /**
     * temporary method to get the list of users
     * 
     * @return an array of users
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
     * Removes a product from a user's shopping cart if the user is not the admin
     * 
     * @param user the user to request a product removal
     * @param id   the id to search for to find the associated product to remove
     * @return the product that was removed
     */
    public Product removeProduct(User user, int id) {
        if (user.toString() != "admin") {
            Customer currUser = (Customer) user;
            return currUser.removeProduct(id);
        } else {
            return null;
        }
    }

}
