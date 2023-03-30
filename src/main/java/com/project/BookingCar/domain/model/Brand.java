package com.project.BookingCar.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Car car;
}
