package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.GarageDTO;
import com.project.BookingCar.domain.dto.request.PriceQuotationRequest;
import com.project.BookingCar.domain.dto.page.GaragePageDTO;
import com.project.BookingCar.domain.enums.CRUDEnums;
import com.project.BookingCar.domain.enums.SuperStatus;
import com.project.BookingCar.domain.param.GarageParam;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GarageService {
    Page<GaragePageDTO> getPagingOfGarage(GarageParam garageParam, Integer pageNo, Integer pageSize);
    Page<GaragePageDTO> getPagingOfGarageBetweenLatAndLong(Integer instance, Double latTitude, Double longTitude, Integer pageNo, Integer pageSize);
    GaragePageDTO getGarageById(Long id);
    void updateGarage(Long id, GarageDTO garageDTO);
    void deleteGarage(Long id);
    void handleIncomingRequest(Long requestTicket, SuperStatus status);
    void confirmCheckIn(Long requestTicketId);
    void inspectionResult(Long requestTicketId, List<MultipartFile> resultImages, String description, CRUDEnums features);
    void providePriceQuotation(Long requestTicketId, PriceQuotationRequest priceQuotation, List<MultipartFile> importImages);
    void garageFixedCar(Long requestTicketId);
    void garagePaymentConfirmed(Long requestTicketId);
}
