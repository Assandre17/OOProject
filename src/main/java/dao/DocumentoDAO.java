package dao;

import model.Documento;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Documento dao.
 */
public interface DocumentoDAO {
    /**
     * Add documento.
     *
     * @param descrizione the descrizione
     * @param versione    the versione
     * @param dataInvio   the data invio
     * @param idTeam      the id team
     */
    void addDocumento(String descrizione, String versione, LocalDate dataInvio, Long idTeam);

    /**
     * Gets documenti by id hackathon.
     *
     * @param idHackathon the id hackathon
     * @return the documenti by id hackathon
     */
    List<Documento> getDocumentiByIdHackathon(Long idHackathon);

    /**
     * Gets documento by id.
     *
     * @param id the id
     * @return the documento by id
     */
    Documento getDocumentoById(Long id);

    /**
     * Add commento to documento.
     *
     * @param commento    the commento
     * @param idDocumento the id documento
     */
    void addCommentoToDocumento(String commento, Long idDocumento);

    /**
     * Gets documenti by id team.
     *
     * @param idTeam the id team
     * @return the documenti by id team
     */
    List<Documento> getDocumentiByIdTeam(Long idTeam);
}
