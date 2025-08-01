package implementazionePostgresDAO;

import dao.OrganizzatoreDAO;
import database.ConnessioneDatabase;
import model.Giudice;
import model.Hackathon;
import model.Utente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrganizzatoreImplementazionePostgresDAO implements OrganizzatoreDAO {
    private Connection connection;

    public OrganizzatoreImplementazionePostgresDAO() throws SQLException {
        this.connection = ConnessioneDatabase.getInstance().connection;
    }

    public void insertHackathon(Hackathon hackathon) {
        try{
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO hackathon (nome,sede,data_inizio,data_fine,num_partecipanti,num_max_partecipanti) VALUES(?,?,?,?,?,?)");
            ps.setString(1, hackathon.getNome());
            ps.setString(2, hackathon.getSede());
            ps.setDate(3, Date.valueOf(hackathon.getDataInizio()));
            ps.setDate(4, Date.valueOf(hackathon.getDataFine()));
            ps.setInt(5, hackathon.getNumpartecipanti());
            ps.setInt(6, hackathon.getNummaxpartecipanti());
            ps.executeUpdate();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Giudice> getListGiudice() throws SQLException {
        //preparo la query
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE tipo = GIUDICE");
        //eseguire la query
        ResultSet rs = ps.executeQuery();

        List<Giudice> list = new ArrayList();
        while(rs.next()){
            Giudice giudice = new Giudice();
            giudice.setId(rs.getLong("id"));
            giudice.setNome(rs.getString("nome"));
            giudice.setCognome(rs.getString("cognome"));
            giudice.setEmail(rs.getString("email"));
            list.add(giudice);
        }
        return list;
    }

    public List<Hackathon> getListHackathon(Utente user) throws SQLException {
        //preparo la query
        Long id = user.getId();
        PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM hackathon WHERE idOrganizzatore = ?");
        //eseguire la query
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        List<Hackathon> list = new ArrayList<>();
        while (rs.next()) {
            Hackathon hackathon = new Hackathon();
            hackathon.setId(rs.getLong("id"));
            hackathon.setNome(rs.getString("nome"));
            hackathon.setSede(rs.getString("sede"));
            hackathon.setDataInizio(LocalDate.parse(rs.getString("data_inizio")));
            hackathon.setDataFine(LocalDate.parse(rs.getString("data_fine")));
            hackathon.setNumpartecipanti(rs.getInt("num_partecipanti"));
            hackathon.setNummaxpartecipanti(rs.getInt("num_max_partecipanti"));
            hackathon.setDescrizione(rs.getString("descrizione"));
            hackathon.setInizioiscrizioni(LocalDate.parse(rs.getString("inizio_iscrizioni")));
            hackathon.setFineiscrizioni(LocalDate.parse(rs.getString("fine_iscrizioni")));
            list.add(hackathon);
        }
        return list;
    }
}
