package main.java.model;

import main.java.controller.Controller;
import main.java.gui.HomePartecipante;

import javax.swing.*;

public class Partecipante extends Utente {

    public Partecipante() {super();}

    public Partecipante(String nome, String cognome) {
        super(nome, cognome);
    }

    @Override
    public void apriFinestra(JFrame frameChiamante, Controller controller){
        HomePartecipante homePartecipante = new HomePartecipante(controller);
        homePartecipante.homePartecipanteFrame.setVisible(true);
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }
}