package model;
import java.time.LocalDate;

public class Documento {
    private String descrizione;
    private String versione; //2.0 ecc
    private LocalDate dataInvio;

    public Documento() {}

    public Documento(String descrizione, String versione, LocalDate dataInvio) {
        this.descrizione = descrizione;
        this.versione = versione;
        this.dataInvio = dataInvio;
    }
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
}
