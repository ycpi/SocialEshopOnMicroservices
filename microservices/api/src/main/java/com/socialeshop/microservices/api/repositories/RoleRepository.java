package com.socialeshop.microservices.api.repositories;

import com.socialeshop.microservices.api.model.ERole;
import com.socialeshop.microservices.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
