package model;


import controller.Controller;
import gui.HomeGiudice;

import javax.swing.*;

public class Giudice extends Utente {

    public Giudice() {
        super();
    }

    public Giudice(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password);
    }

    @Override
    public void apriFinestra(JFrame frameChiamante, Controller controller){
        HomeGiudice homePartecipante = new HomeGiudice(controller);
        homePartecipante.homeGiudiceFrame.setVisible(true);
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }

}
