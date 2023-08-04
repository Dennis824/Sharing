package com.example.Sharing.repositories;

import com.example.Sharing.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserAndReadFalseOrderByDateDesc(long user);

    Notification findById(long id);
}