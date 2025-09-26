package model;
import java.time.LocalDate;

/**
 * The type Documento.
 */
public class Documento {
    private Long id;
    private String descrizione;
    private String versione; //2.0 ecc
    private LocalDate dataInvio;
    private String commGiudice;
    private Team team;


    /**
     * Instantiates a new Documento.
     */
    public Documento() {}

    /**
     * Instantiates a new Documento.
     *
     * @param id          the id
     * @param descrizione the descrizione
     * @param versione    the versione
     * @param dataInvio   the data invio
     */
    public Documento(Long id, String descrizione, String versione, LocalDate dataInvio) {
        this.id = id;
        this.descrizione = descrizione;
        this.versione = versione;
        this.dataInvio = dataInvio;
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
     * Sets descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets versione.
     *
     * @param versione the versione
     */
    public void setVersione(String versione) {
        this.versione = versione;
    }

    /**
     * Gets versione.
     *
     * @return the versione
     */
    public String getVersione() {
        return versione;
    }

    /**
     * Sets data invio.
     *
     * @param dataInvio the data invio
     */
    public void setDataInvio(LocalDate dataInvio) {
        this.dataInvio = dataInvio;
    }

    /**
     * Gets data invio.
     *
     * @return the data invio
     */
    public LocalDate getDataInvio() {
        return dataInvio;
    }

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

    /**
     * Gets comm giudice.
     *
     * @return the comm giudice
     */
    public String getCommGiudice() {return commGiudice;}

    /**
     * Sets comm giudice.
     *
     * @param commGiudice the comm giudice
     */
    public void setCommGiudice(String commGiudice) {this.commGiudice = commGiudice;}
}
