package com.enigma.parkingsystem.model.entity;

import com.enigma.parkingsystem.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@Table(name = DbPath.PARKING_SPOT)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String spotNumber;
    private Boolean availability;
    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;
}
