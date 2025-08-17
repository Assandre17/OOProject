package implementazionePostgresDAO;

import dao.HackathonDAO;
import database.ConnessioneDatabase;
import model.Hackathon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class HackathonImplementazionePostgresDAO implements HackathonDAO {

    private Connection connection;

    public HackathonImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNumPartecipante(Long idHackathon){
        try (PreparedStatement ps = connection.prepareStatement("UPDATE hackathon SET num_partecipanti = num_partecipanti + 1 WHERE id = ?")) {
            ps.setLong(1, idHackathon);
            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
