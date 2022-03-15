package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
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
    public void setupinventoryController() {
        mockCartDAO = mock(ShoppingCartDAO.class);
        cartController = new ShoppingCartController(mockCartDAO);
    }

    



}