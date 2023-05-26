package com.project.BookingCar.domain.dto.page;

import com.project.BookingCar.domain.enums.ServiceTicketsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTicketDTO {
    private Long id;

    private LocalDateTime garageDeliveryDate;

    private LocalDateTime expectedDeliveryDate;

    private ServiceTicketsStatus status;

    private LocalDateTime checkedDate;

    private String checkedUser;

    private LocalDateTime updateWaitingCustomerApproveDate;

    private String updateWaitingCustomerApproveUser;

    private LocalDateTime customerApprovedPriceDate;

    private String customerApprovedPriceUser;

    private LocalDateTime fixedDate;

    private String fixedUser;

    private LocalDateTime paymentConfirmationDate;

    private String paymentConfirmationUser;

    private LocalDateTime garageHandOverCarDate;

    private String garageHandOverCarUser;

    private LocalDateTime completedDate;

    private String completedUser;

    private LocalDateTime canceledDate;

    private String canceledUser;

    private String paymentType;

    private Boolean isPayment;

    private Double totalPrice;

    private String description;

    private List<ServiceBookingMediaDTO> serviceBookingMediaDTOS;

    private List<ServiceCarStatusDTO> serviceCarStatusDTOS;

    private List<ServiceServicesDTO> serviceServicesDTOS;
}
