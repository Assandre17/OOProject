package dao;

import model.Hackathon;
import java.sql.SQLException;

public interface ClasseDAO {
    //ESEMPIO
    Hackathon leggiDalDB() throws SQLException;
}
