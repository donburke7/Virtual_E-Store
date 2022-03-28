package com.estore.api.estoreapi.persistence.Inventory;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.persistence.JsonUtilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implements the functionality for JSON file-based peristance for Inventory
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of
 * this
 * class and injects the instance into other classes as needed
 * 
 * @author team-4-jadin
 */
@Component
public class InventoryFileDAO implements InventoryDAO {

    Map<Integer, Product> inventory; // local data storage of the inventory
    private static int nextId; // the next id to use for a new product
    private String filename; // the file to read and write to
    private JsonUtilities jsonUtilities;

    /**
     * Creates an Inventory File Data Access Object
     * 
     * @param filename     filename to read from and write to
     * @param objectMapper provides conversion between JSON files to object
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public InventoryFileDAO(@Value("${inventory.file}") String filename, JsonUtilities jsonUtilities) throws IOException {
        this.filename = filename;
        this.jsonUtilities = jsonUtilities;
        load(); // load the products from the file
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
                Product[] products = jsonUtilities.DeserializeObject(inventoryJSONString, Product[].class);
                // add every product that was just recently deserialized to the local storage
                // inventory
                if (products.length > 0) {
                    for (Product product : products) {
                        inventory.put(product.getID(), product);
                        if (product.getID() > nextId) {
                            nextId = product.getID();
                        }
                    }
                    ++nextId;
                }
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
        jsonUtilities.SerializeObject(filename, products);
        return true;

    }
    
    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Product[] searchProducts(String searchParam) throws IOException {
        synchronized (inventory) {
            return getInventory(searchParam);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product createProduct(Product product) throws IOException {
        synchronized (inventory) {
            Product newProduct = new Product(product.getName(), nextID(), product.getAmount(), 
                                             product.getPrice(), product.getRatings(), product.getAvgRating());
            inventory.put(newProduct.getID(), newProduct);
            save();
            return newProduct;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product updateProduct(Product product) throws IOException {
        synchronized (inventory) {
            if (inventory.containsKey(product.getID()) == false)
                return null; // product does not exist

            product.updateAvgRating();

            inventory.put(product.getID(), product);
            save(); // may throw an IOException
            return product;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProduct(int id) throws IOException {
        synchronized (inventory) {
            Product result = inventory.remove(id);
            save();

            // return true if the result was found
            return result != null;
        }
    }
    
    public Product createClone(int id, int amount) {
        if (amount == 0) {
            return null;
        } else {

            Product result = new Product(inventory.get(id), amount);
            return result;
        }
        
    }

}
