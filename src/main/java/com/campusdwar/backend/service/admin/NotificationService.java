package com.campusdwar.backend.service.admin;

import java.util.List;

import com.campusdwar.backend.entity.Notification;

public interface NotificationService {

	void sendToAllUsers(String message);
    List<Notification> getMyNotifications(Long userId);
}
