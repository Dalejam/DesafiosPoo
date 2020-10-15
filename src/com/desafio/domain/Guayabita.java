package com.desafio.domain;

import java.util.ArrayList;
import java.util.List;

public class Guayabita {
    private List<Jugador> jugador;
    private  int poteJ;

    public Guayabita() {
        jugador=new ArrayList<>();
        this.poteJ = poteJ;
    }

    public List<Jugador> getJugador() {
        return jugador;
    }

    public int getPoteJ() {
        return poteJ;
    }

    public void setJugador(List<Jugador> jugador) {
        this.jugador = jugador;
    }

    public void setPoteJ(int poteJ) {
        this.poteJ = poteJ;
    }

    public int poteInicial(int participantes){
        return participantes*poteJ;
    }

    public void sumarPote(int valor){
        poteJ= poteJ+valor;
    }
    public void restarPote(int valor){
        poteJ=poteJ-valor;
    }
    public int tirarDados(){
        int numero= (int)(Math.random()*6)+1;
        return numero;
    }


}
