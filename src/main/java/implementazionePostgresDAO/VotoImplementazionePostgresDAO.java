package implementazionePostgresDAO;

import dao.VotoDAO;
import database.ConnessioneDatabase;
import model.Voto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VotoImplementazionePostgresDAO implements VotoDAO {
    private Connection connection;

    public VotoImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Long insertVoto(Integer valutazione, String commento, Long idTeam) {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO voti (valutazione, commento, id_team) VALUES(?,?,?)",  Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, valutazione);
            ps.setString(2,commento);
            ps.setLong(3,idTeam);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Voto> getVotiByIdTeam(Long id) {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM voti WHERE id_team = ?")){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            List <Voto> votiList = new ArrayList<>();
            while(rs.next()){
                Long idVoto = rs.getLong("id");
                Integer valutazione = rs.getInt("valutazione");
                String commento = rs.getString("commento");

                Voto voto = new Voto(idVoto,valutazione,commento,null);
                votiList.add(voto);
            }

            return votiList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
