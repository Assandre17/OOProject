package main.java.model;

import main.java.gui.HomeOrganizzatore;
import main.java.gui.HomePartecipante;

import javax.swing.*;

public class Organizzatore extends Utente {
    public Organizzatore() {super();}

    public Organizzatore(String nome, String cognome) {
        super(nome, cognome);
    }

    @Override
    public void apriFinestra(JFrame frameChiamante){
        HomeOrganizzatore homePartecipante = new HomeOrganizzatore();
        homePartecipante.homeOrganizzatoreFrame.setVisible(true);
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }
}
