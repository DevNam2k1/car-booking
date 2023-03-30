package com.project.BookingCar.domain.model;

import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "request_ticket")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestTicket extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "driver_ref_id")
    private Long driverRefId;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name = "driver_phone")
    private String driverPhone;
    @Column(name = "driver_address")
    private String driverAddress;
    @Column(name = "car_ref_id")
    private Long carRefId;
    @Column(name = "car_license_plate")
    private String carLicensePlate;
    @Column(name = "car_chassis_number")
    private String carChassisNumber;
    @Column(name = "car_engine_number")
    private String carEngineNumber;
    @Column(name = "car_line")
    private String carLine;
    @Column(name = "car_brand")
    private String carBrand;
    @Column(name = "car_km")
    private String carKm;
    @Column(name = "expert_ref_id")
    private Long expertRefId;
    @Column(name = "expert_name")
    private String expertName;
    @Column(name = "expert_phone")
    private String expertPhone;
    @Column(name = "garage_ref_id")
    private Long garageRefId;
    private String garageName;
    private String garagePhone;
    private String garageAddress;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestTicketsStatus status;
    @Column(name = "description")
    private String description;
    @Column(name = "garage_confirmed_user")
    private String garageConfirmedUser;
    @Column(name = "garage_confirmed_date")
    private LocalDateTime garageConfirmedDate;
    @Column(name = "price_checked_user")
    private String priceCheckedUser;
    @Column(name = "price_checked_date")
    private LocalDateTime priceCheckedDate;
    @Column(name = "garage_complete_user")
    private String garageCompletedUser;
    @Column(name = "garage_complete_date")
    private LocalDateTime garageCompleteDate;
    @Column(name = "canceled_user")
    private String canceledUser;
    @Column(name = "canceled_date")
    private LocalDateTime canceledDate;
    @OneToMany(mappedBy = "requestTicket", fetch = FetchType.LAZY)
    private List<RequestBookingMedia> requestTicketRequestBookingMedias;

    @OneToMany(mappedBy = "requestTicket", fetch = FetchType.EAGER)
    private Set<RequestServices> requestTicketRequestServices;

    @OneToMany(mappedBy = "requestTicket", cascade = CascadeType.MERGE)
    private List<ServiceTicket> serviceTickets;

    @OneToMany(mappedBy = "requestTicket", cascade = CascadeType.MERGE)
    private List<RequestCarStatus> carStatuses;

    @OneToMany(mappedBy = "requestTicket", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<RequestSurvey> surveys;

}
