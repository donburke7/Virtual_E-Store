package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;

/**
 * Defines the inventory persistence
 * 
 * @author Alen Van
 */
public interface InventoryDAO {

    /**
     * Search for the {@link Product product} that correpsonds with the id and
     * return it
     * 
     * @param id the id of the {@link Product product} to search for
     * 
     * @return the {@link Product product} that was found, null if no
     *         {@link Product product} was found
     * 
     * @throws IOException if an issue with underlying storage occurs
     */
    Product getProduct(int id) throws IOException;

    Product finProducts(String text) throws IOException;
    /**
     * Gets a list of all the {@link Product products} in the inventory
     * 
     * @return an array of {@link Product products} representing the entire
     *         inventory
     * 
     * @throws IOException if an issue with underlying storage occurs
     */
    Product[] getProducts() throws IOException;

    /**
     * Search and return a {@link Product product} that has a name that contains the
     * inputted string
     * 
     * @param searchParam a string to search for
     * 
     * @return an array of {@link Product products} that matches the inputted
     *         string, null if no matching {@link Product product} was found
     * 
     * @throws IOException if an issue with underlying storage occurs
     */
    Product[] searchProducts(String searchParam) throws IOException;

    /**
     * Creates and adds a new {@link Product product} to the inventory
     * 
     * @param product the {@link Product product} to create and add
     * 
     * @return the newly created {@link Product product}, otherwise null
     * 
     * @throws IOException if an issue with underlying storage occurs
     */
    Product createProduct(Product product) throws IOException;

    /**
     * Updates and saves a {@link Product product}
     * 
     * @param product the {@link Product product} to update
     * 
     * @return the newly updated {@link Product product}, null if no
     *         {@link Product product} was found
     * 
     * @throws IOException if an issue with underlying storage occurs
     */
    Product updateProduct(Product product) throws IOException;

    /**
     * Deletes a {@link Product product} that corresponds with the given id
     * 
     * @param id the id of the {@link Product product} to delete
     * 
     * @return true if the {@link Product product} was deleted successfully,
     *         otherwise false
     * 
     * @throws IOException if an issue with underlying storage occurs
     */
    Boolean deleteProduct(int id) throws IOException;

}
