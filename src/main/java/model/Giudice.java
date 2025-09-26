package model;


import controller.Controller;
import gui.HomeGiudice;

import javax.swing.*;
import java.util.List;

/**
 * The type Giudice.
 */
public class Giudice extends Utente {

    private List<Hackathon> hackathon;

    /**
     * Instantiates a new Giudice.
     */
    public Giudice() {
        super();
    }

    /**
     * Instantiates a new Giudice.
     *
     * @param id        the id
     * @param nome      the nome
     * @param cognome   the cognome
     * @param email     the email
     * @param password  the password
     * @param hackathon the hackathon
     */
    public Giudice(Long id, String nome, String cognome, String email, String password, List<Hackathon> hackathon) {
        super(id, nome, cognome, email, password);
        this.hackathon = hackathon;
    }

    @Override
    public void apriFinestra(JFrame frameChiamante, Controller controller){
        HomeGiudice homePartecipante = new HomeGiudice(controller);
        homePartecipante.homeGiudiceFrame.setVisible(true);
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }

    /**
     * Gets hackathon.
     *
     * @return the hackathon
     */
    public List<Hackathon> getHackathon() {return hackathon;}

    /**
     * Sets hackathon.
     *
     * @param hackathon the hackathon
     */
    public void setHackathon(List<Hackathon> hackathon) {this.hackathon = hackathon;}
}
