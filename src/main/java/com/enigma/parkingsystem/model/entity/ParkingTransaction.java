package com.enigma.parkingsystem.model.entity;

import com.enigma.parkingsystem.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@Table(name = DbPath.TRANSACTION)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot parkingSpot;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double totalFee;
}
