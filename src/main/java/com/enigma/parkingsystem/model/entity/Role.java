package com.enigma.parkingsystem.model.entity;

import com.enigma.parkingsystem.constant.DbPath;
import com.enigma.parkingsystem.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Table(name = DbPath.ROLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole name;
}
