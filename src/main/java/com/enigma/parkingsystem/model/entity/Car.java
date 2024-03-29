package com.enigma.parkingsystem.model.entity;

import com.enigma.parkingsystem.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DbPath.CAR)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String licensePlate;
    private String color;
    private String brand;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}