package com.project.BookingCar.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "origin")
    private String origin;
    @OneToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToOne
    @JoinColumn(name = "body_style_id")
    private BodyStyle bodyStyle;
    @OneToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DriverCar> driverCars;
}
