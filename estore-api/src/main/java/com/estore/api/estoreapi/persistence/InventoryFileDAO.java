package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public InventoryFileDAO(@Value("${heroes.file}") String filename, ObjectMapper objectMapper) throws IOException {
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
        Product[] products = objectMapper.readValue(new File(filename), Product[].class);

        // add every product that was just recently deserialized to the local storage
        // inventory
        for (Product product : products) {
            inventory.put(product.getID(), product);
            if (product.getID() > nextId) {
                nextId = product.getID();
            }
        }

        // set the nextId to be the next available id
        ++nextId;
        return true;

    }

    @Override
    public Product getProduct(int id) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product[] getProducts() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product[] searchProducts(String searchParam) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product createProduct(Product product) throws IOException {
        // TODO Auto-generated method stub
        return null;
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
