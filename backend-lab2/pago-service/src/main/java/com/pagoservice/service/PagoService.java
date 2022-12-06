package com.pagoservice.service;

import com.pagoservice.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    @Autowired
    PagoRepository pagoRepository;

    public Integer obtenerSueldoFijoMensual(Long personalIdCategoria, Long personalIdArea) {
        return pagoRepository.buscarSueldoFijoMensual(personalIdCategoria, personalIdArea);
    }

    public Integer obtenerValorHoraExtra(Long personalIdCategoria, Long personalIdArea) {
        return pagoRepository.buscarValorHoraExtra(personalIdCategoria, personalIdArea);
    }



}
