package implementazionePostgresDAO;

import dao.InvitoDAO;
import database.ConnessioneDatabase;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public List<Invito> getInvitiPartecipante(Partecipante partecipante){
        List<Invito> invitiList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("SELECT  i.id AS invito_id," +
                "    i.id_partecipante," +
                "    i.id_team," +
                "    i.stato, t.nome AS nome_team, u.email, h.nome AS nome_hackathon FROM inviti i " +
                "LEFT JOIN teams t ON i.id_team = t.id  " +
                "LEFT JOIN users u ON i.id_partecipante = u.id " +
                "LEFT JOIN hackathon h ON t.id_hackathon = h.id " +
                "WHERE (i.id_partecipante = ? OR i.id_team = ?) AND stato = ? AND u.id_team IS NULL;" )) {
            ps.setLong(1, partecipante.getId());
            ps.setLong(2, partecipante.getTeam() != null ? partecipante.getTeam().getId() : 0L);
            ps.setString(3,STATO_IN_ATTESA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Long id = rs.getLong("invito_id");
                Long idPartecipante = rs.getLong("id_partecipante");
                Long idTeam = rs.getLong("id_team");
                String stato = rs.getString("stato");
                String nomeTeam = rs.getString("nome_team");
                String emailPartecipante = rs.getString("email");
                String nomeHackathon = rs.getString("nome_hackathon");

                Hackathon hackathon = new Hackathon();
                hackathon.setNome(nomeHackathon);

                Team team = new Team(idTeam,nomeTeam,null,null,hackathon);
                Utente utente = getUtenteModel(idPartecipante,null,null,emailPartecipante,null,TIPO_PARTECIPANTE,team);

                invitiList.add(new Invito(id,(Partecipante) utente,team,stato));
            }
            rs.close();
            return invitiList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
