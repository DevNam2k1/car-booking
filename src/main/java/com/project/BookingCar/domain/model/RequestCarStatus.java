package com.project.BookingCar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "request_car_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCarStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_status_id",nullable = false)
    private Long carStatusId;

    @Column(nullable = false, length = 40)
    private String value;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_ticket_id", nullable = false)
    private RequestTicket requestTicket;
}
