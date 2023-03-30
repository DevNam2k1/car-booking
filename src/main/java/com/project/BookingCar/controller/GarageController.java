package com.project.BookingCar.controller;

import com.project.BookingCar.config.Constant;
import com.project.BookingCar.controller.base.BaseController;
import com.project.BookingCar.domain.dto.GarageDTO;
import com.project.BookingCar.domain.dto.message.ExtendedMessage;
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
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    @Operation(summary = "Create new a garage")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Create new a garage successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> createGarage(@RequestBody GarageDTO garageDTO){
        garageService.createNewGarage(garageDTO);
        return createSuccessResponse("Create a new garage", HttpStatus.CREATED);
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
}
