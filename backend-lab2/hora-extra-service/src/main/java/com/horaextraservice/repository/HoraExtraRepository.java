package com.horaextraservice.repository;

import com.horaextraservice.entity.HoraExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface HoraExtraRepository extends JpaRepository<HoraExtra, Long> {

    @Query(value = "SELECT * FROM hora_extra WHERE hora_extra.id_personal=:id", nativeQuery = true)
    ArrayList<HoraExtra> buscarHorasExtrasPorIdPersonal(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO public.hora_extra (fecha, num_horas, id_personal) VALUES (:fecha, :num_horas, :id_personal)",
            nativeQuery = true)
    @Transactional
    void ingresarQuery(@Param("fecha") LocalDate fecha,
                       @Param("num_horas") Integer num_horas,
                       @Param("id_personal") Long id_personal);
}
