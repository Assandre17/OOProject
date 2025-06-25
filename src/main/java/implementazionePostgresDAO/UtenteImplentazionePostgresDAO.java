package implementazionePostgresDAO;

import dao.UtenteDAO;
import database.ConnessioneDatabase;
import model.Utente;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtenteImplentazionePostgresDAO implements UtenteDAO {
    private Connection connection;

    public UtenteImplentazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUtente(Utente utente){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (nome,cognome,email,password,tipo) VALUES(?,?,?,?,?)");
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getPassword());
            ps.setString(5, Utils.getTipo(utente));

            ps.executeUpdate();

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }



    }
}
