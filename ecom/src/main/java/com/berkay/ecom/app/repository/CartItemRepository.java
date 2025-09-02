package com.berkay.ecom.app.repository;

import com.berkay.ecom.app.model.CartItem;
import com.berkay.ecom.app.model.Product;
import com.berkay.ecom.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);
}
