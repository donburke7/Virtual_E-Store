package com.estore.api.estoreapi.model;

import java.util.ArrayList;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The class that holds the manipulation of the customer's shopping cart
 * 
 * @author Alen Van
 */
public class ShoppingCart {

    @JsonProperty("items")
    private TreeMap<Integer, Product> items;


    /**
     * The json constructor that creates a shopping cart instance
     * This constructor is used whenever a deserialization takes place
     * 
     * @param items a map of {@linkplain Product products}
     */
    public ShoppingCart(@JsonProperty("items") TreeMap<Integer, Product> items) {
        // items can be null indicating this should be a new empty cart
        if (items != null) {
            this.items = items;
        } else {
            this.items = new TreeMap<>();
        }
    }


    /**
     * Adds a {@linkplain Product product} to the shopping cart
     * 
     * @param product the {@link Product product} to add
     * @return the {@link Product product} that was added
     */
    public Product addProduct(Product product) {
        items.put(product.getID(), product);
        return product;
    }

    /**
     * Removes a {@linkplain Product product} from the shopping cart
     * 
     * @param id the id of the {@link Product product} to remove
     * @return the {@link Product product} that was removed, null if no product was removed
     */
    public Product removeProduct(int id) {
        return items.remove(id);
    }

    /**
     * clears the shopping cart
     * 
     * @return true indicating that the cart was cleared
     */
    public boolean clearCart() {
        this.items.clear();
        return true;
    }

    /**
     * transforms the map of {@linkplain Product products} into an array of {@link Product products} and returns it
     * 
     * @return an array of products that represents the items that are in the cart
     */
    @JsonIgnore
    public Product[] getItems() {
        ArrayList<Product> products = new ArrayList<>();

        for (Product i : items.values()) {
            products.add(i);
        }

        Product[] results = new Product[products.size()];
        products.toArray(results);

        return results;

    }

}
