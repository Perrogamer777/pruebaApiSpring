package com.example.apidatos.repository;

import com.example.apidatos.entities.Licitaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface LicitacionesRepository extends JpaRepository<Licitaciones, Long> {
    @Query(value = "select * from licitaciones "
            + "where id_cliente = :idCliente", nativeQuery = true)
    List<Licitaciones> buscarPoridCliente(@Param("idCliente") Long idCliente);
}
