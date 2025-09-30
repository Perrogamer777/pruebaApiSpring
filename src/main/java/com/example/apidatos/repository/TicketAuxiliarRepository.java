package com.example.apidatos.repository;

import com.example.apidatos.entities.TicketAuxiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketAuxiliarRepository extends JpaRepository<TicketAuxiliar, Long> {
}
