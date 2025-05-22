package main.java.model;

import java.util.List;

public class Team {
    private Long id;
    private String nome;
    private List<Partecipante> partecipanti;

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
}
