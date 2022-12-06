package com.relojservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relojservice.entity.Reloj;
import com.relojservice.repository.RelojRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelojService {
    @Autowired
    RelojRepository relojRepository;

    @Autowired
    RestTemplate restTemplate;

    public LocalTime buscarSalida(Long idPersonal, String fechaString){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaString, df);
        return relojRepository.buscarHoraSalida(idPersonal, fecha);
    }

    public Integer obtenerNumDiasDelMes(){
        return relojRepository.buscarNumDiasDelMes();
    }

    public ArrayList<Reloj> obtenerRelojesDePersonal(Long idPersonal){
        return relojRepository.buscarRelojesDePersonal(idPersonal);
    }

    public ArrayList<String> obtenerFechasDelMes(){
        return  relojRepository.buscarFechasDelMes();
    }



    public ArrayList<Reloj> cargarMarcaHoraria(MultipartFile archivo) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        ArrayList<ArrayList<String>> matrizArchivo = new ArrayList<ArrayList<String>>();
        if (!archivo.isEmpty()){
            try{
                String archivoString = new String(archivo.getBytes(), StandardCharsets.UTF_8);
                Factory factory = new Factory();
                Lectura lectura = factory.crearLectura(archivo.getContentType());
                matrizArchivo = lectura.LecturaArchivo(archivoString);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        ArrayList<Reloj> listaRelojes = new ArrayList<Reloj>();
        for (ArrayList<String> arrayArchivo: matrizArchivo){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate fecha = LocalDate.parse(arrayArchivo.get(0), df);
            LocalTime hora = LocalTime.parse(arrayArchivo.get(1));
            String rut = arrayArchivo.get(2);
            Long idPersonal = mapper.convertValue(restTemplate.getForObject("http://personal-service/personal/idpersonal/"+rut, Long.class), new TypeReference<Long>(){});
            ArrayList<Long> reloj = relojRepository.buscarRelojPorIdPersonalYFecha(idPersonal, fecha);
            if (reloj.isEmpty()){ //no existe reloj
                LocalTime fechaAux = LocalTime.now();
                relojRepository.ingresarQuery(fecha, hora, fechaAux, idPersonal);
            } else { //si existe reloj
                relojRepository.actualizarQuery(reloj.get(0), hora);
            }
        }
        return listaRelojes;
    }



}
