package com.project.BookingCar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "service_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceServices implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ref_id",nullable = false)
    private Long refId;

    @Column(name = "name",nullable = false, length = 40)
    private String name;

    @Column(name = "type",length = 200)
    private String type;

    @Column(name = "price")
    private Double price;

    @Column(name = "warranty_time")
    private LocalDateTime warrantyTime;

    @Column(name = "details")
    private String details;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_ticket_id")
    private ServiceTicket serviceTicket;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    @JsonIgnore
    private ServiceServices parentService;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "parentService")
    private List<ServiceServices> children;
}
