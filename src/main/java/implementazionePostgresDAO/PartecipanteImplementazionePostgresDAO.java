package implementazionePostgresDAO;

import dao.PartecipanteDAO;
import database.ConnessioneDatabase;
import model.Hackathon;
import model.Partecipante;
import org.apache.commons.collections.list.TreeList;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Partecipante implementazione postgres dao.
 */
public class PartecipanteImplementazionePostgresDAO implements PartecipanteDAO {
    private Connection connection;

    /**
     * Instantiates a new Partecipante implementazione postgres dao.
     */
    public PartecipanteImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Hackathon> getListHackathon(Partecipante partecipante) throws SQLException {
        //preparo la query
        Long id = partecipante.getId();
        PreparedStatement ps = connection.prepareStatement("SELECT ph.*, h.* FROM partecipante_hackathon ph " +
                "LEFT JOIN hackathon h ON ph.id_hackathon = h.id " +
                " WHERE id_partecipante = ?");
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
    public List<Partecipante> getPartecipantiWithoutTeam(Long idPartecipante, Long idHackathon) {
        List<Partecipante> partecipantiList = new TreeList();
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM users u WHERE tipo = 'PARTECIPANTE' AND id != ? AND NOT EXISTS (SELECT 1 FROM partecipante_team pt JOIN teams t ON pt.id_team = t.id WHERE pt.id_partecipante = u.id AND t.id_hackathon = ?)" )) {
            ps.setLong(1, idPartecipante);
            ps.setLong(2, idHackathon);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String tipo = rs.getString("tipo");
                partecipantiList.add((Partecipante) Utils.getUtenteModel(id,nome,cognome,email,password,tipo,null,null));
            }
            rs.close();
            return partecipantiList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTeamToPartecipante(Long idPartecipante, Long idTeam) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO partecipante_team (id_partecipante, id_team) VALUES(?,?)")) {
            ps.setLong(2, idTeam);
            ps.setLong(1, idPartecipante);
            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Hackathon> getHackathonLiberi(Partecipante partecipante) throws SQLException {
        //preparo la query
        Long id = partecipante.getId();
        PreparedStatement ps = connection.prepareStatement("SELECT h.* " +
                "FROM hackathon h " +
                "LEFT JOIN partecipante_hackathon ph " +
                "    ON h.id = ph.id_hackathon AND ph.id_partecipante = ? " +
                "WHERE ph.id_hackathon IS NULL " +
                "AND h.inizio_iscrizioni <= NOW() " +
                "  AND h.fine_iscrizioni >= NOW() " +
                "AND h.num_partecipanti < h.num_max_partecipanti ");
        //eseguire la query
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        List<Hackathon> list = new ArrayList<>();
        while (rs.next()) {
            Hackathon hackathon = new Hackathon();
            hackathon.setId(rs.getLong("id"));
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
    public void addPartecipanteToHackathon(Long idPartecipante, Long idHackathon) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO partecipante_hackathon (id_partecipante, id_hackathon) VALUES(?,?)")) {
            ps.setLong(2, idHackathon);
            ps.setLong(1, idPartecipante);
            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
