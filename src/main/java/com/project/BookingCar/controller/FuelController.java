package com.project.BookingCar.controller;

import com.project.BookingCar.config.Constant;
import com.project.BookingCar.controller.base.BaseController;
import com.project.BookingCar.domain.dto.FuelDTO;
import com.project.BookingCar.domain.dto.message.ExtendedMessage;
import com.project.BookingCar.service.FuelService;
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
@RequestMapping("/fuel")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class FuelController extends BaseController {

    private final FuelService fuelService;

    @GetMapping
    @Operation(summary = "Get paging of fuel")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get paging of fuel successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> getPagingOfFuel(@RequestParam(required = false) String name,
                                          @RequestParam Integer pageNo,
                                          @RequestParam Integer pageSize){
        return createSuccessResponse("Get paging fuel successful", fuelService.getPagingFuel(name, pageNo, pageSize));
    }

    @PostMapping
    @Operation(summary = "Create a new fuel")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Create a new fuel successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> createNewFuel(@RequestBody  FuelDTO fuelDTO) {
        fuelService.createNewFuel(fuelDTO);
        return createSuccessResponse("Create a new fuel", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update fuel")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Update fuel successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> updateFuel(@PathVariable Long id, @RequestBody FuelDTO fuelDTO) {
        fuelService.updateFuel(id, fuelDTO);
        return createSuccessResponse("Update fuel", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete fuel")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Delete fuel successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> deleteFuel(@PathVariable Long id){
        fuelService.deleteFuel(id);
        return createSuccessResponse("Delete fuel", HttpStatus.OK);
    }
}
