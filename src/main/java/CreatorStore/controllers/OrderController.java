package CreatorStore.controllers;

import CreatorStore.dto.OrderRequest;
import CreatorStore.entities.Order;
import CreatorStore.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

    @GetMapping
    public List<Order> getAllOrder(){
    return orderService.getOrders();
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrdersById(id);
    }

    @DeleteMapping("{id}")
    public void deleteOrderById(@PathVariable Long id){
         orderService.deleteOrdersById(id);
    }

}
