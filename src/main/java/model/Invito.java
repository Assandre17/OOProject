package model;

public class Invito {
    private Long id;
    private Partecipante partecipanteInvitato;
    private Team team;
    private String stato;

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
