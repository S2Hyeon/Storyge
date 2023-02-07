package com.example.project.notification.model.repository;

import com.example.project.notification.model.entity.Notification;
import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findTop30ByUserIdOrderByCreatedAtDesc(User userId);

}
