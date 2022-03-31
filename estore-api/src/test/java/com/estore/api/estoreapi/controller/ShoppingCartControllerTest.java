package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
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
    private Customer mockUser;
    private Product mockProduct;

    /**
     * Before each test, create a new inventoryController object and inject
     * a mock Product DAO
     */
    @BeforeEach
    public void setupinventoryController() {
        mockProduct = mock(Product.class);
        mockCartDAO = mock(ShoppingCartDAO.class);
        cartController = new ShoppingCartController(mockCartDAO);
        mockUser = mock(Customer.class);
        when(mockUser.getUsername()).thenReturn("mock");
        when(mockProduct.getID()).thenReturn(1);
        when(mockProduct.getAmount()).thenReturn(5);
    }

    @Test
    public void testGetCartSuccess() throws IOException {
        Product[] test = { mockProduct };

        when(mockCartDAO.getShoppingCart(mockUser.getUsername())).thenReturn(test);

        ResponseEntity<Product[]> response = cartController.getCart(mockUser.getUsername());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(test, response.getBody());
    }

    @Test
    public void testGetCartFail() throws IOException {
        when(mockCartDAO.getShoppingCart(mockUser.getUsername())).thenReturn(null);

        ResponseEntity<Product[]> response = cartController.getCart(mockUser.getUsername());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetCartError() throws Exception {
        when(mockCartDAO.getShoppingCart(mockUser.getUsername())).thenThrow(new IOException());

        ResponseEntity<Product[]> response = cartController.getCart(mockUser.getUsername());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteProductSuccess() throws IOException {
        when(mockCartDAO.deleteProduct(mockUser.getUsername(), 1)).thenReturn(true);

        ResponseEntity<Product> result = cartController.deleteProduct(mockUser.getUsername(), 1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testDeleteProductFail() throws IOException {
        when(mockCartDAO.deleteProduct(mockUser.getUsername(), 1)).thenReturn(false);

        ResponseEntity<Product> result = cartController.deleteProduct(mockUser.getUsername(), 1);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testDeleteProductError() throws IOException {
        // doThrow(new
        when(mockCartDAO.deleteProduct(mockUser.getUsername(), 1)).thenThrow(new IOException());

        ResponseEntity<Product> result = cartController.deleteProduct(mockUser.getUsername(), 1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    public void testClearCartSuccess() throws IOException {
        when(mockCartDAO.clearShoppingCart(mockUser.getUsername())).thenReturn(true);

        ResponseEntity<Boolean> result = cartController.clearCart(mockUser.getUsername());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testClearCartFail() throws IOException {
        when(mockCartDAO.clearShoppingCart(mockUser.getUsername())).thenReturn(false);

        ResponseEntity<Boolean> result = cartController.clearCart(mockUser.getUsername());

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertFalse(result.getBody());
    }

    @Test
    public void testClearCartError() throws IOException {
        when(mockCartDAO.clearShoppingCart(mockUser.getUsername())).thenThrow(new IOException());

        ResponseEntity<Boolean> result = cartController.clearCart(mockUser.getUsername());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    public void testAddProductSuccess() throws IOException {
        when(mockCartDAO.addProduct(mockUser.getUsername(), mockProduct.getID(), mockProduct.getAmount())).thenReturn(mockProduct);

        ResponseEntity<Integer> result = cartController.addProduct(mockUser.getUsername(), 5, mockProduct.getID());

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void testAddProductFail() throws IOException {
        when(mockCartDAO.addProduct(mockUser.getUsername(), mockProduct.getID(), mockProduct.getAmount())).thenReturn(null);

        ResponseEntity<Integer> result = cartController.addProduct(mockUser.getUsername(), mockProduct.getAmount(), mockProduct.getID());

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals(mockProduct.getID(), result.getBody());
    }

    @Test
    public void testAddProductError() throws IOException {
        when(mockCartDAO.addProduct(mockUser.getUsername(), mockProduct.getID(), mockProduct.getAmount())).thenThrow(new IOException());

        ResponseEntity<Integer> result = cartController.addProduct(mockUser.getUsername(), 5, mockProduct.getID());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }
    @Test
    public void testcheckoutSuccess() throws IOException {
        when(mockCartDAO.checkout(mockUser.getUsername())).thenReturn(true);

        ResponseEntity<Boolean> result = cartController.checkout(mockUser.getUsername());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testcheckoutFail() throws IOException {
        when(mockCartDAO.checkout(mockUser.getUsername())).thenReturn(false);

        ResponseEntity<Boolean> result = cartController.checkout(mockUser.getUsername());

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertFalse(result.getBody());
    }

    @Test
    public void testcheckoutError() throws IOException {
        when(mockCartDAO.checkout(mockUser.getUsername())).thenThrow(new IOException());

        ResponseEntity<Boolean> result = cartController.checkout(mockUser.getUsername());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }
}