package com.estore.api.estoreapi.persistence.User;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.Users.Customer;
import com.estore.api.estoreapi.persistence.Inventory.InventoryDAO;
import com.estore.api.estoreapi.persistence.Inventory.InventoryFileDAO;

import org.springframework.stereotype.Component;

/**
 * The file that manipulates the {@link ShoppingCart shopping cart} of
 * individual {@link Customer customer}. This class
 * manipulates the saved data that corresponds to each user.
 * 
 * {@Literal @Component} is a spring annotation that indicates that this class
 * is to be instantiated
 * and inserted into any class that needs it upon starting.
 * 
 * @author Alen Van
 */
@Component
public class ShoppingCartFileDAO implements ShoppingCartDAO {

    UserDAO userDAO; //the userDAO that corresponds with this DAO

    /**
     * Constructor for the shopping cart DAO class
     * 
     * @param userDAO the userDAO, we pass this in so that the shoppingCartDAO is
     *                able to save the state of the users
     *                since this class modifies the {@link ShoppingCart shopping
     *                cart} which the {@link Customer customers} hold
     */
    public ShoppingCartFileDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Product addProduct(Customer customer, Product product) throws IOException {
        Customer targetCustomer = (Customer) userDAO.getUser(customer.getUsername());
        Product addedProduct = targetCustomer.addProduct(product);
        userDAO.saveUsers();
        return addedProduct;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Boolean deleteProduct(Customer customer, Integer id) throws IOException {
        Customer targetCustomer = (Customer) userDAO.getUser(customer.getUsername());
        Product removedProduct = targetCustomer.removeProduct(id);
        userDAO.saveUsers();
        return removedProduct != null;
    }

    /**
     * {@inheritDoc}
     * @throws IOException
     */
    @Override
    public synchronized Product[] getShoppingCart(Customer customer) throws IOException {
        Customer targetCustomer = (Customer) userDAO.getUser(customer.getUsername());
        return targetCustomer.getCart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized boolean clearShoppingCart(Customer customer) throws IOException {
        Customer targetCustomer = (Customer) userDAO.getUser(customer.getUsername());
        boolean status = targetCustomer.clearCart();
        userDAO.saveUsers();
        return status;
    }
    /**
     * {@inheritDoc}
     */
    public synchronized boolean checkout(String username) throws IOException {
        Customer targetCustomer = (Customer) userDAO.getUser(username);
        boolean status = targetCustomer.checkout();
        if(status){
            targetCustomer.clearCart();
        }
        return status;
    }
    
    

}
