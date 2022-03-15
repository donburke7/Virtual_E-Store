package com.estore.api.estoreapi.model.Users;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Model-Tier")
public class CustomerTest {
    ShoppingCart mockShoppingCart;
    
    @BeforeEach
    public void setup() {
        mockShoppingCart = mock(ShoppingCart.class);
    }
    
    @Test
    public void testCustomerEmpty() {
        
        // Custumer-to-be-tested's username
        String username = "Joe";

        // Customer created with cunstructor that only requires a username
        Customer testCustomer = new Customer(username);

        // Asserts that both the username is "Joe" and shopping cart is an empty shopping cart object
        assertEquals("Joe", testCustomer.getUsername());
        assertEquals(0, testCustomer.getCart().length);
    }

    @Test
    public void testCustomer() {

        // Custumer-to-be-tested's username
        String username = "Joe";
        Product[] expected = {
            new Product("Green Beans", 0, 1, 1.00),
            new Product("Pinto Beans", 1, 5, 2.00)
        };
        
        // Customer created with cunstructor that only requires a username
        Customer testCustomer = new Customer(username, mockShoppingCart);
        when(mockShoppingCart.getItems()).thenReturn(expected);
        
        // Asserts that both the username is "Joe" and shopping cart is an empty shopping cart object
        assertEquals("Joe", testCustomer.getUsername());
        assertArrayEquals(expected, testCustomer.getCart());
    }


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
        Product expected = new Product("Green Beans", id, 500, 2.00);

        TreeMap<Integer, Product> cartMap = new TreeMap<>(){{
            put(id, expected);
            }
        };

        Customer testCustomer = new Customer("Bart", new ShoppingCart(cartMap));
        when(mockShoppingCart.removeProduct(id)).thenReturn(expected);

        // Invoke
        Product actual = testCustomer.removeProduct(id);

        // Analysis
        assertEquals(expected, actual);
    }

}
