package main.java.model;

import javax.swing.*;

public abstract class Utente {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;

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


    public void apriFinestra(JFrame frameChiamante){
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
