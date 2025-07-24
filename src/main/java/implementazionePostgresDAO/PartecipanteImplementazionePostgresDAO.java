package implementazionePostgresDAO;

import dao.PartecipanteDAO;
import database.ConnessioneDatabase;
import model.Hackathon;
import model.Partecipante;
import model.Utente;
import org.apache.commons.collections.list.TreeList;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartecipanteImplementazionePostgresDAO implements PartecipanteDAO {
    private Connection connection;

    public PartecipanteImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Hackathon> getListHackathon(Utente user) throws SQLException {
        //preparo la query
        Long id = user.getId();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM hackathon WHERE idPartecipante = ?");
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

    @Override
    public List<Partecipante> getPartecipantiWithoutTeam(Long idPartecipante) {
        List<Partecipante> partecipantiList = new TreeList();
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE tipo = 'PARTECIPANTE' AND id_team IS NULL AND id != ?" )) {
            ps.setLong(1, idPartecipante);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String tipo = rs.getString("tipo");
                partecipantiList.add((Partecipante) Utils.getUtenteModel(id,nome,cognome,email,password,tipo,null));
            }
            rs.close();
            return partecipantiList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
