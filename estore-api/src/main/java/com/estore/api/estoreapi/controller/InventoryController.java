package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.persistence.InventoryDAO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the REST API requests for the Inventory resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST
 * API
 * method handler to the Spring framework
 * 
 * @author Team jadin
 */

@RestController
@RequestMapping("products")
public class InventoryController {
    private static final Logger LOG = Logger.getLogger(InventoryController.class.getName());
    private InventoryDAO inventoryDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param inventoryDAO The {@link InventoryDAO Inventory Data Access Object} to
     *                     perform CRUD operations
     *                     <br>
     *                     This dependency is injected by the Spring Framework
     */
    public InventoryController(InventoryDAO inventoryDAO) {
        this.inventoryDao = inventoryDAO;
    }

    /**
     * Responds to the GET request for all {@linkplain Product products}
     * 
     * @return ResponseEntity with array of {@link Product product} objects (may be
     *         empty) and
     *         HTTP status of OK<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Product[]> getProducts() {
        LOG.info("GET /products");

        try {
            return new ResponseEntity<Product[]>(inventoryDao.getProducts(), HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@link Product} with the provided product object
     * 
     * @param product The {@link Product} to create
     * 
     * @return A ResponseEntity with the newly created {@link Product product} and
     *         HTTP status CREATED
     *         A ResponseEntity with HTTP status of CONFLICT if {@link Product
     *         product} already exists
     *         A ResposneEntity with HTTP status INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product newProduct = inventoryDao.createProduct(product);
            if (newProduct == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets a {@link Product} with the provided id
     * 
     * @param id An id that is used to get a product that corresponds to that id
     *           from the inventory
     * 
     * @return A ResonseEntity with the {@link Product product} that was obtained
     *         from the id
     *         and a HTTP status code OK
     *         A ResponseEntity with HTTP status of NOT_FOUND if no product was
     *         found
     *         A ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        try {
            Product productGot = inventoryDao.getProduct(id);
            if (productGot == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Product>(productGot, HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Product products} which all
     * share the
     * text input in their name
     * 
     * @param name The String being searched to find {@link Product products} with
     *             the string in the name
     * 
     * @return ResponseEntity with an array of {@link Product product} objects
     *         containing
     *         the input String in their names (Array may be empty)
     */
    @GetMapping("/")
    public ResponseEntity<Product[]> searchProducts(@RequestParam String name) {
        LOG.info("GET /products/?name=" + name);
        try {
            Product[] products = inventoryDao.searchProducts(name);
            return new ResponseEntity<Product[]>(products, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain Product product} with the provided
     * {@linkplain Product product} object, if it exists
     * 
     * @param product The {@link Product product} to update
     * 
     * @return ResponseEntity with updated {@link Product product} object and HTTP
     *         status of OK if updated<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        LOG.info("PUT /product " + product);

        // Replace below with your implementation
        try {
            Product producta = inventoryDao.updateProduct(product);
            if (producta != null)
                return new ResponseEntity<Product>(producta, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Product products} which all
     * share the
     * text input in their name
     * 
     * @param name The String being searched to find {@link Product products} with
     *             the string in the name
     * 
     * @return ResponseEntity with an array of {@link Product product} objects
     *         containing
     *         the input String in their names (Array may be empty)
     *
     *
     *
     *         @GetMapping("/")
     *         public ResponseEntity<Product[]> deleteProduct(@RequestParam String
     *         name){
     *         LOG.info("GET /products/?name=" +name);
     *         try{
     *         Product[] products = inventoryDao.searchProducts(name);
     *         return new ResponseEntity<Product[]>(products, HttpStatus.OK);
     *         }
     *         catch (IOException e){
     *         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     *         }
     *         }
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        LOG.info("DELETE /products/?name=" + id);
        try {
            boolean deleted = inventoryDao.deleteProduct(id);
            if (!deleted) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Product>(HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}