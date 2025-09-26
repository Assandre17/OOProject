package dao;

import model.Giudice;
import model.Invito;
import model.Partecipante;

import java.util.List;

/**
 * The interface Invito dao.
 */
public interface InvitoDAO {
    /**
     * Insert invito.
     *
     * @param idPartecipante    the id partecipante
     * @param idTeam            the id team
     * @param idHackathon       the id hackathon
     * @param figuraInserimento the figura inserimento
     */
    void insertInvito(Long idPartecipante, Long idTeam, Long idHackathon, String figuraInserimento);

    /**
     * Gets inviti partecipante.
     * trova gli inviti in stato di attesa inviati a un partecipante oppure
     * gli inviti inviati da un team che sono ancora in attesa.
     * @param partecipante the partecipante
     * @return the inviti partecipante
     */
    List<Invito> getInvitiPartecipante(Partecipante partecipante);

    /**
     * Gets inviti giudice.
     *
     * @param giudice the giudice
     * @return the inviti giudice
     */
    List<Invito> getInvitiGiudice(Giudice giudice);

    /**
     * Update stato invito.
     *
     * @param idInvito    the id invito
     * @param statoInvito the stato invito
     */
    void updateStatoInvito(Long idInvito, String statoInvito);
}
