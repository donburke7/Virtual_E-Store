package com.estore.api.estoreapi.persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InventoryFileDAO implements InventoryDAO {

    Map<Integer, Product> inventory; // local data storage of the inventory
    private ObjectMapper objectMapper; // provides conversion between JSON files to object
    private static int nextId; // the next id to use for a new product
    private String filename; // the file to read and write to

    /**
     * Creates an Inventory File Data Access Object
     * 
     * @param filename     filename to read from and write to
     * @param objectMapper provides conversion between JSON files to object
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public InventoryFileDAO(@Value("${inventory.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load(); // load the heroes from the file
    }

    /**
     * Loads all products that were in the file that was passed in
     * Deserialize all JSON products and saves it into a local storage for easy
     * access
     * 
     * @return a boolean indicating if the operation was successful
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        inventory = new TreeMap<>();
        nextId = 0;

        // deserialize the JSON file into a list of products
        try {
            String inventoryJSONString = Files.readString(Path.of(filename));

            if (inventoryJSONString.length() > 0) {
                Product[] products = objectMapper.readValue(inventoryJSONString, Product[].class);

                // add every product that was just recently deserialized to the local storage
                // inventory
                for (Product product : products) {
                    inventory.put(product.getID(), product);
                    if (product.getID() > nextId) {
                        nextId = product.getID();
                    }
                }
                ++nextId;
            }

        } catch (EOFException e) {
        }
        // set the nextId to be the next available id
        return true;

    }

    /**
     * Gets and sets the next id for a {@link Product}
     * 
     * @return an id
     */
    private synchronized int nextID() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Given a string, search and find all {@link Product} that matches the string
     * 
     * @param searchParameter a string to use to search for {@link Product}
     * @return an array of all {@link Product} that matches the string, if the
     *         string is null, return the entire inventory
     */
    private Product[] getInventory(String searchParameter) {
        ArrayList<Product> products = new ArrayList<>();

        // add all items that matches the string that was passed in
        for (Product currProduct : inventory.values()) {
            if (searchParameter == null || currProduct.getName().contains(searchParameter)) {
                products.add(currProduct);
            }
        }

        Product[] results = new Product[products.size()];
        products.toArray(results);
        return results;

    }

    /**
     * An overloaded method that gives a default parameter to the getInvetory()
     * method
     * uses null as the parameter for the method.
     * 
     * @return An array of products that has every {@link Product} in the inventory
     */
    private Product[] getInventory() {
        return getInventory(null);
    }

    /**
     * Saves the inventory of {@link Product} from the map
     * as an array of JSON objects
     * 
     * @return true if the inventory was written correctly
     * 
     * @throws IOException when the file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Product[] products = getInventory();

        // serializes the entire inventory into a file
        // whose path was initially passed into this class
        objectMapper.writeValue(new File(filename), products);
        return true;

    }

    @Override
    public Product getProduct(int id) throws IOException {
        synchronized (inventory) {
            return inventory.get(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product[] getProducts() throws IOException {
        synchronized (inventory) {
            return getInventory();
        }
    }

    @Override
    public Product[] searchProducts(String searchParam) throws IOException {
        synchronized (inventory) {
            return getInventory(searchParam);
        }
    }

    @Override
    public Product createProduct(Product product) throws IOException {
        synchronized (inventory) {
            Product newProduct = new Product(product.getName(), nextID(), product.getAmount());
            inventory.put(newProduct.getID(), newProduct);
            save();
            return newProduct;
        }
    }

    @Override
    public Product updateProduct(Product product) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteProduct(int id) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

}
