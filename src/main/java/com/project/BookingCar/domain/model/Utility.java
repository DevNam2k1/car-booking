package com.project.BookingCar.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "utility")
public class Utility extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code")
    private String code;
}
