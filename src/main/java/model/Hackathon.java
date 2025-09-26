package model;

import java.time.LocalDate;

/**
 * The type Hackathon.
 */
public class Hackathon {
    private Long id;
    private String nome;
    private String sede;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private Integer numPartecipanti;
    private Integer numMaxPartecipanti;
    private LocalDate inizioIscrizioni;
    private LocalDate fineIscrizioni;
    private String descrizione;
    private Organizzatore organizzatore;

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {this.id = id;}

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {return id;}

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {this.nome = nome;}

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {return nome;}

    /**
     * Sets sede.
     *
     * @param sede the sede
     */
    public void setSede(String sede) {this.sede = sede;}

    /**
     * Gets sede.
     *
     * @return the sede
     */
    public String getSede() {return sede;}

    /**
     * Sets data inizio.
     *
     * @param dataInizio the data inizio
     */
    public void setDataInizio(LocalDate dataInizio) {this.dataInizio = dataInizio;}

    /**
     * Gets data inizio.
     *
     * @return the data inizio
     */
    public LocalDate getDataInizio() {return dataInizio;}

    /**
     * Sets data fine.
     *
     * @param dataFine the data fine
     */
    public void setDataFine(LocalDate dataFine) {this.dataFine = dataFine;}

    /**
     * Gets data fine.
     *
     * @return the data fine
     */
    public LocalDate getDataFine() {return dataFine;}

    /**
     * Sets num partecipanti.
     *
     * @param numPartecipanti the num partecipanti
     */
    public void setNumPartecipanti(Integer numPartecipanti) {this.numPartecipanti = numPartecipanti;}

    /**
     * Gets num partecipanti.
     *
     * @return the num partecipanti
     */
    public Integer getNumPartecipanti() {return numPartecipanti;}

    /**
     * Sets num max partecipanti.
     *
     * @param numMaxPartecipanti the num max partecipanti
     */
    public void setNumMaxPartecipanti(Integer numMaxPartecipanti) {this.numMaxPartecipanti = numMaxPartecipanti;}

    /**
     * Gets num max partecipanti.
     *
     * @return the num max partecipanti
     */
    public Integer getNumMaxPartecipanti() {return numMaxPartecipanti;}

    /**
     * Sets inizio iscrizioni.
     *
     * @param inizioIscrizioni the inizio iscrizioni
     */
    public void setInizioIscrizioni(LocalDate inizioIscrizioni) {this.inizioIscrizioni = inizioIscrizioni;}

    /**
     * Gets inizio iscrizioni.
     *
     * @return the inizio iscrizioni
     */
    public LocalDate getInizioIscrizioni() {return inizioIscrizioni;}

    /**
     * Sets fine iscrizioni.
     *
     * @param fineIscrizioni the fine iscrizioni
     */
    public void setFineIscrizioni(LocalDate fineIscrizioni) {this.fineIscrizioni = fineIscrizioni;}

    /**
     * Gets fine iscrizioni.
     *
     * @return the fine iscrizioni
     */
    public LocalDate getFineIscrizioni() {return fineIscrizioni;}

    /**
     * Sets descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    /**
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {return descrizione;}

    /**
     * Set organizzatore.
     *
     * @param organizzatore the organizzatore
     */
    public void setOrganizzatore(Organizzatore organizzatore){this.organizzatore = organizzatore;}

    /**
     * Gets organizzatore.
     *
     * @return the organizzatore
     */
    public Organizzatore getOrganizzatore() {return organizzatore;}
}
