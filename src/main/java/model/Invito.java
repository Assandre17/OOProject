package model;

public class Invito {
    private Long id;
    private Utente utenteInvitato;
    private Team team;
    private Hackathon hackathon;
    private String stato;

    public Invito() {}

    public Invito(Long id, Utente utenteInvitato, Team team, Hackathon hackathon, String stato) {
        this.id = id;
        this.utenteInvitato = utenteInvitato;
        this.team = team;
        this.hackathon = hackathon;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtenteInvitato() {
        return utenteInvitato;
    }

    public void setUtenteInvitato(Utente utente) {
        this.utenteInvitato = utente;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Hackathon getHackathon() {return hackathon;}

    public void setHackathon(Hackathon hackathon) {this.hackathon = hackathon;}
}
