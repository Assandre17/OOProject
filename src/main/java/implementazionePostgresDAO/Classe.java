package implementazionePostgresDAO;

import dao.ClasseDAO;
import database.ConnessioneDatabase;
import model.Hackathon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Classe implements ClasseDAO {
    private Connection connection;

    public Classe() throws SQLException {
        this.connection = ConnessioneDatabase.getInstance().connection;
    }


    //ESEMPIO
    @Override
    public Hackathon leggiDalDB() throws SQLException {
        //preparo la query
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM hackathon WHERE id = 1");
        //eseguire la query
        ResultSet rs = ps.executeQuery();

        String nome = rs.getString("nome");

        Hackathon hackathon = new Hackathon();
        hackathon.setNome(nome);
        return hackathon;

    }

}
