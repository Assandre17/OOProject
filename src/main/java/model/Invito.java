package model;

public class Invito {
    private Long id;
    private Partecipante partecipanteInvitato;
    private Team team;
    private String stato;

    public Invito() {}

    public Invito(Long id, Partecipante partecipanteInvitato, Team team, String stato) {
        this.id = id;
        this.partecipanteInvitato = partecipanteInvitato;
        this.team = team;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partecipante getPartecipanteInvitato() {
        return partecipanteInvitato;
    }

    public void setPartecipanteInvitato(Partecipante partecipante) {
        this.partecipanteInvitato = partecipante;
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
}
