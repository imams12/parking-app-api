package com.enigma.parkingsystem.model.entity;

import com.enigma.parkingsystem.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@Table(name = DbPath.PARKING_LOT)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String areaName;
    private Integer capacity;
    private Double hourlyRate;
    @OneToMany(mappedBy = "parkingLot")
    private List<ParkingSpot> parkingSpots;
}
