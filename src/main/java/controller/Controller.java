package main.java.controller;
import javax.swing.*;
import java.nio.file.Path;
import java.util.*;
import main.java.model.*;

public class Controller {
    private String nomeTeam;

    public Hackathon creaHackathon(){return new Hackathon();}
    public void invitaGiudice(Giudice giudice){}
    public void apriRegistrazioni(){}
    public void registrati(){}
    public Utente accedi(String email, String password){

        System.out.println("accesso in corso...");

        //TODO: nel 3o homework si dovrà prendere l'entita dal DB e ritornarla. Al momento il ritorno è mockato con Giudice.


        return new Partecipante();
    }
    public void iscriviti(){}
    public void creaTeam(String nome, List<Partecipante> listaPartecipanti){
        Team team = new Team();
        team.setPartecipanti(listaPartecipanti);
        team.setNome(nome);

        //TODO: aggiunta del nuovo team a DB

        invitaPartecipanti(listaPartecipanti,team);


    }
    public void invitaPartecipanti(List<Partecipante> partecipanti, Team team){
        System.out.println("invitaPartecipanti in corso...");
        partecipanti.forEach(partecipante -> {
            Invito invito = new Invito();
            invito.setTeam(team);
            invito.setPartecipante(partecipante);

            //TODO: salvataggio a DB dell'invito

        });



    }
    public void richiestaIngressoTeam(){}
    public boolean accettaIngressoTeam(){return true;}
    public boolean accettaInvitoTeam(){return true;}
    public String pubblicaProblema(){return "";}
    public int assegnaVoto(){return 0;}
    public Documento pubblicaDocumento(Documento documento){return null;}
    public void stampaClassifica(HashMap<String,Integer> classifica){}
    /*classifica l'ho pensata come una Map cosi che si possa
    facilmente ordinare in base al voto assegnato dal giudice*/


    public String getNomeTeam() {
        return nomeTeam;
    }

    public void setNomeTeam(String nomeTeam) {
        this.nomeTeam = nomeTeam;
    }
}