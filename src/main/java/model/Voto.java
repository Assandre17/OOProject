package main.java.model;

public class Voto {
    private int valutazione;
    private String commento;

    public Voto() {
    }

    public Voto(int valutazione, String commento) {
        this.valutazione = valutazione;
        this.commento = commento;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }
}
