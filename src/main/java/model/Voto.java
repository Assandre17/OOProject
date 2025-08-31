package model;

public class Voto {
    private Long id;
    private int valutazione;
    private String commento;

    public Voto() {
    }

    public Voto(Long id, int valutazione, String commento) {
        this.id = id;
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

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}
}
