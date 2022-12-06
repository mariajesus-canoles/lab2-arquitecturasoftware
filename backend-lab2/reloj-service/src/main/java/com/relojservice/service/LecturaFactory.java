package com.relojservice.service;

public abstract class LecturaFactory {
    public LecturaFactory(){
    }

    protected abstract Lectura crearLectura(String var);
}
