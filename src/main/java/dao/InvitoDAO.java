package dao;

import model.Invito;
import model.Partecipante;

import java.util.List;

public interface InvitoDAO {
    void insertInvito(Long idPartecipante, Long idTeam);

    List<Invito> getInvitiPartecipante(Partecipante partecipante);

    void updateStatoInvito(Long idInvito, String statoInvito);
}
