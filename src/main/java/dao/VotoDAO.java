package dao;

public interface VotoDAO {
    Long insertVoto(Integer valutazione, String commento, Long idTeam);
}
