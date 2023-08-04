package com.example.Sharing.services;


import com.example.Sharing.model.Notification;
import com.example.Sharing.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification findById(long id) {
        return notificationRepository.findById(id);
    }

    public List<Notification> findByUser(long id){
        return notificationRepository.findByUserAndReadFalseOrderByDateDesc(id);
    }

    @Transactional
    public void delete(Notification notification){
        notificationRepository.delete(notification);
    }

    @Transactional
    public void save(Notification notification){
        notificationRepository.save(notification);
    }
}
