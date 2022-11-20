package com.shree.compositeKey.controller;

import com.shree.compositeKey.dto.OrderDTO;
import com.shree.compositeKey.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/{id}")
    ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        OrderDTO orderDto = orderService.getOrder(id);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/orders")
    ResponseEntity<List<OrderDTO>> getAllOrders(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getAll(pageable));
    }

    @PostMapping("/order/save")
    ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO dto) throws Exception {
        OrderDTO orderDto = orderService.saveOrder(dto);
        return ResponseEntity.created(new URI("/order/save")).body(orderDto);
    }

    @DeleteMapping("/order/delete/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws Exception {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
