package model;

/**
 * The type Voto.
 */
public class Voto {
    private Long id;
    private int valutazione;
    private String commento;
    private Team team;

    /**
     * Instantiates a new Voto.
     */
    public Voto() {
    }

    /**
     * Instantiates a new Voto.
     *
     * @param id          the id
     * @param valutazione the valutazione
     * @param commento    the commento
     * @param team        the team
     */
    public Voto(Long id, int valutazione, String commento, Team team) {
        this.id = id;
        this.valutazione = valutazione;
        this.commento = commento;
        this.team = team;
    }

    /**
     * Gets valutazione.
     *
     * @return the valutazione
     */
    public int getValutazione() {
        return valutazione;
    }

    /**
     * Sets valutazione.
     *
     * @param valutazione the valutazione
     */
    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    /**
     * Gets commento.
     *
     * @return the commento
     */
    public String getCommento() {
        return commento;
    }

    /**
     * Sets commento.
     *
     * @param commento the commento
     */
    public void setCommento(String commento) {
        this.commento = commento;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {return id;}

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {this.id = id;}

    /**
     * Gets team.
     *
     * @return the team
     */
    public Team getTeam() {return team;}

    /**
     * Sets team.
     *
     * @param team the team
     */
    public void setTeam(Team team) {this.team = team;}
}
