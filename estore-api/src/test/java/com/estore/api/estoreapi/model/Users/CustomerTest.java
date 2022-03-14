package com.estore.api.estoreapi.model.Users;

<<<<<<< HEAD
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

import java.util.Map;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
=======
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import com.estore.api.estoreapi.model.Product;

>>>>>>> 34f261d2183430a951ec96cacf5e5afe6d524877
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

<<<<<<< HEAD
@Tag("Model-Tier")
public class CustomerTest {
    ShoppingCart testShoppingCart;
    ShoppingCart testEmptyShoppingCart;
    
    @BeforeEach
    public void setup(){
        
        
        TreeMap<Integer, Product> map = new TreeMap();
        Product expected = new Product("Green Beans", 0, 1, 1.00);
        map.put(0, expected);

        testEmptyShoppingCart = new ShoppingCart(null);
        testShoppingCart = new ShoppingCart(map);
    }
    @Test
    public void testCustomerEmpty() {
        
        // Custumer-to-be-tested's username
        String username = "Joe";

        // Customer created with cunstructor that only requires a username
        Customer testCustomer = new Customer(username);

        // Asserts that both the username is "Joe" and shopping cart is an empty shopping cart object
        assertEquals("Joe", testCustomer.getUsername());

        for (int i : testCustomer.cart.getItems().keySet()) {
            assertEquals(testEmptyShoppingCart.getItems().get(i), testCustomer.getCart().getItems().get(i));
        }
        
    }

    @Test
    public void testCustomer() {

        // Custumer-to-be-tested's username
        String username = "Joe";
        
        // Customer created with cunstructor that only requires a username
        Customer testCustomer = new Customer(username, testShoppingCart);
        
        // Asserts that both the username is "Joe" and shopping cart is an empty shopping cart object
        assertEquals("Joe", testCustomer.getUsername());
        for (int i : testCustomer.cart.getItems().keySet()) {
            assertEquals(testShoppingCart.getItems().get(i), testCustomer.getCart().getItems().get(i));
        }    
    }

=======
/**
 * unit tests for the customer class
 * 
 * @author Isaac Post
 */
@Tag("Model-Tier")
public class CustomerTest {
>>>>>>> 34f261d2183430a951ec96cacf5e5afe6d524877

    @Test
    public void testAddProduct() {
        // Setup
        Product addedProduct = new Product("Green Beans", 0, 500, 2.00);
        Customer testCustomer = new Customer("Bart");

        // Invoke
        testCustomer.addProduct(addedProduct);
        Product[] actual = testCustomer.getCart();
        Product[] expected = {addedProduct};

        // Analysis
        assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void testRemoveProduct() {
        // Setup
        int id = 0;
        Product nexpected = new Product("Green Beans", id, 500, 2.00);
        Customer testCustomer = new Customer("Bart");

        // Invoke
        testCustomer.removeProduct(id);
        ShoppingCart cart = testCustomer.getCart();
        Product actual = cart.removeProduct(id); // Returns the product that was removed, null if not
        Product expected = nexpected.removeProduct(id);

        // Analysis
        assertEquals(expected, actual);
    }

}
