package com.project.BookingCar.domain.dto.page;

import com.project.BookingCar.domain.enums.RequestTicketType;
import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTicketDTO {
    private Long id;

    private Long driverRefId;

    private String driverName;

    private String driverPhone;

    private String driverAddress;

    private Long carRefId;

    private String carLicensePlate;

    private String carChassisNumber;

    private String carEngineNumber;

    private String carLine;

    private String carBrand;

    private String carKm;

    private Long expertRefId;

    private String expertName;

    private String expertPhone;

    private Long garageRefId;

    private String garageName;

    private String garagePhone;

    private String garageAddress;

    private RequestTicketsStatus status;

    private RequestTicketType type;

    private String description;

    private String garageConfirmedUser;

    private LocalDateTime garageConfirmedDate;

    private String priceCheckedUser;

    private LocalDateTime priceCheckedDate;

    private String garageCompletedUser;

    private LocalDateTime garageCompleteDate;

    private String canceledUser;

    private LocalDateTime appointmentDate;

    private LocalDateTime appointmentTime;

    private LocalDateTime canceledDate;

    private String addressMark;

    private List<RequestBookingMediaDTO> requestTicketRequestBookingMedias;

    private Set<RequestServicesDTO> requestTicketRequestServices;

    private List<ServiceTicketDTO> serviceTickets;

    private List<RequestCarStatusDTO> carStatuses;

    private Set<RequestSurveyDTO> surveys;

}
