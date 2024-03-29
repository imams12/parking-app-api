package com.enigma.parkingsystem.model.entity;

import com.enigma.parkingsystem.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Table(name = DbPath.ADMIN)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "phone_number", nullable = false, unique = true,length = 50)
    private String mobilePhone;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
