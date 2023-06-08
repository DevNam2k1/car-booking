package com.project.BookingCar.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.project.BookingCar.domain.model.NotificationMessage;

public interface FirebaseMessageService {
    String sendNotification(NotificationMessage notificationMessage) ;
}
