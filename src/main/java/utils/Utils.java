package utils;

import model.Organizzatore;
import model.Partecipante;
import model.Utente;

public class Utils {

    public static final String[] COLONNE_LISTA_PARTECIPANTI = {"","ID", "Nome", "Cognome"};
    public static final String[] COLONNE_LISTA_INVITI = {"ID", "Nome Team", "Email partecipante", "Nome Hackathon"};
    public static final String[] COLONNE_LISTA_TEAM = {"ID", "Nome Team"};
    public static final String[] COLONNE_LISTA_HACKATHON = {"ID", "Nome Hackathon", "Descrizione"};

    public static final String TIPO_GIUDICE = "GIUDICE";
    public static final String TIPO_ORGANIZZATORE = "ORGANIZZATORE";
    public static final String TIPO_PARTECIPANTE = "PARTECIPANTE";

    public static String getTipo(Utente utente) {
        if (utente instanceof Organizzatore) {
            return TIPO_ORGANIZZATORE;
        } else if (utente instanceof Partecipante) {
            return TIPO_PARTECIPANTE;
        }

        return TIPO_GIUDICE;

    }
}
