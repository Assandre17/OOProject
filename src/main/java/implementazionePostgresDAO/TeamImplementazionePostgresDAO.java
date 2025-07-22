package implementazionePostgresDAO;

import dao.TeamDAO;
import database.ConnessioneDatabase;

import java.sql.*;

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
}
