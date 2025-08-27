package model;
import java.time.LocalDate;

public class Documento {
    private Long id;
    private String descrizione;
    private String versione; //2.0 ecc
    private LocalDate dataInvio;
    private String commGiudice;
    private Team team;



    public Documento() {}

    public Documento(Long id, String descrizione, String versione, LocalDate dataInvio) {
        this.id = id;
        this.descrizione = descrizione;
        this.versione = versione;
        this.dataInvio = dataInvio;
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setVersione(String versione) {
        this.versione = versione;
    }
    public String getVersione() {
        return versione;
    }
    public void setDataInvio(LocalDate dataInvio) {
        this.dataInvio = dataInvio;
    }
    public LocalDate getDataInvio() {
        return dataInvio;
    }
    public Team getTeam() {return team;}
    public void setTeam(Team team) {this.team = team;}
    public String getCommGiudice() {return commGiudice;}
    public void setCommGiudice(String commGiudice) {this.commGiudice = commGiudice;}
}
