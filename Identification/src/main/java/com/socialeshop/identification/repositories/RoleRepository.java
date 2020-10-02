package com.socialeshop.identification.repositories;

import com.socialeshop.identification.models.ERole;
import com.socialeshop.identification.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
