package com.project.BookingCar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.BookingCar.domain.enums.RequestMediaImageType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "request_booking_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestBookingMedia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url",nullable = false, columnDefinition = "longtext")
    private String imageUrl;

    @Column(name = "image_type",nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private RequestMediaImageType imageType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_ticket_id", nullable = false)
    private RequestTicket requestTicket;

}
