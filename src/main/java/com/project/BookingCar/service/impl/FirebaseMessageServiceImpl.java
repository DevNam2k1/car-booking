package com.project.BookingCar.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.project.BookingCar.domain.model.NotificationMessage;
import com.project.BookingCar.service.FirebaseMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirebaseMessageServiceImpl implements FirebaseMessageService {

    private final FirebaseMessaging firebaseMessaging;


    @Override
    public String sendNotification(NotificationMessage notificationMessage) {
        Notification notification = Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImage())
                .build();

        Message message = Message
                .builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .putAllData(notificationMessage.getData())
                .build();
        try {
            firebaseMessaging.send(message);
            return "Send notification successfully";
        } catch (FirebaseMessagingException e){
            return "Error" + e;
        }
    }
}
