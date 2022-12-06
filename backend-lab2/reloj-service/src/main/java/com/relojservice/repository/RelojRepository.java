package com.relojservice.repository;

import com.relojservice.entity.Reloj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface RelojRepository extends JpaRepository<Reloj, Long> {
    @Query(value = "SELECT reloj.hora_salida FROM reloj WHERE reloj.id_personal=:id_personal AND reloj.fecha=:fecha", nativeQuery = true)
    LocalTime buscarHoraSalida(@Param("id_personal") Long id_personal, @Param("fecha") LocalDate fecha);

    @Query(value = "SELECT COUNT (DISTINCT reloj.fecha) FROM reloj ", nativeQuery = true)
    Integer buscarNumDiasDelMes();

    @Query(value = "SELECT * FROM reloj WHERE reloj.id_personal=:id_personal", nativeQuery = true)
    ArrayList<Reloj> buscarRelojesDePersonal(@Param("id_personal") Long id_personal);

    @Query(value = "SELECT DISTINCT reloj.fecha FROM reloj", nativeQuery = true)
    ArrayList<String> buscarFechasDelMes();

    @Query(value = "SELECT reloj.id FROM reloj WHERE reloj.id_personal=:idPersonal AND reloj.fecha=:fecha", nativeQuery = true)
    ArrayList<Long> buscarRelojPorIdPersonalYFecha(@Param("idPersonal") Long idPersonal, @Param("fecha") LocalDate fecha);

    @Modifying
    @Query(value = "INSERT INTO public.reloj (fecha, hora_entrada, hora_salida, id_personal) VALUES (:fecha, :hora_entrada, :hora_salida, :id_personal)",
            nativeQuery = true)
    @Transactional
    void ingresarQuery(@Param("fecha") LocalDate fecha,
                       @Param("hora_entrada") LocalTime hora_entrada,
                       @Param("hora_salida") LocalTime hora_salida,
                       @Param("id_personal") Long id_personal);

    @Modifying
    @Query(value = "UPDATE public.reloj SET hora_salida=:hora_salida WHERE reloj.id=:id",
            nativeQuery = true)
    @Transactional
    void actualizarQuery(@Param("id") Long id,
                         @Param("hora_salida") LocalTime hora_salida);
}
