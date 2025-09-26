package model;


import controller.Controller;
import gui.HomeOrganizzatore;

import javax.swing.*;

/**
 * The type Organizzatore.
 */
public class Organizzatore extends Utente {
    /**
     * Instantiates a new Organizzatore.
     */
    public Organizzatore() {super();}

    /**
     * Instantiates a new Organizzatore.
     *
     * @param id       the id
     * @param nome     the nome
     * @param cognome  the cognome
     * @param email    the email
     * @param password the password
     */
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
