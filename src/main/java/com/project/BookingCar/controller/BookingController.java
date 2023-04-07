package com.project.BookingCar.controller;

import com.project.BookingCar.config.Constant;
import com.project.BookingCar.controller.base.BaseController;
import com.project.BookingCar.domain.dto.appointment.CreateAppointmentDTO;
import com.project.BookingCar.domain.dto.message.ExtendedMessage;
import com.project.BookingCar.domain.enums.AppointmentDriverStatus;
import com.project.BookingCar.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class BookingController extends BaseController {
    private final BookingService bookingService;

    @PostMapping("/create-appointment")
    @Operation(summary = "Create a new appointment")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Create a new appointment successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> createNewAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO){
        bookingService.createNewBookingForAppointment(createAppointmentDTO);
        return createSuccessResponse("Create a new appointment", HttpStatus.CREATED);
    }

    @PostMapping("/upgrade-car")
    @Operation(summary = "Upgrade of the car")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Upgrade of the car successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> upgradeOfTheCar(@RequestBody  String description){
        bookingService.createUpgradeOfTheCar(description);
        return createSuccessResponse("Upgrade of the car", HttpStatus.CREATED);
    }

    @GetMapping("/appointment-of-driver")
    @Operation(summary = "Get paging of appointment")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get paging of appointment successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> getPagingOfAppointment(@RequestParam AppointmentDriverStatus appointmentDriverStatus,
                                                    @RequestParam Integer pageNum,
                                                    @RequestParam Integer pageSize){
        return createSuccessResponse("Get paging of appointment", bookingService.getPagingOfAppointmentByStatus(appointmentDriverStatus, pageNum, pageSize));
    }

}
