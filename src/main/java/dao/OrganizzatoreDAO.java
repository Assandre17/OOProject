package dao;

import model.Giudice;
import model.Hackathon;
import model.Utente;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface Organizzatore dao.
 */
public interface OrganizzatoreDAO {
    /**
     * Insert hackathon.
     *
     * @param hackathon the hackathon
     * @throws SQLException the sql exception
     */
    public void insertHackathon(Hackathon hackathon) throws SQLException;

    /**
     * Gets list hackathon.
     *
     * @param user the user
     * @return the list hackathon
     * @throws SQLException the sql exception
     */
    public List<Hackathon> getListHackathon(Utente user) throws SQLException;

    /**
     * Gets list giudice.
     *
     * @param idHackathon the id hackathon
     * @return the list giudice
     * @throws SQLException the sql exception
     */
    public List<Giudice> getListGiudice(Long idHackathon);
}
