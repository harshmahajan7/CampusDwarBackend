package com.campusdwar.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusdwar.backend.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

//    List<Notification> findByUser(User user);
//
//    List<Notification> findTop10ByUserOrderByCreatedAtDesc(User user);
	
    List<Notification> findByUserUserIdOrderByCreatedAtDesc(Long userId);
}
