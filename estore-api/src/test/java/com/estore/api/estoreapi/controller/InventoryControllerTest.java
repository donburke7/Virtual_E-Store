package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.estore.api.estoreapi.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Product Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class InventoryControllerTest {
    private InventoryController inventoryController;
    private InventoryDAO mockInventoryDAO;

    /**
     * Before each test, create a new inventoryController object and inject
     * a mock Product DAO
     */
    @BeforeEach
    public void setupinventoryController() {
        mockInventoryDAO = mock(InventoryDAO.class);
        inventoryController = new InventoryController(mockInventoryDAO);
    }

    @Test
    public void testgetProduct() throws IOException { // getProduct may throw IOException
        // Setup
        Product product = new Product("Green Bean", 99, 1);
        // When the same id is passed in, our mock Product DAO will return the Product
        // object
        when(mockInventoryDAO.getProduct(product.getID())).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = inventoryController.getProduct(product.getID());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testgetProductNotFound() throws Exception { // createProduct may throw IOException
        // Setup
        int ProductId = 99;
        // When the same id is passed in, our mock Product DAO will return null,
        // simulating
        // no Product found
        when(mockInventoryDAO.getProduct(ProductId)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = inventoryController.getProduct(ProductId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testgetProductHandleException() throws Exception { // createProduct may throw IOException
        // Setup
        int id = 99;
        // When getProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).getProduct(id);

        // Invoke
        ResponseEntity<Product> response = inventoryController.getProduct(id);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /*****************************************************************
     * The following tests will fail until all inventoryController methods
     * are implemented.
     ****************************************************************/

    @Test
    public void testcreateProduct() throws IOException { // createProduct may throw IOException
        // Setup
        Product product = new Product("Blue Beans", 99, 1);
        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockInventoryDAO.createProduct(product)).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testcreateProductFailed() throws IOException { // createProduct may throw IOException
        // Setup
        Product product = new Product("Yello Beans", 99, 1);
        // when createProduct is called, return false simulating failed
        // creation and save
        when(mockInventoryDAO.createProduct(product)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testcreateProductHandleException() throws IOException { // createProduct may throw IOException
        // Setup
        Product product = new Product("Ice Beans", 99, 1);

        // When createProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).createProduct(product);

        // Invoke
        ResponseEntity<Product> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testupdateProduct() throws IOException { // updateProduct may throw IOException
        // Setup
        Product product = new Product("Blue Beans", 99, 1);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockInventoryDAO.updateProduct(product)).thenReturn(product);
        ResponseEntity<Product> response = inventoryController.updateProduct(product);
        product.setAmount(4);

        // Invoke
        response = inventoryController.updateProduct(product);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testupdateProductFailed() throws IOException { // updateProduct may throw IOException
        // Setup
        Product Product = new Product("Green Bean", 99, 1);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockInventoryDAO.updateProduct(Product)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = inventoryController.updateProduct(Product);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testupdateProductHandleException() throws IOException { // updateProduct may throw IOException
        // Setup
        Product Product = new Product("Green Bean", 99, 1);
        // When updateProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).updateProduct(Product);

        // Invoke
        ResponseEntity<Product> response = inventoryController.updateProduct(Product);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testgetProducts() throws IOException { // getProducts may throw IOException
        // Setup
        Product[] Productes = new Product[2];
        Productes[0] = new Product("Yellow Beans", 99, 1);
        Productes[1] = new Product("Space Beans", 100, 1);
        ;
        // When getProducts is called return the Productes created above
        when(mockInventoryDAO.getProducts()).thenReturn(Productes);

        // Invoke
        ResponseEntity<Product[]> response = inventoryController.getProducts();

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Productes, response.getBody());
    }

    @Test
    public void testgetProductsHandleException() throws IOException { // getProducts may throw IOException
        // Setup
        // When getProducts is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).getProducts();

        // Invoke
        ResponseEntity<Product[]> response = inventoryController.getProducts();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testsearchProducts() throws IOException { // searchProducts may throw IOException
        // Setup
        String searchString = "Gr";
        Product[] Productes = new Product[2];
        Productes[0] = new Product("Green Bean", 99, 1);
        Productes[1] = new Product("Great Beans", 100, 1);
        // When searchProducts is called with the search string, return the two
        /// Productes above
        when(mockInventoryDAO.searchProducts(searchString)).thenReturn(Productes);

        // Invoke
        ResponseEntity<Product[]> response = inventoryController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Productes, response.getBody());
    }

    @Test
    public void testsearchProductsHandleException() throws IOException { // searchProducts may throw IOException
        // Setup
        String searchString = "an";
        // When createProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).searchProducts(searchString);

        // Invoke
        ResponseEntity<Product[]> response = inventoryController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testdeleteProduct() throws IOException { // deleteProduct may throw IOException
        // Setup
        int ProductId = 99;
        // when deleteProduct is called return true, simulating successful deletion
        when(mockInventoryDAO.deleteProduct(ProductId)).thenReturn(true);

        // Invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(ProductId);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testdeleteProductNotFound() throws IOException { // deleteProduct may throw IOException
        // Setup
        int ProductId = 99;
        // when deleteProduct is called return false, simulating failed deletion
        when(mockInventoryDAO.deleteProduct(ProductId)).thenReturn(false);

        // Invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(ProductId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testdeleteProductHandleException() throws IOException { // deleteProduct may throw IOException
        // Setup
        int ProductId = 99;
        // When deleteProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).deleteProduct(ProductId);

        // Invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(ProductId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
