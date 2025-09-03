package com.berkay.ecom.app.service;

import com.berkay.ecom.app.dto.order.OrderResponse;
import com.berkay.ecom.app.model.CartItem;
import com.berkay.ecom.app.model.Order;
import com.berkay.ecom.app.model.OrderItem;
import com.berkay.ecom.app.model.User;
import com.berkay.ecom.app.model.enums.OrderStatus;
import com.berkay.ecom.app.repository.OrderRepository;
import com.berkay.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserRepository userRepository;

    public Optional<OrderResponse> createOrder(String userId) {
        // validate cart items
        List<CartItem> cartItems = cartService.getCart(userId);
        if (cartItems.isEmpty()) {
            return Optional.empty();
        }

        // validate for user
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        User user = userOptional.get();

        // total price
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                )).toList();
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(userId);
        return Optional.of(mapToOrderResponse(Order savedOrder));
    }
}
