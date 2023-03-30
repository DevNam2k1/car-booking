package com.project.BookingCar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "request_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestServices implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ref_id",nullable = false)
    private Long refId;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "type",length = 200)
    private String type;

    @Column(name = "price")
    private Double price;

    @Column(name = "parent_id")
    private Long parentId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_ticket_id")
    private RequestTicket requestTicket;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    @JsonIgnore
    private RequestServices parentService;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "parentService")
    private List<RequestServices> children;


}
