package implementazionePostgresDAO;

import dao.GiudiceDAO;
import database.ConnessioneDatabase;
import model.Giudice;
import model.Hackathon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Giudice implementazione postgres dao.
 */
public class GiudiceImplementazionePostgresDAO implements GiudiceDAO {
    private Connection connection;

    /**
     * Instantiates a new Giudice implementazione postgres dao.
     */
    public GiudiceImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Hackathon> getListHackathon(Giudice giudice) throws SQLException {
        //preparo la query
        Long id = giudice.getId();
        PreparedStatement ps = connection.prepareStatement("SELECT gh.*, h.* FROM giudice_hackathon gh " +
                "LEFT JOIN hackathon h ON gh.id_hackathon = h.id " +
                " WHERE id_giudice = ?");
        //eseguire la query
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        List<Hackathon> list = new ArrayList<>();
        while (rs.next()) {
            Hackathon hackathon = new Hackathon();
            hackathon.setId(rs.getLong("id_hackathon"));
            hackathon.setNome(rs.getString("nome"));
            hackathon.setSede(rs.getString("sede"));
            hackathon.setDataInizio(LocalDate.parse(rs.getString("data_inizio")));
            hackathon.setDataFine(LocalDate.parse(rs.getString("data_fine")));
            hackathon.setNumPartecipanti(rs.getInt("num_partecipanti"));
            hackathon.setNumMaxPartecipanti(rs.getInt("num_max_partecipanti"));
            hackathon.setDescrizione(rs.getString("descrizione"));
            hackathon.setInizioIscrizioni( rs.getDate("inizio_iscrizioni") != null ? rs.getDate("inizio_iscrizioni").toLocalDate() : null);
            hackathon.setFineIscrizioni( rs.getDate("fine_iscrizioni") != null ? rs.getDate("fine_iscrizioni").toLocalDate() : null);
            list.add(hackathon);
        }
        return list;
    }

    @Override
    public void addHackathonToGiudice(Long idGiudice, Long idHackathon) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO giudice_hackathon (id_giudice, id_hackathon) VALUES(?,?)")) {
                ps.setLong(2, idHackathon);
                ps.setLong(1, idGiudice);
                ps.executeUpdate();

                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

}
