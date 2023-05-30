package com.project.BookingCar.domain.model;

import com.project.BookingCar.domain.enums.ServiceTicketsStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "service_ticket")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServiceTicket extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_ticket_id")
    private RequestTicket requestTicket;
    @Column(name = "garage_delivery_date")
    private LocalDateTime garageDeliveryDate;
    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ServiceTicketsStatus status;
    @Column(name = "checked_date")
    private LocalDateTime checkedDate;
    @Column(name = "checked_user")
    private String checkedUser;
    @Column(name = "update_waiting_customer_approve_date")
    private LocalDateTime updateWaitingCustomerApproveDate;
    @Column(name = "update_waiting_customer_approve_user")
    private String updateWaitingCustomerApproveUser;
    @Column(name = "customer_approved_price_date")
    private LocalDateTime customerApprovedPriceDate;
    @Column(name = "customer_approved_price_user")
    private String customerApprovedPriceUser;
    @Column(name = "fixed_date")
    private LocalDateTime fixedDate;
    @Column(name = "fixed_user")
    private String fixedUser;
    @Column(name = "payment_confirmation_date")
    private LocalDateTime paymentConfirmationDate;
    @Column(name = "payment_confirmation_user")
    private String paymentConfirmationUser;
    @Column(name = "garage_hand_over_car_date")
    private LocalDateTime garageHandOverCarDate;
    @Column(name = "garage_hand_over_car_user")
    private String garageHandOverCarUser;
    @Column(name = "completed_date")
    private LocalDateTime completedDate;
    @Column(name = "completed_user")
    private String completedUser;
    @Column(name = "canceled_date")
    private LocalDateTime canceledDate;
    @Column(name = "canceled_user")
    private String canceledUser;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "is_payment")
    private Boolean isPayment;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "description")
    private String description;
    @Column(name = "expected_hand_over_date")
    private LocalDateTime expectedHandOverDate;
    @Column(name = "expected_hand_over_time")
    private LocalTime expectedHandOverTime;
    @OneToMany(mappedBy = "serviceTicket")
    private List<ServiceBookingMedia> serviceTicketServiceBookingMedias;

    @OneToMany(mappedBy = "serviceTicket")
    private List<ServiceCarStatus> serviceTicketServiceCarStatus;

    @OneToMany(mappedBy = "serviceTicket")
    private List<ServiceServices> serviceTicketServiceServices;


}
