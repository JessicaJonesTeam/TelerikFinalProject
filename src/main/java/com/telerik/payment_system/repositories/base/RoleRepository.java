package com.telerik.payment_system.repositories.base;

import com.telerik.payment_system.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
}
