package com.hse.flowerapp.repository;

import com.hse.flowerapp.domain.Order;
import com.hse.flowerapp.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getById(Long id);

}
