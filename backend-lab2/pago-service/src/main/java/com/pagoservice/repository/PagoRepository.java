package com.pagoservice.repository;


import com.pagoservice.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query(value = "SELECT pago.sueldo_fijo FROM pago WHERE pago.id_categoria=:personalIdCategoria AND pago.id_area=:personalIdArea", nativeQuery=true)
    Integer buscarSueldoFijoMensual(@Param("personalIdCategoria") Long personalIdCategoria, @Param("personalIdArea") Long personalIdArea);

    @Query(value = "SELECT pago.valor_hora_extra FROM pago WHERE pago.id_categoria=:personalIdCategoria AND pago.id_area=:personalIdArea", nativeQuery=true)
    Integer buscarValorHoraExtra(@Param("personalIdCategoria") Long personalIdCategoria, @Param("personalIdArea") Long personalIdArea);
}
