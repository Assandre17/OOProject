package model;

import java.util.List;

/**
 * The type Team.
 */
public class Team {
    private Long id;
    private String nome;
    private List<Partecipante> partecipanti;
    private Hackathon hackathon;

    /**
     * Instantiates a new Team.
     */
    public Team() {}

    /**
     * Instantiates a new Team.
     *
     * @param id           the id
     * @param nome         the nome
     * @param partecipanti the partecipanti
     * @param hackathon    the hackathon
     */
    public Team(Long id, String nome, List<Partecipante> partecipanti, Hackathon hackathon) {
        this.id = id;
        this.nome = nome;
        this.partecipanti = partecipanti;
        this.hackathon = hackathon;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets partecipanti.
     *
     * @return the partecipanti
     */
    public List<Partecipante> getPartecipanti() {
        return partecipanti;
    }

    /**
     * Sets partecipanti.
     *
     * @param partecipanti the partecipanti
     */
    public void setPartecipanti(List<Partecipante> partecipanti) {
        this.partecipanti = partecipanti;
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
     * Gets hackathon.
     *
     * @return the hackathon
     */
    public Hackathon getHackathon() {
        return hackathon;
    }

    /**
     * Sets hackathon.
     *
     * @param hackathon the hackathon
     */
    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }
}
