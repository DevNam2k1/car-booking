package com.project.BookingCar.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "utility")
@Data
public class Utility extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code")
    private String code;
}
