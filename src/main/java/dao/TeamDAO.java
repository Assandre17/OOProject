package dao;

import model.Team;

import java.sql.SQLException;
import java.util.List;

public interface TeamDAO {
    Long insertTeam(String nome,Long idHackathon);
    List<Team> getTeamByIdHackathon(Long idHackathon) throws SQLException;
}
