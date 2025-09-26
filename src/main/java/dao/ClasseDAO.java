package dao;

import model.Hackathon;
import java.sql.SQLException;

/**
 * The interface Classe dao.
 */
public interface ClasseDAO {
    /**
     * Leggi dal db hackathon.
     *
     * @return the hackathon
     * @throws SQLException the sql exception
     */
//ESEMPIO
    Hackathon leggiDalDB() throws SQLException;
}
