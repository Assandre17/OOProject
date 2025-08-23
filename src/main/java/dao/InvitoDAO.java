package dao;

import model.Invito;
import model.Partecipante;

import java.util.List;

public interface InvitoDAO {
    void insertInvito(Long idPartecipante, Long idTeam, String figuraInserimento);

    List<Invito> getInvitiPartecipante(Partecipante partecipante);

    void updateStatoInvito(Long idInvito, String statoInvito);
}
