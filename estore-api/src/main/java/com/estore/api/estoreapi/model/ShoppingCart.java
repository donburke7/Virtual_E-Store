package com.estore.api.estoreapi.model;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The class that holds the manipulation of the customer's shopping cart
 * 
 * @author Alen Van
 */
public class ShoppingCart {

    @JsonProperty("items")
    Map<Integer, Product> items;

    @JsonCreator
    public ShoppingCart(@JsonProperty("items") Map<Integer, Product> items) {
        // items can be null indicating this should be a new empty cart
        if (items != null) {
            this.items = items;
        }

        else {
            this.items = new TreeMap<Integer, Product>();
        }
    }


    /**
     * Adds a {@linkplain Product product} to the shopping cart of this user
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
     * @return the {@link Product product} that was removed
     */
    public Product removeProduct(int id) {
        return items.remove(id);
    }

    public void clearCart() {
        this.items.clear();

    }

    public Map<Integer, Product> getItems() {
        return this.items;
    }

}
