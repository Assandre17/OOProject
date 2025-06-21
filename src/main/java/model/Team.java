package model;

import java.util.List;

public class Team {
    private Long id;
    private String nome;
    private Voto voto;
    private List<Partecipante> partecipanti;
    private Hackathon hackathon;

    public Team() {}

    public Team(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Partecipante> getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(List<Partecipante> partecipanti) {
        this.partecipanti = partecipanti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Voto getVoto() {
        return voto;
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }
}
