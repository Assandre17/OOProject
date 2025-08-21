package dao;

import java.time.LocalDate;

public interface DocumentoDAO {
    void addDocumento(String descrizione, String versione, LocalDate dataInvio, Long idTeam);
}
