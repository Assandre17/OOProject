package implementazionePostgresDAO;

import dao.DocumentoDAO;
import database.ConnessioneDatabase;
import model.Documento;
import model.Team;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocumentoImplementazionePostgresDAO implements DocumentoDAO {

    private Connection connection;

    public DocumentoImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDocumento(String descrizione, String versione, LocalDate dataPubblicazione, Long idTeam) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO documenti (descrizione,versione,data_invio, id_team) VALUES(?,?,?,?)")) {
            ps.setString(1, descrizione);
            ps.setString(2, versione);
            ps.setDate(3, Date.valueOf(dataPubblicazione));
            ps.setLong(4, idTeam);

            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Documento> getDocumentiByIdHackathon(Long idHackathon) {
        List<Documento> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT d.*, t.nome AS nome_team FROM documenti d " +
                    "JOIN teams t ON d.id_team = t.id " +
                    "WHERE id_hackathon = ?");
            ps.setLong(1, idHackathon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Documento documento = new Documento();
                documento.setId(rs.getLong("id"));
                documento.setVersione(rs.getString("versione"));

                String nomeTeam = rs.getString("nome_team");
                Team team = new Team();
                team.setNome(nomeTeam);
                documento.setTeam(team);

                list.add(documento);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Documento getDocumentoById(Long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT d.descrizione,d.versione,d.data_invio,d.commento_giudice, d.id AS id_documento, t.nome AS nome_team FROM documenti d " +
                    "JOIN teams t ON d.id_team = t.id " +
                    "WHERE d.id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Documento documento = new Documento();
                documento.setId(rs.getLong("id_documento"));
                documento.setVersione(rs.getString("versione"));
                documento.setDescrizione(rs.getString("descrizione"));
                documento.setDataInvio(rs.getDate("data_invio").toLocalDate());
                documento.setCommGiudice(rs.getString("commento_giudice"));

                String nomeTeam = rs.getString("nome_team");
                Team team = new Team();
                team.setNome(nomeTeam);
                documento.setTeam(team);

                return documento;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Documento> getDocumentiByIdTeam(Long idTeam) {
        List<Documento> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT d.*, t.nome AS nome_team FROM documenti d " +
                    "JOIN teams t ON d.id_team = t.id " +
                    "WHERE id_team = ?");
            ps.setLong(1, idTeam);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Documento documento = new Documento();
                documento.setId(rs.getLong("id"));
                documento.setVersione(rs.getString("versione"));
                documento.setDescrizione(rs.getString("descrizione"));
                String nomeTeam = rs.getString("nome_team");
                Team team = new Team();
                team.setNome(nomeTeam);
                documento.setTeam(team);

                list.add(documento);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addCommentoToDocumento(String commento, Long idDocumento) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE documenti SET commento_giudice = ? WHERE id = ?")) {
            ps.setString(1, commento);
            ps.setLong(2, idDocumento);
            ps.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
