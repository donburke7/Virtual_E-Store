package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class ShoppingCartTest {
    @Test
    public void testAddProduct() {
        // Setup
        Product expected = new Product("Green Beans", 0, 500, 2.00);
        ShoppingCart cart = new ShoppingCart(null);

        // Invoke
        cart.addProduct(expected);
        Product actual = cart.getItems().get(0);

        // Analysis
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveProduct() {
        // Setup
        Product product = new Product("Green Beans", 0, 500, 2.00);
        ShoppingCart actual = new ShoppingCart(null);
        ShoppingCart expected = new ShoppingCart(null);
        actual.addProduct(product);

        // Invoke
        actual.removeProduct(1);

        // Analysis
        assertEquals(expected, actual);
        
    }
}
