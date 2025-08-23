package implementazionePostgresDAO;

import dao.InvitoDAO;
import database.ConnessioneDatabase;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.Utils.*;

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
    public void insertInvito(Long idPartecipante, Long idTeam, String figuraInserimento){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO inviti (id_partecipante,id_team,stato,figura_inserimento) VALUES(?,?,?,?)");
            ps.setLong(1, idPartecipante);
            ps.setLong(2,idTeam);
            ps.setString(3,STATO_IN_ATTESA);
            ps.setString(4,figuraInserimento);

            ps.executeUpdate();

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Invito> getInvitiPartecipante(Partecipante partecipante) {
        List<Invito> invitiList = new ArrayList<>();

        String sql =
                "SELECT i.id AS invito_id, i.id_partecipante, i.id_team, i.stato, " +
                        "t.nome AS nome_team, t.id_hackathon AS id_hackathon, h.nome AS nome_hackathon, u.email " +
                        "FROM inviti i " +
                        "JOIN teams t ON i.id_team=t.id " +
                        "JOIN hackathon h ON t.id_hackathon=h.id " +
                        "JOIN users u ON i.id_partecipante=u.id " +
                        "WHERE (i.id_partecipante=? AND i.figura_inserimento='TEAM' AND i.stato = 'IN ATTESA') " +
                        "OR (i.figura_inserimento='PARTECIPANTE' " +
                        "AND i.stato = 'IN ATTESA' AND i.id_team IN(SELECT pt.id_team FROM partecipante_team pt WHERE pt.id_partecipante=?) " +
                        "AND NOT EXISTS(SELECT 1 FROM partecipante_team pt2 " +
                        "JOIN teams t2 ON t2.id=pt2.id_team " +
                        "WHERE pt2.id_partecipante=i.id_partecipante AND t2.id_hackathon=t.id_hackathon))";



        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, partecipante.getId());
            ps.setLong(2, partecipante.getId());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long invitoId = rs.getLong("invito_id");
                    Long idPartecipante = rs.getLong("id_partecipante");
                    Long idTeam = rs.getLong("id_team");
                    Long idHackathon = rs.getLong(("id_hackathon"));
                    String stato = rs.getString("stato");
                    String nomeTeam = rs.getString("nome_team");
                    String nomeHackathon = rs.getString("nome_hackathon");
                    String emailPartecipante = rs.getString("email");

                    Hackathon hackathon = new Hackathon();
                    hackathon.setNome(nomeHackathon);
                    hackathon.setId(idHackathon);

                    Team team = new Team(idTeam, nomeTeam, null, null, hackathon);

                    // Utente contiene solo il team dell’invito
                    Utente utente = getUtenteModel(idPartecipante, null, null, emailPartecipante,
                            null, TIPO_PARTECIPANTE, Collections.singletonList(team));

                    invitiList.add(new Invito(invitoId, (Partecipante) utente, team, stato));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return invitiList;
    }

    @Override
    public void updateStatoInvito(Long idInvito, String statoInvito){
        try (PreparedStatement ps = connection.prepareStatement("UPDATE inviti SET stato = ? WHERE id = ?")) {
            ps.setString(1, statoInvito);
            ps.setLong(2, idInvito);
            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
