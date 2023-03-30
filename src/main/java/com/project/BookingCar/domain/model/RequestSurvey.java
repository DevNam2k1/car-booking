package com.project.BookingCar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request_survey")
@Builder
public class RequestSurvey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ref_id")
    private Long refId;

    @Column(name = "name")
    private String name;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "max_price")
    private Double maxPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_ticket_id")
    private RequestTicket requestTicket;
}