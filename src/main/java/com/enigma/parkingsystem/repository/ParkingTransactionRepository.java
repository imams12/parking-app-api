package com.enigma.parkingsystem.repository;

import com.enigma.parkingsystem.model.entity.ParkingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction, String> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE t_transaction SET exit_time = :exitTime, total_fee = :totalFee WHERE id = :id")
    void completeTransaction(@Param("id") String id, @Param("exitTime") LocalTime exitTime, @Param("totalFee") double totalFee);
}