package com.project.BookingCar.controller;

import com.project.BookingCar.config.Constant;
import com.project.BookingCar.controller.base.BaseController;
import com.project.BookingCar.domain.dto.GarageDTO;
import com.project.BookingCar.domain.dto.message.ExtendedMessage;
import com.project.BookingCar.domain.enums.SuperStatus;
import com.project.BookingCar.domain.param.GarageParam;
import com.project.BookingCar.service.GarageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/garage")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class GarageController extends BaseController {

    private final GarageService garageService;

    @GetMapping
    @Operation(summary = "Get paging of garage")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get paging of garage successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> getPagingOfGarage(GarageParam garageParam,
                                               @RequestParam Integer pageNo,
                                               @RequestParam Integer pageSize) {
        return createSuccessResponse("Get paging of garage successful", garageService.getPagingOfGarage(garageParam,pageNo, pageSize));
    }

    @GetMapping("/to-booking")
    @Operation(summary = "Get paging of garage")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get paging of garage successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> getDistanceOfLatAndLong(@RequestParam Integer distance,
                                                     @RequestParam Double latTitude,
                                                     @RequestParam Double longTitude,
                                                     @RequestParam Integer pageNo,
                                                     @RequestParam Integer pageSize){
        return createSuccessResponse("Get paging of garage", garageService.getPagingOfGarageBetweenLatAndLong(distance, latTitude, longTitude, pageNo, pageSize));

    }
    @GetMapping("/{id}")
    @Operation(summary = "Get garage by id")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get garage by id successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> getGarageById(@PathVariable Long id){
        return createSuccessResponse("Get garage by id", garageService.getGarageById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update garage")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Update garage successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> updateGarage(@PathVariable Long id, @RequestBody GarageDTO garageDTO) {
        garageService.updateGarage(id, garageDTO);
        return createSuccessResponse("Update garage", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete garage")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Delete garage successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> deleteGarage(@PathVariable Long id) {
        garageService.deleteGarage(id);
        return createSuccessResponse("Delete garage", HttpStatus.OK);
    }

    @PostMapping("/{requestTicketId}/handle-incoming-request/{status}")
    @Operation(summary = "Garage accepted appointment of car")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Garage accepted appointment of car successfully",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> handleIncomingRequest(@PathVariable Long requestTicketId, @PathVariable SuperStatus status){
        garageService.handleIncomingRequest(requestTicketId, status);
        return createSuccessResponse("Garage accepted appointment of car", HttpStatus.OK);
    }

    @PostMapping("/{requestTicketId}/confirm-checked-in")
//    @PreAuthorize("hasAuthority('GARAGE')")
    @Operation(summary = "Garage confirm driver check in")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Garage confirm driver check in successfully",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> confirmCheckedIn(@PathVariable Long requestTicketId){
        garageService.confirmCheckIn(requestTicketId);
        return createSuccessResponse("Garage confirm driver check in", HttpStatus.OK);
    }

   @PostMapping("/{requestTicketId}/car-inspection-report")
   @Operation(summary = "Car inspection result in garage")
   @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Car inspection result in garage successfully",
           content = {@Content(mediaType = "application/json",
                   schema = @Schema(implementation = ExtendedMessage.class))})
   @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
           content = {@Content(mediaType = "application/json",
                   schema = @Schema(implementation = ExtendedMessage.class))})
   @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
           content = {@Content(mediaType = "application/json",
                   schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> inspectionResult(@PathVariable Long requestTicketId, @RequestPart String description, @RequestPart List<MultipartFile> resultImages){
        garageService.inspectionResult(requestTicketId, resultImages, description);
        return createSuccessResponse("Car inspection result in garage successfully", HttpStatus.OK);
   }
}
