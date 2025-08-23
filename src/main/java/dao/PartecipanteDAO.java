package dao;

import model.Hackathon;
import model.Partecipante;

import java.sql.SQLException;
import java.util.List;

public interface PartecipanteDAO {
    public List<Hackathon> getListHackathon(Partecipante partecipante) throws SQLException;
    List<Partecipante> getPartecipantiWithoutTeam(Long idPartecipante, Long idHackathon);
    void addTeamToPartecipante(Long idPartecipante, Long idTeam);

    List<Hackathon> getHackathonLiberi(Partecipante user) throws SQLException;

    void addPartecipanteToHackathon(Long idPartecipante, Long idHackathon);
}
