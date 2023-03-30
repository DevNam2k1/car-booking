package com.project.BookingCar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "garage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String address;
    @Column(name = "lattitude")
    private Float latiTude;
    @Column(name = "longtitude")
    private Float longiTude;
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
