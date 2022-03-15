package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.Users.User;
import com.estore.api.estoreapi.persistence.User.UserDAO;

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
public class UserControllerTest {
    private UserController userController;
    private UserDAO mockUserDAO;

    /**
     * Before each test, create a new inventoryController object and inject
     * a mock Product DAO
     */
    @BeforeEach
    public void setupUserController() {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
    }
    @Test
    public void testgetUser() throws IOException { // getProduct may throw IOException
        // Setup
        User user = new User("John Adamns");
        // When the same id is passed in, our mock Product DAO will return the Product
        // object
        when(mockUserDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = userController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testgetUserNotFound() throws Exception { // createProduct may throw IOException
        // Setup
        String user = ("Ben Dover");
        // When the same id is passed in, our mock Product DAO will return null,
        // simulating
        // no Product found
        when(mockUserDAO.getUser(user)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = userController.getUser(user);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testgetUserHandleException() throws Exception { // createProduct may throw IOException
        // Setup
        String user = ("Ben Dover");
        // When getProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).getUser(user);

        // Invoke
        ResponseEntity<User> response = userController.getUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void testdeleteUser() throws IOException { // deleteProduct may throw IOException
        // Setup
        String user = ("Yuri Nator");
        // when deleteProduct is called return true, simulating successful deletion
        when(mockUserDAO.deleteUser(user)).thenReturn(true);

        // Invoke
        ResponseEntity<String> response = userController.deleteUser(user);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testdeleteUserNotFound() throws IOException { // deleteProduct may throw IOException
        // Setup
        String user = ("John Smith");
        // when deleteProduct is called return false, simulating failed deletion
        when(mockUserDAO.deleteUser(user)).thenReturn(false);

        // Invoke
        ResponseEntity<String> response = userController.deleteUser(user);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testdeleteUserException() throws IOException { // deleteProduct may throw IOException
        // Setup
        String user = ("John Smith");
        // When deleteProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).deleteUser(user);

        // Invoke
        ResponseEntity<String> response = userController.deleteUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void testcreateProduct() throws IOException { // createProduct may throw IOException
        // Setup
        User user = new User("John Smith");
        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockUserDAO.addUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = userController.addUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());//not sure what created should replace because we are only adding people
        assertEquals(user, response.getBody());
    }
    @Test
    public void testaddUserProductFailed() throws IOException { // createProduct may throw IOException
        // Setup
        User user = new User("John Smith");
        // when createProduct is called, return false simulating failed
        // creation and save
        when(mockUserDAO.addUser(user.getUsername())).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = userController.addUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testaddUserProductHandleException() throws IOException { // createProduct may throw IOException
        // Setup
        User user = new User("John Smith");

        // When createProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).addUser(user.getUsername());

        // Invoke
        ResponseEntity<User> response = userController.addUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
    @Test
    public void testSaveuser() throws IOException { // deleteProduct may throw IOException
        // Setup
        User[] users = new User[2];
        users[0] = new User("John Smith");
        users[1] = new User("Will Smith");
        String name = "John Smith";
        String name2 = "Will Smith";
        // when deleteProduct is called return true, simulating successful deletion
        when(mockUserDAO.saveUsers(users)).thenReturn(true);

        // Invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(ProductId);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    

}