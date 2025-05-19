package main.java.model;

import main.java.gui.HomePartecipante;

import javax.swing.*;

public class Partecipante extends Utente {

    public Partecipante() {super();}

    public Partecipante(String nome, String cognome) {
        super(nome, cognome);
    }

    @Override
    public void apriFinestra(JFrame frameChiamante){
        HomePartecipante homePartecipante = new HomePartecipante();
        homePartecipante.homePartecipanteFrame.setVisible(true);
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }
}