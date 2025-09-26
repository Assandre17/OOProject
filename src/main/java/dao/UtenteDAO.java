package dao;

import model.Utente;

/**
 * The interface Utente dao.
 */
public interface UtenteDAO {

    /**
     * Insert utente.
     *
     * @param utente the utente
     */
    void insertUtente(Utente utente);

    /**
     * Gets utente by email and password.
     * utile per l'accesso.
     * @param email    the email
     * @param password the password
     * @return the utente by email and password
     */
    Utente getUtenteByEmailAndPassword(String email, String password);

    /**
     * Gets utente by email.
     *
     * @param email the email
     * @return the utente by email
     */
//serve per evitare di far registrare un utente con un'email già esistente
    Utente getUtenteByEmail(String email);
}
