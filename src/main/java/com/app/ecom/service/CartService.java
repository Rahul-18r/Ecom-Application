package com.app.ecom.service;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository  productRepository;
    private final UserRepository userRepository;

    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
        Optional<Product> productOpt=productRepository.findById(cartItemRequest.getProductId());
        if(productOpt.isEmpty()){
            return false;
        }
        Product product = productOpt.get();
        if(product.getStockQuantity() < cartItemRequest.getProductQuantity()){
            return false;
        }
        Optional<User> userOpt=userRepository.findById(Integer.valueOf(userId));
        if(userOpt.isEmpty()){
            return false;
        }
        User user = userOpt.get();
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user,product);
        if(existingCartItem != null){
            existingCartItem.setQuantity(existingCartItem.getQuantity() +
                    cartItemRequest.getProductQuantity());
            cartItemRepository.save(existingCartItem);
        }else{
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getProductQuantity())));
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getProductQuantity());
            cartItemRepository.save(cartItem);
        }
        return true;
    }
}
