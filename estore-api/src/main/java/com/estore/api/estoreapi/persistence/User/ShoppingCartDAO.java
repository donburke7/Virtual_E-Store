package com.estore.api.estoreapi.persistence.User;

import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.Users.Customer;
import com.estore.api.estoreapi.model.Users.User;

/**
 * The interface that holds the abstracted methods for the {@linkplain ShoppingCartFileDAO} class
 * 
 * @author Alen Van
 */
public interface ShoppingCartDAO {
    
    /**
     * When given a {@linkplain Customer customer} and the desired {@link Product
     * product} to add to their {@link ShoppingCart shopping cart}, this method
     * calls upon the underlying method within the {@link Customer customer} class to add
     * the {@link Product product} to their {@link ShoppingCart shopping cart}.
     * 
     * @param customer the {@link Customer customer} that is adding a
     *                 {@link Product product}
     * @param product  the {@link Product product} that is being added
     * 
     * @return the {@link Product product} that was added
     * 
     * @throws IOException If an issue occured whilst accessing the json files
     */
    Product addProduct(Customer customer, Product product) throws IOException;

    /**
     * Deletes a {@linkplain Product product} within the specified {@link Customer
     * customer}'s {@link ShoppingCart shopping cart}
     * 
     * @param customer the {@link Customer customer} that is deleting a
     *                 {@link Product product}
     * @param id       the id of the {@link Product product} to delete
     * 
     * @return a boolean indicating whether or not the deletion was successful
     * 
     * @throws IOException If an issue occured whilst accessing the json files
     */
    Boolean deleteProduct(Customer customer, Integer id) throws IOException;

    /**
     * A method that gets the entire {@linkplain Customer customer}'s {@link ShoppingCart
     * shopping cart} in the form of an
     * array of {@link Product products}
     * 
     * @param customer the {@link Customer customer} that is invoking this method
     * 
     * @return the array of {@link Product products} in this specific
     *         {@link Customer customer}'s
     *         {@link ShoppingCart shopping cart}
     */
    Product[] getShoppingCart(Customer customer);

    /**
     * Clears the specified {@linkplain Customer customer}'s {@link ShoppingCart shopping
     * cart}
     * 
     * @param customer the {@link Customer customer} that wishes to clear their
     *                 {@link ShoppingCart shopping cart}
     * 
     * @return a boolean indicating that the {@link ShoppingCart shopping cart} was
     *         cleared
     * 
     * @throws IOException If an issue occured whilst accessing the json files
     */
    boolean clearShoppingCart(Customer customer) throws IOException;



}
