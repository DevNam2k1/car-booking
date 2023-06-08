package com.project.BookingCar.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.project.BookingCar.controller.base.BaseController;
import com.project.BookingCar.domain.model.NotificationMessage;
import com.project.BookingCar.service.FirebaseMessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
@SecurityRequirement(name = "Bearer Authentication")
public class FirebaseMessageController extends BaseController {

    private final FirebaseMessageService firebaseMessageService;

    @PostMapping("/push-notification")
    public ResponseEntity<?> pushNotification(@RequestBody  NotificationMessage notificationMessage)  {
        firebaseMessageService.sendNotification(notificationMessage);
        return createSuccessResponse("Send notification", HttpStatus.OK);
    }
}
