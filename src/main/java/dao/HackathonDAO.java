package dao;

import model.Hackathon;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface HackathonDAO {
    void addNumPartecipante(Long idHackathon);

    void addProblemaToHackathon(String problema, Long idHackathon);

    Hackathon getHackathonById(Long id) throws SQLException;

    void apriRegistrazioni(Long idHackathon, LocalDate inizioIscrizioni, LocalDate fineIscrizioni) throws SQLException;

    List<Hackathon> getAllHackathon();
}
