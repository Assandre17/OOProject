package model;


import controller.Controller;

import javax.swing.*;

/**
 * The type Utente.
 */
public abstract class Utente {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;

    /**
     * Instantiates a new Utente.
     */
    protected Utente() {}

    /**
     * Instantiates a new Utente.
     *
     * @param id       the id
     * @param nome     the nome
     * @param cognome  the cognome
     * @param email    the email
     * @param password the password
     */
    protected Utente(Long id, String nome, String cognome, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
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
     * Gets cognome.
     *
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets cognome.
     *
     * @param cognome the cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    /**
     * Apri finestra.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public void apriFinestra(JFrame frameChiamante, Controller controller){
        frameChiamante.setVisible(false);
        frameChiamante.dispose();
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
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
}
