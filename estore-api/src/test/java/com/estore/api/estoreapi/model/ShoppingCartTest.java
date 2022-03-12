package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-Tier")
public class ShoppingCartTest {

    ShoppingCart testShoppingCart;
    ShoppingCart emptyShoppingCart;

    @BeforeEach
    public void setup() {
        TreeMap<Integer, Product> map = new TreeMap<>();
        Product expected = new Product("Green Beans", 0, 1, 1.00);
        map.put(0, expected);

        testShoppingCart = new ShoppingCart(map);
        emptyShoppingCart = new ShoppingCart(null);
    }

    @Test
    public void testAddProduct() {
        // Setup
        Product addedProduct = new Product("Green Beans", 0, 500, 2.00);
        ShoppingCart cart = new ShoppingCart(null);

        // Invoke
        cart.addProduct(addedProduct);
        Product[] actual = cart.getItems();
        Product[] expected = { addedProduct };

        // Analysis
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testRemoveProduct() {
        TreeMap<Integer, Product> map = new TreeMap<>();

        Product expected = new Product("Green Beans", 0, 1, 1.00);

        map.put(0, expected);

        ShoppingCart cart = new ShoppingCart(map);

        assertEquals(expected, cart.removeProduct(0));

    }

    @Test
    public void testClearCart() {
        testShoppingCart.clearCart();

        assertEquals(0, testShoppingCart.getItems().length);

    }

}
