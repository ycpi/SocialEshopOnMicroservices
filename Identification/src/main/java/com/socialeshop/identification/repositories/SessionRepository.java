package com.socialeshop.identification.repositories;


import com.socialeshop.identification.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
