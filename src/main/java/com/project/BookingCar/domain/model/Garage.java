package com.project.BookingCar.domain.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    private String status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(targetEntity = Utility.class)
    @JoinTable(name = "garage_provide_utility", joinColumns = {
            @JoinColumn(name = "garage_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "utility_id", referencedColumnName = "id") })
    private List<Utility> utilities;
}
