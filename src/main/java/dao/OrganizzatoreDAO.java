package dao;

import model.Giudice;
import model.Hackathon;
import model.Utente;
import java.sql.SQLException;
import java.util.List;

public interface OrganizzatoreDAO {
    public void insertHackathon(Hackathon hackathon) throws SQLException;
    public List<Hackathon> getListHackathon(Utente user) throws SQLException;
    public List<Giudice> getListGiudice(Long idHackathon) throws SQLException;
}
