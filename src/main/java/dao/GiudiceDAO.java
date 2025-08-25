package dao;

import model.Giudice;
import model.Hackathon;

import java.sql.SQLException;
import java.util.List;

public interface GiudiceDAO {
    List<Hackathon> getListHackathon(Giudice giudice) throws SQLException;
}
