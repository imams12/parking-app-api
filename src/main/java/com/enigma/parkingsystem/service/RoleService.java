package com.enigma.parkingsystem.service;

import com.enigma.parkingsystem.constant.ERole;
import com.enigma.parkingsystem.model.entity.Role;

public interface RoleService {

    Role getOrSave(ERole role);
}
