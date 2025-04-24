package main.java.model;

public abstract class Utente {
    private String nome;
    private String cognome;

    protected Utente() {}

    protected Utente(String nome, String cognome){
        this.nome = nome;
        this.cognome = cognome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
