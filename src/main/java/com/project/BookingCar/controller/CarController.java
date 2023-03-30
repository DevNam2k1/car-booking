package com.project.BookingCar.controller;

import com.project.BookingCar.config.Constant;
import com.project.BookingCar.controller.base.BaseController;
import com.project.BookingCar.domain.dto.CarDTO;
import com.project.BookingCar.domain.dto.message.ExtendedMessage;
import com.project.BookingCar.domain.dto.page.CarPageDTO;
import com.project.BookingCar.domain.param.CarParam;
import com.project.BookingCar.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class CarController extends BaseController {

    private final CarService carService;

    @GetMapping
    @Operation(summary = "Get paging of car")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get paging of car successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<Page<CarPageDTO>> getPagingOfCar(
                                            @RequestParam Integer pageNumber,
                                            @RequestParam Integer pageSize,
                                            CarParam carParam) {
        return (ResponseEntity<Page<CarPageDTO>>) createSuccessResponse("Get paging of car", carService.getPagingOfCar(carParam, pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get car by id")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get car by id successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> getCarById(@PathVariable Long id) {
        return createSuccessResponse("Get car by id", carService.getCarById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new car")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Create a new car successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> createCar(@RequestBody CarDTO carDTO) {
        carService.createCar(carDTO);
        return createSuccessResponse("Create a new car", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PostMapping
    @Operation(summary = "Update car")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Update car successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        carService.updateCar(id, carDTO);
        return createSuccessResponse("Update car", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PostMapping
    @Operation(summary = "Delete car")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Delete car successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return createSuccessResponse("Delete car", HttpStatus.OK);
    }
}
