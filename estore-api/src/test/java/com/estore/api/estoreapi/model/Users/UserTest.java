package com.estore.api.estoreapi.model.Users;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.estore.api.estoreapi.model.Product;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the user class
 */
@Tag("Model-Tier")
public class UserTest {

    @Test
    public void testequalsTrue() {
        //setup
        User test1 = new User("Bob");
        Customer test2 = new Customer("Bob");

        //invoke and analyze, 
        //should return true because the usernames are the same and 
        //they are of the same parent/child heirachy
        assertTrue(test1.equals(test2));
    }

    @Test
    public void testequalsFalse() {
        //setup
        User test1 = new User("Bob");
        Customer test2 = new Customer("Billy");

        //invoke and analyze, should return false
        //not the same persone, different usernames
        assertFalse(test1.equals(test2));
    }

    @Test
    public void testequalsFalseWrongObject() {
        //setup
        User test1 = new User("Bob");
        Product product1 = new Product("Green Bean", 0, 1, 1.00);

        //invoke and analyze, should return false
        //two different objects
        assertFalse(test1.equals(product1));
    }

    @Test
    public void testequalsFalseNull() {
        //setup
        User test1 = new User("Bob");

        //invoke and analyze, should return false
        //null is not of type User
        assertFalse(test1.equals(null));
    }

}
