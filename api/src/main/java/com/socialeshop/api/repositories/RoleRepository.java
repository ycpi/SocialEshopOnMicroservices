package com.socialeshop.api.repositories;

import com.socialeshop.api.model.ERole;
import com.socialeshop.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
