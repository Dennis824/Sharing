package com.example.Sharing.repositories;

import com.example.Sharing.model.Order;
import com.example.Sharing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(long id);

    Iterable<Order> findAllByUser(User user);

    @Query(
            value = "SELECT o.sum FROM Order o where o.id=?1")
    Integer getSum(long id);

    @Query(
            value = "select o from Order o where o.id=?1")
    long getId();
}
