package dao;

import model.Utente;

public interface UtenteDAO {

    void insertUtente(Utente utente);
    Utente getUtenteByEmailAndPassword(String email, String password);
}
