package com.example.Sharing.services;


import com.example.Sharing.model.Order;
import com.example.Sharing.model.User;
import com.example.Sharing.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUser(User user) {
        return (List<Order>) orderRepository.findAllByUser(user);
    }

    public Order findById(long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public void deleteById(long id){
        orderRepository.deleteById(id);
    }

    @Transactional
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Transactional
    public void save(Order order)throws Exception{
        orderRepository.save(order);
    }
    public int getSum(long id){
        return orderRepository.getSum(id);
    }
    public long getId(){
        return orderRepository.getId();
    }
}