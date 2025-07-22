package model;


import controller.Controller;
import gui.HomeOrganizzatore;

import javax.swing.*;

public class Organizzatore extends Utente {
    public Organizzatore() {super();}

    public Organizzatore(Long id, String nome, String cognome, String email, String password) {
        super(id, nome, cognome, email, password);
    }

    @Override
    public void apriFinestra(JFrame frameChiamante, Controller controller){
        HomeOrganizzatore homePartecipante = new HomeOrganizzatore(controller);
        homePartecipante.homeOrganizzatoreFrame.setVisible(true);
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }
}
