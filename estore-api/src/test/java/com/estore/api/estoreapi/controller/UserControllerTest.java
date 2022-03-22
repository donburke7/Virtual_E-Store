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
    private User mockUser;

    /**
     * Before each test, create a new inventoryController object and inject
     * a mock Product DAO
     */
    @BeforeEach
    public void setupUserController() {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
        mockUser = mock(User.class);
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
    public void testDeleteUserFail() throws IOException{
        String username = "Joe";

        when(mockUserDAO.deleteUser(username)).thenReturn(false);

        ResponseEntity<String> result = userController.deleteUser(username);
        
        assertEquals(username, result.getBody());
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testDeleteUserError() throws IOException{
        String username = "Joe";

        doThrow(new IOException()).when(mockUserDAO).deleteUser(username);

        ResponseEntity<String> result = userController.deleteUser(username);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    public void testAddUserSuccess() throws IOException{
        String username = "Joe";

        when(mockUserDAO.addUser(username)).thenReturn(mockUser);

        ResponseEntity<User> result = userController.addUser(username);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockUser, result.getBody());
    }

    @Test
    public void testAddUserFail() throws IOException{
        String username = "Joe";

        when(mockUserDAO.addUser(username)).thenReturn(null);

        ResponseEntity<User> result = userController.addUser(username);
        
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testAddUserError() throws IOException{
        String username = "Joe";

        doThrow(new IOException()).when(mockUserDAO).addUser(username);

        ResponseEntity<User> result = userController.addUser(username);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }
}