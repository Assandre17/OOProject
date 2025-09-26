package dao;

import model.Hackathon;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * The interface Hackathon dao.
 */
public interface HackathonDAO {
    /**
     * Add num partecipante.
     *
     * @param idHackathon the id hackathon
     */
    void addNumPartecipante(Long idHackathon);

    /**
     * Add problema to hackathon.
     *
     * @param problema    the problema
     * @param idHackathon the id hackathon
     */
    void addProblemaToHackathon(String problema, Long idHackathon);

    /**
     * Gets hackathon by id.
     *
     * @param id the id
     * @return the hackathon by id
     * @throws SQLException the sql exception
     */
    Hackathon getHackathonById(Long id) throws SQLException;

    /**
     * Apri registrazioni.
     *
     * @param idHackathon      the id hackathon
     * @param inizioIscrizioni the inizio iscrizioni
     * @param fineIscrizioni   the fine iscrizioni
     * @throws SQLException the sql exception
     */
    void apriRegistrazioni(Long idHackathon, LocalDate inizioIscrizioni, LocalDate fineIscrizioni) throws SQLException;

    /**
     * Gets all hackathon.
     *
     * @return the all hackathon
     */
    List<Hackathon> getAllHackathon();
}
