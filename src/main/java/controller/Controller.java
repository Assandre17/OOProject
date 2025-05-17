package main.java.controller;
import javax.swing.*;
import java.util.*;
import main.java.model.*;

public class Controller {
    public Hackathon creaHackathon(){return new Hackathon();}
    public void invitaGiudice(Giudice giudice){}
    public void apriRegistrazioni(){}
    public void registrati(){}
    public void accedi(){}
    public void iscriviti(){}
    public void creaTeam(){}
    public void invitaPartecipanti(Partecipante partecipante){}
    public void richiestaIngressoTeam(){}
    public boolean accettaIngressoTeam(){return true;}
    public boolean accettaInvitoTeam(){return true;}
    public String pubblicaProblema(){return "";}
    public int assegnaVoto(){return 0;}
    public Documento pubblicaDocumento(Documento documento){return null;}
    public void stampaClassifica(HashMap<String,Integer> classifica){}
    /*classifica l'ho pensata come una Map cosi che si possa
    facilmente ordinare in base al voto assegnato dal giudice*/
}