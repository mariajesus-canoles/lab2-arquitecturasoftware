package com.justificativoservice.repository;

import com.justificativoservice.entity.Justificativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;

public interface JustificativoRepository extends JpaRepository<Justificativo, Long> {

    @Query(value = "SELECT * FROM justificativo WHERE justificativo.id_personal=:id", nativeQuery = true)
    ArrayList<Justificativo> buscarJustificativosDePersonal(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO public.justificativo (fecha, id_personal) VALUES (:fecha, :id_personal)",
            nativeQuery = true)
    @Transactional
    void ingresarQuery(@Param("fecha") LocalDate fecha,
                       @Param("id_personal") Long id_personal);
}
