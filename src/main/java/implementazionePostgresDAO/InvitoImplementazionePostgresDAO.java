package implementazionePostgresDAO;

import dao.InvitoDAO;
import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static utils.Utils.STATO_IN_ATTESA;

public class InvitoImplementazionePostgresDAO implements InvitoDAO {
    private Connection connection;

    public InvitoImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertInvito(Long idPartecipante, Long idTeam){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO inviti (id_partecipante,id_team,stato) VALUES(?,?,?)");
            ps.setLong(1, idPartecipante);
            ps.setLong(2,idTeam);
            ps.setString(3,STATO_IN_ATTESA);

            ps.executeUpdate();

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
