package com.example.apidatos.repository;

import com.example.apidatos.entities.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Logs, Integer> {

    // Comprueba si existe al menos una fila
    Boolean existsByIdLicitacion(Long idLicitacion);
}
