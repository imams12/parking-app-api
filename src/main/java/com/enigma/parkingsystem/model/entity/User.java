package com.enigma.parkingsystem.model.entity;

import com.enigma.parkingsystem.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@Table(name = DbPath.USER)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
