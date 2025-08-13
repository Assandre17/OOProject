package model;


import controller.Controller;
import gui.HomePartecipante;

import javax.swing.*;
import java.util.List;

public class Partecipante extends Utente {

    private List<Team> team;
    public Partecipante() {super();}

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

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }
}