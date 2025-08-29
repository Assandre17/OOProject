package model;


import controller.Controller;
import gui.HomeGiudice;

import javax.swing.*;
import java.util.List;

public class Giudice extends Utente {

    private List<Hackathon> hackathon;

    public Giudice() {
        super();
    }

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

    public List<Hackathon> getHackathon() {return hackathon;}

    public void setHackathon(List<Hackathon> hackathon) {this.hackathon = hackathon;}
}
