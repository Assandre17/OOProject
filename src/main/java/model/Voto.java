package model;

public class Voto {
    private Long id;
    private int valutazione;
    private String commento;
    private Team team;

    public Voto() {
    }

    public Voto(Long id, int valutazione, String commento, Team team) {
        this.id = id;
        this.valutazione = valutazione;
        this.commento = commento;
        this.team = team;
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

    public Team getTeam() {return team;}

    public void setTeam(Team team) {this.team = team;}
}
