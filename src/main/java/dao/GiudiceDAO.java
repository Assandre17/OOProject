package dao;

import model.Giudice;
import model.Hackathon;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Giudice dao.
 */
public interface GiudiceDAO {
    /**
     * Gets list hackathon.
     *
     * @param giudice the giudice
     * @return the list hackathon
     * @throws SQLException the sql exception
     */
    List<Hackathon> getListHackathon(Giudice giudice) throws SQLException;

    /**
     * Add hackathon to giudice.
     *
     * @param id          the id
     * @param idHackathon the id hackathon
     */
    void addHackathonToGiudice(Long id, Long idHackathon);
}
