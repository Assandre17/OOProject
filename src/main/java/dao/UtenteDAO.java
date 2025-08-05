package dao;

import model.Utente;

public interface UtenteDAO {

    void insertUtente(Utente utente);
    Utente getUtenteByEmailAndPassword(String email, String password);

    //serve per evitare di far registrare un utente con un'email già esistente
    Utente getUtenteByEmail(String email);
}
