package com.project.BookingCar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.BookingCar.domain.enums.ServiceMediaImageType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "service_booking_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceBookingMedia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url",nullable = false, columnDefinition = "longtext")
    private String imageUrl;

    @Column(name = "image_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceMediaImageType imageType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_ticket_id", nullable = false)
    private ServiceTicket serviceTicket;

}

