package implementazionePostgresDAO;

import dao.VotoDAO;
import database.ConnessioneDatabase;

import java.sql.*;

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
    public Long insertVoto(Integer valutazione, String commento) {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO voti (valutazione, commento) VALUES(?,?)",  Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, valutazione);
            ps.setString(2,commento);

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
}
