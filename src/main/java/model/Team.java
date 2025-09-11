package model;

import java.util.List;

public class Team {
    private Long id;
    private String nome;
    private List<Partecipante> partecipanti;
    private Hackathon hackathon;

    public Team() {}

    public Team(Long id, String nome, List<Partecipante> partecipanti, Hackathon hackathon) {
        this.id = id;
        this.nome = nome;
        this.partecipanti = partecipanti;
        this.hackathon = hackathon;
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

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }
}
