package com.campusdwar.backend.controller.student;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusdwar.backend.entity.Notification;
import com.campusdwar.backend.entity.User;
import com.campusdwar.backend.service.admin.NotificationService;

@RestController
@RequestMapping("/notifications")
@PreAuthorize("hasAnyRole('STUDENT','FACULTY')")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public List<Notification> myNotifications(
            @AuthenticationPrincipal User user
    ) {
        return service.getMyNotifications(user.getUserId());
    }
}
