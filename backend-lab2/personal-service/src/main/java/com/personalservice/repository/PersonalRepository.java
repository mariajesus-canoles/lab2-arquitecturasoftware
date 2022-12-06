package com.personalservice.repository;

import com.personalservice.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {

    @Query(value = "SELECT personal.fecha_ingreso FROM personal WHERE personal.id=:id", nativeQuery = true)
    LocalDate buscarFechaIngreso(@Param("id") Long id);

    @Query(value = "SELECT personal.id FROM personal WHERE personal.rut=:rut", nativeQuery = true)
    Long buscarIdPersonalPorRut(@Param("rut") String rut);
}
