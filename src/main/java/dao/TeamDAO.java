package dao;

import model.Team;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Team dao.
 */
public interface TeamDAO {
    /**
     * Insert team long.
     *
     * @param nome        the nome
     * @param idHackathon the id hackathon
     * @return the long
     */
    Long insertTeam(String nome,Long idHackathon);

    /**
     * Gets team by id hackathon.
     *
     * @param idHackathon the id hackathon
     * @return the team by id hackathon
     * @throws SQLException the sql exception
     */
    List<Team> getTeamByIdHackathon(Long idHackathon);

    /**
     * Gets team by id.
     *
     * @param id the id
     * @return the team by id
     */
    Team getTeamById(Long id);
}
