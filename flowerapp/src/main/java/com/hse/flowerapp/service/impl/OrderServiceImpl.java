package com.hse.flowerapp.service.impl;

import com.hse.flowerapp.domain.Order;
import com.hse.flowerapp.domain.OrderItem;
import com.hse.flowerapp.domain.OrderStatus;
import com.hse.flowerapp.dto.OrderDto;
import com.hse.flowerapp.repository.ItemRepository;
import com.hse.flowerapp.repository.OrderItemRepository;
import com.hse.flowerapp.repository.OrderRepository;
import com.hse.flowerapp.repository.UserRepository;
import com.hse.flowerapp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository, UserRepository userRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderDto getItemById(Long order_id) {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long user_id) {
        return null;
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order newOrder = new Order();
        newOrder.setTotalSum(orderDto.getTotalSum());
        newOrder.setOrderStatus(OrderStatus.CREATED);
        newOrder.setSecretDelivery(false);
        newOrder.setDeliveryPrice(orderDto.getDeliveryPrice());
        newOrder.setPhone(orderDto.getPhone());
        newOrder.setEmail(orderDto.getEmail());

        if(orderDto.getPromocode() != null)
            newOrder.setPromocode(orderDto.getPromocode());
        if(orderDto.getDiscountSum() != null)
            newOrder.setDiscountSum(orderDto.getDiscountSum());
        if(orderDto.getName() != null)
            newOrder.setName(orderDto.getName());
        if(orderDto.getComment() != null)
            newOrder.setComment(orderDto.getComment());
        
        newOrder.setUser(userRepository.getById(orderDto.getUserId()));

        for (Map.Entry<Long, Integer> item: orderDto.getListItemIds().entrySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(itemRepository.getItemById(item.getKey()));
            orderItem.setCountOfThisItemInOrder(item.getValue());
            orderItem.setOrder(newOrder);
            orderItemRepository.save(orderItem);

            List<OrderItem> orderItemList1 = newOrder.getCountItemInOrder();
            orderItemList1.add(orderItem);
            newOrder.setCountItemInOrder(orderItemList1);
            orderRepository.save(newOrder);

            List<OrderItem> orderItemList2 = itemRepository.getItemById(item.getKey()).getCountItemInOrders();
            orderItemList2.add(orderItem);
            itemRepository.getItemById(item.getKey()).setCountItemInOrders(orderItemList2);
            itemRepository.save(itemRepository.getItemById(item.getKey()));
        }

        return newOrder;
    }

    @Override
    public OrderDto cancelOrder(OrderDto orderDto) {
        return null;
    }
}
