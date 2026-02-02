package com.campusdwar.backend.service.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.campusdwar.backend.entity.Notification;
import com.campusdwar.backend.entity.User;
import com.campusdwar.backend.repository.NotificationRepository;
import com.campusdwar.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository,
            UserRepository userRepository
    ) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional  // 🔥 MUST BE HERE
    public void sendToAllUsers(String message) {

        if (message == null || message.trim().isEmpty()) {
            throw new RuntimeException("Message cannot be empty");
        }

        List<User> users = userRepository.findAll();

        List<Notification> notifications = new ArrayList<>();

        for (User user : users) {

            if (user == null || user.getRole() == null) continue;

            String role = user.getRole().getRoleName();
            if (!"STUDENT".equals(role) && !"FACULTY".equals(role)) continue;

            Notification notification = new Notification();
            notification.setUser(user);
            notification.setMessage(message);
            notification.setCreatedAt(LocalDateTime.now());

            notifications.add(notification);
        }

        // 🔥 THIS IS THE KEY DIFFERENCE
        notificationRepository.saveAll(notifications);
        notificationRepository.flush();
    }


    @Override
    public List<Notification> getMyNotifications(Long userId) {
        return notificationRepository
                .findByUserUserIdOrderByCreatedAtDesc(userId);
    }
}
