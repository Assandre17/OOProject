package model;


import controller.Controller;
import gui.HomePartecipante;

import javax.swing.*;
import java.util.List;

/**
 * The type Partecipante.
 */
public class Partecipante extends Utente {

    private List<Team> team;

    /**
     * Instantiates a new Partecipante.
     */
    public Partecipante() {super();}

    /**
     * Instantiates a new Partecipante.
     *
     * @param id       the id
     * @param nome     the nome
     * @param cognome  the cognome
     * @param email    the email
     * @param password the password
     * @param team     the team
     */
    public Partecipante(Long id, String nome, String cognome, String email, String password, List<Team> team) {
        super(id, nome, cognome, email, password);
        this.team = team;
    }

    @Override
    public void apriFinestra(JFrame frameChiamante, Controller controller){
        HomePartecipante homePartecipante = new HomePartecipante(controller);
        homePartecipante.homePartecipanteFrame.setVisible(true);
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    public List<Team> getTeam() {
        return team;
    }

    /**
     * Sets team.
     *
     * @param team the team
     */
    public void setTeam(List<Team> team) {
        this.team = team;
    }
}