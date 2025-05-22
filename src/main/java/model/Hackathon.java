package main.java.model;
import java.time.LocalDate;

public class Hackathon {
    private String nome;
    private String sede;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int numpartecipanti;
    private int nummaxpartecipanti;
    private LocalDate inizioiscrizioni;
    private LocalDate fineiscrizioni;
    private String descrizione;

    public void setNome(String nome) {this.nome = nome;}
    public String getNome() {return nome;}
    public void setSede(String sede) {
        this.sede = sede;
    }
    public String getSede() {
        return sede;
    }
    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }
    public LocalDate getDataInizio() {
        return dataInizio;
    }
    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }
    public LocalDate getDataFine() {
        return dataFine;
    }
    public void setNumpartecipanti(int numpartecipanti) {
        this.numpartecipanti = numpartecipanti;
    }
    public int getNumpartecipanti() {
        return numpartecipanti;
    }
    public void setNummaxpartecipanti(int nummaxpartecipanti) {
        this.nummaxpartecipanti = nummaxpartecipanti;
    }
    public int getNummaxpartecipanti() {
        return nummaxpartecipanti;
    }
    public void setInizioiscrizioni() {
        this.inizioiscrizioni = LocalDate.now();
    }
    public LocalDate getInizioiscrizioni() {
        return inizioiscrizioni;
    }
    public void setFineiscrizioni() {
        this.fineiscrizioni = LocalDate.now();
    }
    public LocalDate getFineiscrizioni() {
        return fineiscrizioni;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getDescrizione() {
        return descrizione;
    }
}
