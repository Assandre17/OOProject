package main.java;

import main.java.model.Giudice;
import main.java.model.Partecipante;
import main.java.model.Utente;

public class Main {
    public static void main(String[] args) {
        Utente partecipante1 = new Partecipante();
        partecipante1.setNome("Pippo");
        partecipante1.setCognome("Pluto");

        System.out.println(partecipante1.getNome());
        System.out.println(partecipante1.getCognome());


        Utente giudice1 = new Giudice("Mario", "Rossi");

        System.out.println(giudice1.getNome());
        System.out.println(giudice1.getCognome());


    }
}