package dao;

/**
 * The interface Voto dao.
 */
import model.Voto;

import java.util.List;

public interface VotoDAO {
    /**
     * Insert voto long.
     *
     * @param valutazione the valutazione
     * @param commento    the commento
     * @param idTeam      the id team
     * @return the long
     */
    Long insertVoto(Integer valutazione, String commento, Long idTeam);

    List<Voto> getVotiByIdTeam(Long id);
}
