package model;


import controller.Controller;
import gui.HomePartecipante;

import javax.swing.*;

public class Partecipante extends Utente {

    private Team team;
    public Partecipante() {super();}

    public Partecipante(Long id, String nome, String cognome, String email, String password, Team team) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}