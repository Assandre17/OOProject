package model;

/**
 * The type Invito.
 */
public class Invito {
    private Long id;
    private Utente utenteInvitato;
    private Team team;
    private Hackathon hackathon;
    private String stato;

    /**
     * Instantiates a new Invito.
     */
    public Invito() {}

    /**
     * Instantiates a new Invito.
     *
     * @param id             the id
     * @param utenteInvitato the utente invitato
     * @param team           the team
     * @param hackathon      the hackathon
     * @param stato          the stato
     */
    public Invito(Long id, Utente utenteInvitato, Team team, Hackathon hackathon, String stato) {
        this.id = id;
        this.utenteInvitato = utenteInvitato;
        this.team = team;
        this.hackathon = hackathon;
        this.stato = stato;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets utente invitato.
     *
     * @return the utente invitato
     */
    public Utente getUtenteInvitato() {
        return utenteInvitato;
    }

    /**
     * Sets utente invitato.
     *
     * @param utente the utente
     */
    public void setUtenteInvitato(Utente utente) {
        this.utenteInvitato = utente;
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets team.
     *
     * @param team the team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Gets stato.
     *
     * @return the stato
     */
    public String getStato() {
        return stato;
    }

    /**
     * Sets stato.
     *
     * @param stato the stato
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Gets hackathon.
     *
     * @return the hackathon
     */
    public Hackathon getHackathon() {return hackathon;}

    /**
     * Sets hackathon.
     *
     * @param hackathon the hackathon
     */
    public void setHackathon(Hackathon hackathon) {this.hackathon = hackathon;}
}
