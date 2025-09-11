package implementazionePostgresDAO;

import dao.HackathonDAO;
import database.ConnessioneDatabase;
import model.Hackathon;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addProblemaToHackathon(String problema, Long idHackathon) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE hackathon SET descrizione = ? WHERE id = ?")) {
            ps.setString(1, problema);
            ps.setLong(2, idHackathon);
            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Hackathon getHackathonById(Long id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM hackathon " +
                " WHERE id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
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
           return hackathon;
        }
        return null;
    }
     @Override
    public void apriRegistrazioni(Long idHackathon, LocalDate inizioIscrizioni, LocalDate fineIscrizioni) throws SQLException {
     try (PreparedStatement ps = connection.prepareStatement("UPDATE hackathon SET inizio_iscrizioni = ?, fine_iscrizioni = ? WHERE id = ?")) {
            ps.setDate(1, Date.valueOf(inizioIscrizioni));
            ps.setDate(2, Date.valueOf(fineIscrizioni));
            ps.setLong(3, idHackathon);
            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Hackathon> getAllHackathon() {
        try (PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM hackathon ")) {

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
                hackathon.setInizioIscrizioni(rs.getDate("inizio_iscrizioni") != null ? rs.getDate("inizio_iscrizioni").toLocalDate() : null);
                hackathon.setFineIscrizioni(rs.getDate("fine_iscrizioni") != null ? rs.getDate("fine_iscrizioni").toLocalDate() : null);
                list.add(hackathon);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
