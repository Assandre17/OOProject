package main.java.model;

import main.java.controller.Controller;
import main.java.gui.HomeGiudice;

import javax.swing.*;

public class Giudice extends Utente {

    public Giudice() {
        super();
    }

    public Giudice(String nome, String cognome) {
        super(nome, cognome);
    }

    @Override
    public void apriFinestra(JFrame frameChiamante, Controller controller){
        HomeGiudice homePartecipante = new HomeGiudice();
        homePartecipante.homeGiudiceFrame.setVisible(true);
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }

}
