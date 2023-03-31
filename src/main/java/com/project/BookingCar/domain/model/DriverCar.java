package com.project.BookingCar.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "driver_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverCar extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chassis_number")
    private String chassisNumber;
    @Column(name = "engine_number")
    private String engineNumber;
    @Column(name = "license_plate")
    private String licensePlate;
    private String color;
    private String carImage;
    private String status;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
}
