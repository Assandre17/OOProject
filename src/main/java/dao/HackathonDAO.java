package dao;

import model.Hackathon;

import java.sql.SQLException;

public interface HackathonDAO {
    void addNumPartecipante(Long idHackathon);

    void addProblemaToHackathon(String problema, Long idHackathon);

    Hackathon getHackathonById(Long id) throws SQLException;
}
