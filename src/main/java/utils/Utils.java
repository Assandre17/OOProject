package utils;

import model.*;
import org.apache.commons.validator.routines.EmailValidator;

public class Utils {

    public static final String[] COLONNE_LISTA_PARTECIPANTI = {"","ID", "Nome", "Cognome"};
    public static final String[] COLONNE_LISTA_INVITI = {"ID", "Nome Team", "Email partecipante", "Nome Hackathon"};
    public static final String[] COLONNE_LISTA_TEAM = {"ID", "Nome Team"};
    public static final String[] COLONNE_LISTA_HACKATHON = {"ID", "Nome Hackathon", "Descrizione"};

    public static final String TIPO_GIUDICE = "GIUDICE";
    public static final String TIPO_ORGANIZZATORE = "ORGANIZZATORE";
    public static final String TIPO_PARTECIPANTE = "PARTECIPANTE";

    public static final String STATO_IN_ATTESA = "IN ATTESA";
    public static final String STATO_ACCETTATO = "ACCETTATO";
    public static final String STATO_RIFIUTATO = "RIFIUTATO";




    public static String getTipo(Utente utente) {
        if (utente instanceof Organizzatore) {
            return TIPO_ORGANIZZATORE;
        } else if (utente instanceof Partecipante) {
            return TIPO_PARTECIPANTE;
        }

        return TIPO_GIUDICE;

    }

    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);


    }

    public static Utente getUtenteModel(Long id, String nome, String cognome, String email, String password, String tipo, Team team) {
        return switch (tipo.toUpperCase()) {
            case TIPO_ORGANIZZATORE -> new Organizzatore(id, nome, cognome, email, password);
            case TIPO_GIUDICE -> new Giudice(id, nome, cognome, email, password);
            case TIPO_PARTECIPANTE-> new Partecipante(id, nome, cognome, email, password,team);
            default -> null;
        };
    }
}
