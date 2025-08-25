package dao;

import model.Documento;

import java.time.LocalDate;
import java.util.List;

public interface DocumentoDAO {
    void addDocumento(String descrizione, String versione, LocalDate dataInvio, Long idTeam);

    List<Documento> getDocumentiByIdHackathon(Long idHackathon);

    Documento getDocumentoById(Long id);

    void addCommentoToDocumento(String commento, Long idDocumento);
}
