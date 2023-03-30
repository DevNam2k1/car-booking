package com.project.BookingCar.controller;

import com.project.BookingCar.config.Constant;
import com.project.BookingCar.controller.base.BaseController;
import com.project.BookingCar.domain.dto.BrandDTO;
import com.project.BookingCar.domain.dto.message.ExtendedMessage;
import com.project.BookingCar.service.BrandService;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class BrandController extends BaseController {

    private final BrandService brandService;

    @GetMapping
    @Operation(summary = "Get paging of brand")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get paging of brand successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})

    public ResponseEntity<?> getPagingOfBrand(@RequestParam(required = false) String name,
                                              @RequestParam Integer pageNo,
                                              @RequestParam Integer pageSize) {
        return createSuccessResponse("Get paging of brand",brandService.getPagingBrand(name, pageNo, pageSize));
    }

    @PostMapping
    @Operation(summary = "Create a new brand")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Create a new brand successfully",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> createNewBrand(@RequestBody BrandDTO brandDTO){
        brandService.createNewBrand(brandDTO);
        return createSuccessResponse("Create a new brand",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update brand ")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Update brand successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> updateBrand(@PathVariable Long id,@RequestBody BrandDTO brandDTO){
        brandService.updateBrand(id,brandDTO);
        return createSuccessResponse("Update brand", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete brand ")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Delete brand successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return createSuccessResponse("Delete brand", HttpStatus.OK);
    }

}
