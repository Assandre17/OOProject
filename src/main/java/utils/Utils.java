package utils;

import model.*;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * The constant COLONNE_LISTA_PARTECIPANTI.
     */
    public static final String[] COLONNE_LISTA_PARTECIPANTI = {"","ID", "Nome", "Cognome"};
    /**
     * The constant COLONNE_LISTA_INVITI.
     */
    public static final String[] COLONNE_LISTA_INVITI = {"ID", "Nome Team", "Partecipante Invitato", "Nome Hackathon"};
    /**
     * The constant COLONNE_LISTA_INVITI_GIUDICE.
     */
    public static final String[] COLONNE_LISTA_INVITI_GIUDICE = {"ID Invito", "Nome Hackathon"};
    /**
     * The constant COLONNE_CLASSIFICA.
     */
    public static final String[] COLONNE_CLASSIFICA = {"ID", "Nome Team", "Media Voto"};

    /**
     * The constant COLONNE_LISTA_TEAM.
     */
    public static final String[] COLONNE_LISTA_TEAM = {"ID", "Nome Team"};
    /**
     * The constant COLONNE_LISTA_HACKATHON.
     */
    public static final String[] COLONNE_LISTA_HACKATHON = {"ID", "Nome Hackathon", "Descrizione"};
    /**
     * The constant COLONNE_LISTA_DOCUMENTI.
     */
    public static final String[] COLONNE_LISTA_DOCUMENTI = {"ID", "Nome Team", "Versione Documento"};

    /**
     * The constant TIPO_GIUDICE.
     */
    public static final String TIPO_GIUDICE = "GIUDICE";
    /**
     * The constant TIPO_ORGANIZZATORE.
     */
    public static final String TIPO_ORGANIZZATORE = "ORGANIZZATORE";
    /**
     * The constant TIPO_PARTECIPANTE.
     */
    public static final String TIPO_PARTECIPANTE = "PARTECIPANTE";
    /**
     * The constant TIPO_TEAM.
     */
    public static final String TIPO_TEAM = "TEAM";

    /**
     * The constant STATO_IN_ATTESA.
     */
    public static final String STATO_IN_ATTESA = "IN ATTESA";
    /**
     * The constant STATO_ACCETTATO.
     */
    public static final String STATO_ACCETTATO = "ACCETTATO";
    /**
     * The constant STATO_RIFIUTATO.
     */
    public static final String STATO_RIFIUTATO = "RIFIUTATO";


    /**
     * Gets tipo.
     *
     * @param utente the utente
     * @return the tipo
     */
    public static String getTipo(Utente utente) {
        if (utente instanceof Organizzatore) {
            return TIPO_ORGANIZZATORE;
        } else if (utente instanceof Partecipante) {
            return TIPO_PARTECIPANTE;
        }

        return TIPO_GIUDICE;

    }

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);


    }

    /**
     * Gets utente model.
     *
     * @param id        the id
     * @param nome      the nome
     * @param cognome   the cognome
     * @param email     the email
     * @param password  the password
     * @param tipo      the tipo
     * @param team      the team
     * @param hackathon the hackathon
     * @return the utente model
     */
    public static Utente getUtenteModel(Long id, String nome, String cognome, String email, String password, String tipo, List<Team> team, List<Hackathon> hackathon) {
        return switch (tipo.toUpperCase()) {
            case TIPO_ORGANIZZATORE -> new Organizzatore(id, nome, cognome, email, password);
            case TIPO_GIUDICE -> new Giudice(id, nome, cognome, email, password, hackathon);
            case TIPO_PARTECIPANTE-> new Partecipante(id, nome, cognome, email, password,team);
            default -> null;
        };
    }
}
