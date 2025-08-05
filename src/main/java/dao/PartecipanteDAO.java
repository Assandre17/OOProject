package dao;

import model.Hackathon;
import model.Partecipante;
import model.Utente;
import java.sql.SQLException;
import java.util.List;

public interface PartecipanteDAO {
    public List<Hackathon> getListHackathon(Utente user) throws SQLException;

    List<Partecipante> getPartecipantiWithoutTeam(Long idPartecipante);
    void addTeamToPartecipante(Long idPartecipante, Long idTeam);
}
