package implementazionePostgresDAO;

import dao.DocumentoDAO;
import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DocumentoImplementazionePostgresDAO implements DocumentoDAO {

    private Connection connection;

    public DocumentoImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDocumento(String descrizione, String versione, LocalDate dataPubblicazione, Long idTeam) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO documenti (descrizione,versione,data_invio, id_team) VALUES(?,?,?,?)")) {
            ps.setString(1, descrizione);
            ps.setString(2, versione);
            ps.setDate(3, Date.valueOf(dataPubblicazione));
            ps.setLong(4, idTeam);

            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
