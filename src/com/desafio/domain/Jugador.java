package com.desafio.domain;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private int pote;

    public Jugador(String nombre,int pote) {
        this.nombre = nombre;
        this.pote = pote;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPote() {
        return pote;
    }

    public void setPote(int pote) {
        this.pote = pote;
    }

    public int sumar(int sumaV){
        int sumarValor=getPote();
        sumarValor=sumarValor+sumaV;
        return sumarValor;
    }
    public int resta(int restaV){
        int restaValor=getPote();
        restaValor= restaValor-restaV;
        return restaValor;
    }




}