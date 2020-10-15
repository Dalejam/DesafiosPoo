package com.desafio.App;

import com.desafio.domain.Guayabita;
import com.desafio.domain.Jugador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class App {
    public static final int JUGAR=0;
    public static final int INSTRUCCIONES=1;
    public static void main(String[] args) {
        Guayabita guayabita=new Guayabita();
        Icon icono=new ImageIcon(App.class.getResource("guayaba.png"));
        boolean iniciar=true;
        ArrayList<Jugador>jugador=new ArrayList<>();
        ArrayList<Jugador>resultadosJugadores=new ArrayList<>();
        boolean continuar=true;

        while (iniciar==true){
            int opcion = JOptionPane.showOptionDialog(null,"BIENVENIDO AL JUEGO DE GUAYABITA","GUAYABITA",0,0,
                    icono,Arrays.asList("JUGAR","INSTRUCCIONES").toArray(),null);
            switch (opcion){
                case JUGAR:
                    int poteInicial =Integer.parseInt( JOptionPane.showInputDialog("Ingrese Valor de pote para iniciar juego"));
                    //JOptionPane.showMessageDialog(null,"El valor del pote incial para jugar es: "+guayabita.getPoteJ(),"GUAYABITA",JOptionPane.INFORMATION_MESSAGE);
                    int participantes = Integer.parseInt( JOptionPane.showInputDialog("Ingrese cantidad de Jugadores\n"));
                    for(int i=0;i<participantes;i++){
                        String nombre= JOptionPane.showInputDialog("Ingrese Nombre de participante"+(i+1));
                        int pote= Integer.parseInt(JOptionPane.showInputDialog("Ingrese valor de pote de participante "+(i+1)));
                        pote=pote-poteInicial;
                        Jugador guardarJugador = new Jugador(nombre,pote);
                        jugador.add(guardarJugador);
                        guardarJugador=null;
                    }
                    guayabita.setPoteJ(poteInicial*participantes);
                    JOptionPane.showMessageDialog(null,"El juego inicia con un pote de: "+poteInicial*participantes);

                    while (guayabita.getPoteJ()>0&&continuar==true){
                        for (int j=0; j < jugador.size(); j++) {
                            int lanzarDado = JOptionPane.showConfirmDialog(null, jugador.get(j).getNombre() + "¿Desea Tirar Dados?", "GUAYABITA", JOptionPane.YES_NO_OPTION);
                            if (lanzarDado == 0) {
                                int dado = guayabita.tirarDados();
                                Icon iconoDados = new ImageIcon(App.class.getResource("dado " + dado + ".png"));
                                if (dado == 1 || dado == 6) {
                                    JOptionPane.showOptionDialog(null, jugador.get(j).getNombre() + "Lo siento!!! \n Sede el turno" +
                                            "\n se descontara 50 de su pote como multa ", "GUAYABITA", 0, 0, iconoDados, Arrays.asList("Ok").toArray(), null);
                                    jugador.get(j).setPote(jugador.get(j).resta(50));
                                    guayabita.sumarPote(50);
                                    if(jugador.get(j).getPote()<=0)
                                    {
                                        resultadosJugadores.add(jugador.get(j));
                                        jugador.remove(jugador.get(j));
                                        if(jugador.size()==0){
                                            continuar=false;
                                        }
                                    }
                                } else {
                                    int apostar = JOptionPane.showConfirmDialog(null, jugador.get(j).getNombre() + "¿Desea Apostar?\n" + "El pote tiene el valor de: " + guayabita.getPoteJ(), "GUAYABITA", JOptionPane.YES_NO_OPTION, 0, iconoDados);
                                    if (apostar == 0) {
                                        int apuesta = Integer.parseInt(JOptionPane.showInputDialog(jugador.get(j).getNombre() + "¿Cuanto desea apostar?\n recuerde que el pote esta en " + guayabita.getPoteJ() + "\n su saldo es: " + jugador.get(j).getPote()));
                                        int dadoDos = guayabita.tirarDados();
                                        Icon iconoDadoDos = new ImageIcon(App.class.getResource("dado " + dadoDos + ".png"));
                                        JOptionPane.showOptionDialog(null, jugador.get(j).getNombre() + " Su dado es ", "GUAYABITA", 0, 0,
                                                iconoDadoDos, Arrays.asList("Ok").toArray(), null);
                                        if (dado >= dadoDos) {
                                            JOptionPane.showMessageDialog(null, "Lo sentimos acaba de perder " + apuesta);
                                            jugador.get(j).setPote(jugador.get(j).resta(apuesta));
                                            guayabita.sumarPote(apuesta);
                                            if(jugador.get(j).getPote()==0){
                                                resultadosJugadores.add(jugador.get(j));
                                                jugador.remove(jugador.get(j));
                                                if(jugador.size()==0){
                                                    continuar=false;
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Felicitaciones acaba de Ganar " + apuesta);
                                            jugador.get(j).setPote(jugador.get(j).sumar(apuesta));
                                            guayabita.restarPote(apuesta);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(continuar==false){
                        resultadosJugadores.forEach(resultado->{
                            JOptionPane.showMessageDialog(null,resultado.getNombre()+" Su resultado final es: "+resultado.getPote());
                        });
                    }
                    else{
                        jugador.forEach(resultado->{
                            JOptionPane.showMessageDialog(null,resultado.getNombre()+" Su resultado final es: "+resultado.getPote());
                        });
                    }
                    break;
                case INSTRUCCIONES:
                    String instruccinesReglas="INSTRUCCIONES:\n 1.Debe ingresar cantidad de participates al juego \n " +
                            "2.El juego solicitara nombre y valor de pote con el que ingresa al juego \n" +
                            "3.Se debe tirar los datos una vez y de pendiendo del valor del dado se podra o no hacer apuesta\n" +
                            "4.Si desea hacer la apuesta debe tener en cuenta el valor de pote que tiene el juego e ingresar el valor a apostar\n" +
                            "5.El juego termina cuando el pote del juego este en cero o los jugadores ya no tengan que apostar\n" +
                            "\n REGLAS:\n 1.Cada jugador debe aportar el valor correspondiente al pote inicial\n" +
                            "2.Si los dados sale 1 o 6 debe seder el turno\n" +
                            "3.Si los dados sale entre 2 o 5 puede apostar\n" +
                            "4.Solo se puede apostar valor inferior o igual al pote del juego\n";
                    mostarInstrucciones(instruccinesReglas);
                    break;
                default:
                    iniciar=false;
            }


        }


    }
    public static void mostarInstrucciones(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,"INSTRUCCIONES DE GUAYABITA",
                JOptionPane.PLAIN_MESSAGE);

    }
}