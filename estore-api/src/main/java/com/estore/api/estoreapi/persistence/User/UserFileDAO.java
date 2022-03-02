package com.estore.api.estoreapi.persistence.User;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.Users.Customer;
import com.estore.api.estoreapi.model.Users.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;

public class UserFileDAO {

    Map<String, User> users; // local data storage of the inventory
    private ObjectMapper objectMapper; // provides conversion between JSON files to object
    private String filename; // the file to read and write to



    /**
     * Creates an Inventory File Data Access Object
     * 
     * @param filename     filename to read from and write to
     * @param objectMapper provides conversion between JSON files to object
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
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
                User[] userPassedIn = objectMapper.readValue(inventoryJSONString, User[].class);

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
     * Adds a product to the user's shopping cart if the user is not the admin
     * 
     * @param user the user to save a product to
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
     * Removes a product from a user's shopping cart if the user is not the admin
     * 
     * @param user the user to request a product removal
     * @param id the id to search for to find the associated product to remove
     * @return the product that was removed
     */
    public Product removeProduct(User user, int id) {
        if(user.toString() != "admin") {
            Customer currUser = (Customer) user;
            return currUser.removeProduct(id);
        } else {
            return null;
        } 
    }

}
