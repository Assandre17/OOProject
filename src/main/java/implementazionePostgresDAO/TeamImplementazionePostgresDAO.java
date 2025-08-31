package implementazionePostgresDAO;

import dao.TeamDAO;
import database.ConnessioneDatabase;
import model.Hackathon;
import model.Team;
import model.Voto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamImplementazionePostgresDAO implements TeamDAO {
    private Connection connection;

    public TeamImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long insertTeam(String nome,Long idHackathon){
        Long idTeam = 0L;
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO teams (nome,id_hackathon) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nome);
            ps.setLong(2, idHackathon);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idTeam = rs.getLong(1);
            }

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idTeam;
    }

    @Override
    public List<Team> getTeamByIdHackathon(Long idHackathon) {
        List<Team> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT t.*,h.nome AS nome_hackathon FROM teams t " +
                    "LEFT JOIN hackathon h ON t.id_hackathon = h.id " +
                    "WHERE id_hackathon = ?");
            ps.setLong(1, idHackathon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Team team = new Team();
                team.setId(rs.getLong("id"));
                team.setNome(rs.getString("nome"));

                String nomeHackathon = rs.getString("nome_hackathon");
                Hackathon hackathon = new Hackathon();
                hackathon.setNome(nomeHackathon);
                hackathon.setId(idHackathon);
                team.setHackathon(hackathon);

                list.add(team);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Team getTeamById(Long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM teams " +
                    " WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Team team = new Team();
                team.setId(rs.getLong("id"));
                team.setNome(rs.getString("nome"));

                long idVoto = rs.getLong("id_voto");

                if(idVoto != 0){
                    Voto voto = new Voto();
                    voto.setId(idVoto);
                    team.setVoto(voto);
                }

                return team;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addVotoToTeam(Long idTeam, Long idVoto) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE teams SET id_voto = ? WHERE id = ?")) {
            ps.setLong(1, idVoto);
            ps.setLong(2, idTeam);
            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
