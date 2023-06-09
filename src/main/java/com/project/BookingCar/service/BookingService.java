package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.PriceQuotationDTO;
import com.project.BookingCar.domain.dto.appointment.CreateAppointmentDTO;
import com.project.BookingCar.domain.dto.page.AppointmentDriverPageDTO;
import com.project.BookingCar.domain.dto.page.InspectionResultDTO;
import com.project.BookingCar.domain.dto.page.RequestTicketDTO;
import com.project.BookingCar.domain.dto.page.ServiceBookingMediaDTO;
import com.project.BookingCar.domain.enums.AppointmentDriverStatus;
import com.project.BookingCar.domain.enums.PaymentType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {
    void createNewBookingForAppointment(CreateAppointmentDTO createAppointmentDTO);

    void createUpgradeOfTheCar(String description);

    Page<AppointmentDriverPageDTO> getPagingOfAppointmentByStatus(AppointmentDriverStatus status, Integer pageNum, Integer pageSize);

    Integer countRequestTicketOfWaitingCustomerApprove();

    RequestTicketDTO getRequestTicketInformation(Long requestTicketId);

    InspectionResultDTO getInspectionResultForCustomer(Long requestTicketId);

    PriceQuotationDTO getDriverReceivePriceQuotation(Long requestTicketId);

    void approvePriceQuotation(Long requestTicketId);

    void driverConfirmPayment(Long requestTicketId, PaymentType paymentType);
    List<ServiceBookingMediaDTO> getHandedOverCar(Long requestTicketId);
    void driverReceivedCar(Long requestTicketId);
}
