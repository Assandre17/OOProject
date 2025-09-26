package implementazionePostgresDAO;

import dao.UtenteDAO;
import database.ConnessioneDatabase;
import model.Hackathon;
import model.Team;
import model.Utente;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Utente implentazione postgres dao.
 */
public class UtenteImplentazionePostgresDAO implements UtenteDAO {
    private Connection connection;

    /**
     * Instantiates a new Utente implentazione postgres dao.
     */
    public UtenteImplentazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUtente(Utente utente){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (nome,cognome,email,password,tipo) VALUES(?,?,?,?,?)");
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getPassword());
            ps.setString(5, Utils.getTipo(utente));

            ps.executeUpdate();

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //serve per trovare utente che accede, quindi per fare l'accesso
    @Override
    public Utente getUtenteByEmailAndPassword(String email, String password) {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT u.id, u.nome, u.cognome, u.email, u.password, u.tipo, " +
                        "       t.id AS team_id, t.nome AS nome_team, t.id_hackathon AS id_hackathon " +
                        "FROM users u " +
                        "LEFT JOIN partecipante_team pt ON pt.id_partecipante = u.id " +
                        "LEFT JOIN teams t ON pt.id_team = t.id " +
                        "WHERE u.email = ? AND u.password = ?")) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            Utente utente = null;
            List<Team> teams = new ArrayList<>();

            while (rs.next()) {

                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String tipo = rs.getString("tipo");
                Long idTeam = rs.getLong("team_id");
                Long idHackathon = rs.getLong("id_hackathon");

                if (idTeam.equals(0L)) {
                    utente = Utils.getUtenteModel(id, nome, cognome, email, password, tipo, null, null);
                } else {
                    Hackathon hackathon = new Hackathon();
                    hackathon.setId(idHackathon);
                    String nomeTeam = rs.getString("nome_team");
                    Team team = new Team();
                    team.setId(idTeam);
                    team.setNome(nomeTeam);
                    team.setHackathon(hackathon);
                    teams.add(team);
                    utente = Utils.getUtenteModel(id, nome, cognome, email, password, tipo, teams, null);// Assumendo che Utente ora abbia una lista di Team
                }
            }
            rs.close();
            return utente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //serve per evitare di far registrare un utente con un'email già esistente
    @Override
    public Utente getUtenteByEmail(String email) {
        try(PreparedStatement ps = connection.prepareStatement("SELECT u.*, t.nome AS nome_team FROM users u LEFT JOIN partecipante_team pt ON u.id = pt.id_partecipante LEFT JOIN teams t ON pt.id_team = t.id  WHERE u.email = ?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String password =  rs.getString("password");
                String cognome = rs.getString("cognome");
                String tipo = rs.getString("tipo");

                return Utils.getUtenteModel(id, nome,cognome,email,password,tipo, null, null);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
