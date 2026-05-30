package CreatorStore.service;

import CreatorStore.Repositories.OrderRepository;
import CreatorStore.Repositories.ProductRepository;
import CreatorStore.dto.OrderItemRequest;
import CreatorStore.dto.OrderRequest;
import CreatorStore.entities.Order;
import CreatorStore.entities.OrderItem;
import CreatorStore.entities.Product;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());

        order.setStaus("CONFIRMED");

        for (OrderItemRequest itemRequest : orderRequest.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with given id"));

            // check the product stock
            if (product.getStockQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("not enough stock for the given product ");
            }

            BigDecimal priceOfItem = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalPrice = totalPrice.add(priceOfItem);

            // update product table with latest stock quantity
            product.setStockQuantity(
                    product.getStockQuantity() - itemRequest.getQuantity());
            productRepository.save(product);

            // Builder patern to make object
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrdersById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id not found" + id));
    }

    public void deleteOrdersById(Long id) {
        orderRepository.deleteById(id);
    }
}
