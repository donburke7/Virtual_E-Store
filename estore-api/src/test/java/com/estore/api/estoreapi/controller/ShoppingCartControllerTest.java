package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.Users.Customer;
import com.estore.api.estoreapi.persistence.User.ShoppingCartDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Product Controller class
 * 
 * @author Team jadin
 */
@Tag("Controller-tier")
public class ShoppingCartControllerTest {
    private ShoppingCartController cartController;
    private ShoppingCartDAO mockCartDAO;

    /**
     * Before each test, create a new inventoryController object and inject
     * a mock Product DAO
     */
    @BeforeEach
    public void setupShoppingCartController() {
        mockCartDAO = mock(ShoppingCartDAO.class);
        cartController = new ShoppingCartController(mockCartDAO);
    }

    @Test
    public void testgetCart() throws IOException { // getProduct may throw IOException
        // Setup
        Product product = new Product("Green Bean", 99, 1, 1.0);
        ShoppingCart cart = new ShoppingCart(null);
        cart.addProduct(product);
        Customer customer = new Customer("John Smith", cart);

        // When the same id is passed in, our mock Product DAO will return the Product
        // object
        // when(mockCartDAO.getShoppingCart(customer.getUsername(),customer.getCart())).thenReturn(product);
        when(mockCartDAO.getShoppingCart(customer)).thenReturn(customer);

        // Invoke
        ResponseEntity<ShoppingCart> response = cartController.getShoppingCart(customer.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testgetCartNotFound() throws Exception { // createProduct may throw IOException
        // Setup
        String customerName = "John Smith";
        // When the same id is passed in, our mock Product DAO will return null,
        // simulating
        // no Product found
        when(mockCartDAO.getShoppingCart(customerName)).thenReturn(null);

        // Invoke
        ResponseEntity<ShoppingCart> response = cartController.getShoppingCart(customerName);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testgetShoppingCartHandleException() throws Exception { // createProduct may throw IOException
        // Setup
        String customerName = "John Smith";
        // When getProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).getShoppingCart(customerName);

        // Invoke
        ResponseEntity<ShoppingCart> response = cartController.getShoppingCart(customerName);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testdeleteProduct() throws IOException { // deleteProduct may throw IOException
        // Setup
        String customerName = "John Smith";
        int ProductId = 99;
        // when deleteProduct is called return true, simulating successful deletion
        when(mockCartDAO.deleteProduct(customerName, ProductId)).thenReturn(true);

        // Invoke
        ResponseEntity<ShoppingCart> response = cartController.deleteProduct(customerName, ProductId);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testdeleteProductNotFound() throws IOException { // deleteProduct may throw IOException
        // Setup
        String customerName = "John Smith";
        int ProductId = 99;
        // when deleteProduct is called return false, simulating failed deletion
        when(mockCartDAO.deleteProduct(customerName, ProductId)).thenReturn(false);

        // Invoke
        ResponseEntity<ShoppingCart> response = cartController.deleteProduct(customerName, ProductId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testdeleteProductHandleException() throws IOException { // deleteProduct may throw IOException
        // Setup
        String customerName = "John Smith";
        int ProductId = 99;
        // When deleteProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).deleteProduct(customerName, ProductId);

        // Invoke
        ResponseEntity<Product> response = cartController.deleteProduct(customerName, ProductId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testclearShoppingCart() throws IOException { // deleteProduct may throw IOException
        // Setup
        String customerName = "John Smith";
        int ProductId = 99;
        // when deleteProduct is called return true, simulating successful deletion
        when(mockCartDAO.clearShoppingCart(customerName)).thenReturn(true);

        // Invoke
        ResponseEntity<ShoppingCart> response = cartController.clearShoppingCart(customerName);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testclearShoppingCartNotFound() throws IOException { // deleteProduct may throw IOException
        // Setup
        String customerName = "John Smith";
        int ProductId = 99;
        // when deleteProduct is called return false, simulating failed deletion
        when(mockCartDAO.clearShoppingCart(customerName)).thenReturn(false);

        // Invoke
        ResponseEntity<ShoppingCart> response = cartController.clearShoppingCart(customerName);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testclearShoppingCartHandleException() throws IOException { // deleteProduct may throw IOException
        // Setup
        String customerName = "John Smith";
        int ProductId = 99;
        // When deleteProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).clearShoppingCart(customerName);

        // Invoke
        ResponseEntity<ShoppingCart> response = cartController.clearShoppingCart(customerName);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testaddProduct() throws IOException { // updateProduct may throw IOException
        // Setup
        Product product = new Product("Green Bean", 99, 1, 1.0);
        ShoppingCart cart = new ShoppingCart(null);
        // cart.addProduct(product);
        Customer customer = new Customer("John Smith", cart);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockCartDAO.addProduct(customer, product)).thenReturn(product);
        ResponseEntity<Product> response = cartController.addProduct(customer, product);
        Product newProduct = new Product("Black Bean", 100, 2, 1.0);
        cart.addProduct(newProduct);

        // Invoke
        response = cartController.addProduct(customer, newProduct);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testaddProductFailed() throws IOException { // updateProduct may throw IOException
        // Setup
        Product product = new Product("Green Bean", 99, 1, 1.0);
        ShoppingCart cart = new ShoppingCart(null);
        Customer customer = new Customer("John Smith", cart);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockCartDAO.addProduct(customer, product)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = cartController.addProduct(customer, product);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testaddProductHandleException() throws IOException { // updateProduct may throw IOException
        // Setup
        Product product = new Product("Green Bean", 99, 1, 1.0);
        ShoppingCart cart = new ShoppingCart(null);
        Customer customer = new Customer("John Smith", cart);
        // When updateProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).addProduct(customer, product);

        // Invoke
        ResponseEntity<Product> response = cartController.addProduct(customer, product);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}