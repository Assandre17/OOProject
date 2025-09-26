package dao;

import model.Hackathon;
import model.Partecipante;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Partecipante dao.
 */
public interface PartecipanteDAO {
    /**
     * Gets list hackathon.
     *
     * @param partecipante the partecipante
     * @return the list hackathon
     * @throws SQLException the sql exception
     */
    public List<Hackathon> getListHackathon(Partecipante partecipante) throws SQLException;

    /**
     * Gets partecipanti without team.
     *
     * @param idPartecipante the id partecipante
     * @param idHackathon    the id hackathon
     * @return the partecipanti without team
     */
    List<Partecipante> getPartecipantiWithoutTeam(Long idPartecipante, Long idHackathon);

    /**
     * Add team to partecipante.
     *
     * @param idPartecipante the id partecipante
     * @param idTeam         the id team
     */
    void addTeamToPartecipante(Long idPartecipante, Long idTeam);

    /**
     * Gets hackathon liberi.
     *
     * @param user the user
     * @return the hackathon liberi
     * @throws SQLException the sql exception
     */
    List<Hackathon> getHackathonLiberi(Partecipante user) throws SQLException;

    /**
     * Add partecipante to hackathon.
     *
     * @param idPartecipante the id partecipante
     * @param idHackathon    the id hackathon
     */
    void addPartecipanteToHackathon(Long idPartecipante, Long idHackathon);
}
