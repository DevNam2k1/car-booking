package com.project.BookingCar.controller;

import com.project.BookingCar.config.Constant;
import com.project.BookingCar.controller.base.BaseController;
import com.project.BookingCar.domain.dto.DriverCarDTO;
import com.project.BookingCar.domain.dto.message.ExtendedMessage;
import com.project.BookingCar.domain.param.DriverCarParam;
import com.project.BookingCar.service.DriverCarService;
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
@RequestMapping("/driver-car")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class DriverCarController extends BaseController {

    private final DriverCarService driverCarService;

    @GetMapping("/paging/{driverId}")
    @Operation(summary = "Get paging car of driver id")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get paging car of driver id successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> getPagingCarOfDriverId(@PathVariable Long driverId,
                                                    @RequestParam Integer pageNum,
                                                    @RequestParam Integer pageSize){
        return createSuccessResponse("Get paging car of driver id", driverCarService.getPagingCarOfDriverId(driverId, pageNum, pageSize));
    }
    @GetMapping("/search")
    @Operation(summary = "Get paging car and searching with param")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get paging car and searching with param successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> getPagingCarAndSearchingByParam(DriverCarParam driverCarParam,
                                                             @RequestParam Integer pageNum,
                                                             @RequestParam Integer pageSize){
        return createSuccessResponse("Get paging by searching param",driverCarService.getPagingCarAndSearchingForParam(driverCarParam,pageNum,pageSize));
    }
    @GetMapping("/{driverCarId}")
    @Operation(summary = "Get car of driver by id")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get car of driver by id successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> getCarOfDriverByDriverCarId(@PathVariable Long driverCarId){
        return createSuccessResponse("Get car by id of driver",driverCarService.getCarOfDriverById(driverCarId));
    }
    @PostMapping
    @Operation(summary = "Create a new car of driver")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Create a new car of driver successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> createNewCarOfDriver(@RequestBody DriverCarDTO driverCarDTO){
        driverCarService.createCarOfDriver(driverCarDTO);
        return createSuccessResponse("Create a new car of driver", HttpStatus.CREATED);
    }
    @PutMapping("/{driverCarId}")
    @Operation(summary = "Update car of driver")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Update car of driver successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> updateCarOfDriver(@PathVariable Long driverCarId, @RequestBody DriverCarDTO driverCarDTO){
        driverCarService.updateCarOfDriver(driverCarId, driverCarDTO);
        return createSuccessResponse("Update car of driver", HttpStatus.OK);
    }
    @PostMapping("/change-status/{driverCarId}")
    @Operation(summary = "Change status of car in driver")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Change status of car in driver successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> changeStatusOfCarInDriver(@PathVariable Long driverCarId){
        driverCarService.disableCarOfDriver(driverCarId);
        return createSuccessResponse("Change status", HttpStatus.OK);
    }

}
