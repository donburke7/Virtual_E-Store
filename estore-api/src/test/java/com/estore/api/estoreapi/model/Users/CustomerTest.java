package com.estore.api.estoreapi.model.Users;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.Users.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("Model-Tier")
public class CustomerTest {
    
    @Test
    public void testCustomerEmpty() {
        
        // Custumer-to-be-tested's username
        String username = "Joe";

        // Customer created with cunstructor that only requires a username
        Customer testCustomer = new Customer(username);

        // Asserts that both the username is "Joe" and shopping cart is an empty shopping cart object
        assertEquals("Joe", testCustomer.getUsername());
        assertArrayEquals(testEmptyShoppingCart, Customer.getCart());
    }

    @Test
    public void testCustomer() {

        // Custumer-to-be-tested's username
        String username = "Joe";
        
        // Customer created with cunstructor that only requires a username
        Customer testCustomer = new Customer(username, testShoppingCart);
        
        // Asserts that both the username is "Joe" and shopping cart is an empty shopping cart object
        assertEquals("Joe", testCustomer.getUsername());
        assertArrayEquals(testShoppingCart, Customer.getCart());
    }
}
