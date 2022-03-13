package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.Arrays;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.Users.Customer;
import com.estore.api.estoreapi.persistence.User.ShoppingCartFileDAO;
import com.estore.api.estoreapi.persistence.User.UserFileDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the shopping cart file DAO
 * 
 * @author Alen Van
 */
@Tag("Persistence-tier")
public class ShoppingCartFileDAOTest {

    ShoppingCartFileDAO shoppingCartFileDAO;
    UserFileDAO mockUserFileDAO;
    Customer customer;

    /**
     * setup before each test
     * Create a new shopping cart file DAO with a mock user file DAO passed in
     * Create a new customer with an empty shopping cart
     */
    @BeforeEach
    public void setup() {
        mockUserFileDAO = mock(UserFileDAO.class);
        shoppingCartFileDAO = new ShoppingCartFileDAO(mockUserFileDAO);
        customer = new Customer("username");
    }
    
    @Test
    public void testaddProduct() throws IOException {

        //setup
        Product testProduct = new Product("Green Bean", 0, 1, 1.00);
        
        //invoke
        shoppingCartFileDAO.addProduct(customer, testProduct);

        //setup analysis
        Product[] expected = { testProduct };
        Product[] actual = customer.getCart();

        //analyze
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testdeleteProduct() throws IOException {

        //setup
        Product testProduct = new Product("Green Bean", 0, 1, 1.00);
        customer.addProduct(testProduct);

        //invoke
        shoppingCartFileDAO.deleteProduct(customer, 0);

        //setup analysis
        Product[] expected = { };
        Product[] actual = customer.getCart();

        //analyze
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void testgetShoppingCart() throws IOException {

        //setup
        Product testProduct = new Product("Green Bean", 0, 1, 1.00);
        Product testProductTwo = new Product("Red Beans", 1, 1, 2.50);
        customer.addProduct(testProduct);
        customer.addProduct(testProductTwo);

        //invoke
        Product[] actual = shoppingCartFileDAO.getShoppingCart(customer);

        //setup analysis
        Product[] expected = { testProduct, testProductTwo };

        //analyze
        assertTrue(Arrays.equals(expected, actual));

    }

    @Test
    public void testclearShoppingCart() throws IOException {

        //setup
        Product testProduct = new Product("Green Bean", 0, 1, 1.00);
        Product testProductTwo = new Product("Red Beans", 1, 1, 2.50);
        customer.addProduct(testProduct);
        customer.addProduct(testProductTwo);

        //invoke
        shoppingCartFileDAO.clearShoppingCart(customer);

        //setup analysis
        Product[] expected = {};
        Product[] actual = customer.getCart();

        //analyze
        assertTrue(Arrays.equals(expected, actual));

    }
    
}
