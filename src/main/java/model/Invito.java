package main.java.model;

public class Invito {
    private Long id;
    private Partecipante partecipante;
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partecipante getPartecipante() {
        return partecipante;
    }

    public void setPartecipante(Partecipante partecipante) {
        this.partecipante = partecipante;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
