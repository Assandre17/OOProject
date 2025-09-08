package model;

import java.time.LocalDate;

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

    public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
    public void setNome(String nome) {this.nome = nome;}
    public String getNome() {return nome;}
    public void setSede(String sede) {this.sede = sede;}
    public String getSede() {return sede;}
    public void setDataInizio(LocalDate dataInizio) {this.dataInizio = dataInizio;}
    public LocalDate getDataInizio() {return dataInizio;}
    public void setDataFine(LocalDate dataFine) {this.dataFine = dataFine;}
    public LocalDate getDataFine() {return dataFine;}
    public void setNumPartecipanti(Integer numPartecipanti) {this.numPartecipanti = numPartecipanti;}
    public Integer getNumPartecipanti() {return numPartecipanti;}
    public void setNumMaxPartecipanti(Integer numMaxPartecipanti) {this.numMaxPartecipanti = numMaxPartecipanti;}
    public Integer getNumMaxPartecipanti() {return numMaxPartecipanti;}
    public void setInizioIscrizioni(LocalDate inizioIscrizioni) {this.inizioIscrizioni = inizioIscrizioni;}
    public LocalDate getInizioIscrizioni() {return inizioIscrizioni;}
    public void setFineIscrizioni(LocalDate fineIscrizioni) {this.fineIscrizioni = fineIscrizioni;}
    public LocalDate getFineIscrizioni() {return fineIscrizioni;}
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}
    public String getDescrizione() {return descrizione;}
    public void setOrganizzatore(Organizzatore organizzatore){this.organizzatore = organizzatore;}
    public Organizzatore getOrganizzatore() {return organizzatore;}
}
