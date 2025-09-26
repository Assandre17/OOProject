package dao;

/**
 * The interface Voto dao.
 */
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
}
