package controller;

import gui.ActionButton;
import implementazionePostgresDAO.*;
import model.*;
import utils.Utils;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.*;

public class Controller {
    private String nomeTeam;
    private Utente utente;
    private Long idHackathon;
    private Long idTeam;
    private ActionButton actionButton;
    private String nomeButton;

    public Long getIdHackathon() {
        return idHackathon;
    }

    public void setIdHackathon(Long idHackathon) {
        this.idHackathon = idHackathon;
    }

    public void creaHackathon(String sede, String nome, String numMaxPartecipanti, String inizioIscrizioni, String fineIscrizioni, String descrizione) throws SQLException {
        System.out.println("creazione Hackathon in corso...");

        //regex per formato AAAA-MM-GG
        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(dateRegex);

        //check sul formato della data
        if(!pattern.matcher(inizioIscrizioni).matches()){
            throw new IllegalArgumentException("formato data inizio iscrizioni non valido! il formato deve essere AAAA-MM-GG");
        }
        if(!pattern.matcher(fineIscrizioni).matches()){
            throw new IllegalArgumentException("formato data fine iscrizioni non valido! il formato deve essere AAAA-MM-GG");
        } //check data fatto

        Hackathon hackathon = new Hackathon();
        hackathon.setSede(sede);
        hackathon.setNome(nome);
        hackathon.setNummaxpartecipanti(Integer.parseInt(numMaxPartecipanti));
        hackathon.setDataInizio(LocalDate.parse(inizioIscrizioni));
        hackathon.setDataFine(LocalDate.parse(fineIscrizioni));
        hackathon.setDescrizione(descrizione);
        OrganizzatoreImplementazionePostgresDAO organizzatoreDAO = new OrganizzatoreImplementazionePostgresDAO();
        organizzatoreDAO.insertHackathon(hackathon);
    }

    public List<Hackathon> vediHackathonCreati(Utente user) throws SQLException {
        System.out.println("visualizzazione Hackathon creati");
        OrganizzatoreImplementazionePostgresDAO organizzatoreDAO = new OrganizzatoreImplementazionePostgresDAO();
        return organizzatoreDAO.getListHackathon(user); //visualizzazione hackathon creati fatta
    }


    public List<Hackathon> getHackathonPartecipante(Partecipante user) throws SQLException {
        System.out.println("visualizzazione Hackathon del partecipante");
        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        return partecipanteDAO.getListHackathon(user);
    }

    public List<Hackathon> getHackathonLiberi(Partecipante user) throws SQLException {
        System.out.println("visualizzazione Hackathon  a cui può iscriversi il partecipante");
        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        return partecipanteDAO.getHackathonLiberi(user);

    }

    public List<Team> getTeamByIdHackathon(Long idHackathon) {
        System.out.println("visualizzazione dei team appartenenti all'hackathon con id " + idHackathon);
        TeamImplementazionePostgresDAO teamDAO = new TeamImplementazionePostgresDAO();
        return teamDAO.getTeamByIdHackathon(idHackathon);
    }



    public List<Partecipante> getPartecipantiWithoutTeam(){
        System.out.println("visualizzazione partecipanti senza team");
        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        return partecipanteDAO.getPartecipantiWithoutTeam(getUtente().getId());
    }

    public void invitaGiudice(Long idHackathon) throws SQLException {
        OrganizzatoreImplementazionePostgresDAO organizzatoreDAO = new OrganizzatoreImplementazionePostgresDAO();
        organizzatoreDAO.getListGiudice();
    }
    public void apriRegistrazioni(){}
    public void registrati(String nome,String cognome, String email, String password, String tipo) throws InstanceAlreadyExistsException {
        checkField(nome, cognome, email, password);

        UtenteImplentazionePostgresDAO utenteDAO = new UtenteImplentazionePostgresDAO();
        //verifica se la mail inserita in fase di registrazione è già esistente
        if(Objects.nonNull(utenteDAO.getUtenteByEmail(email))){
            throw new InstanceAlreadyExistsException("Utente già esistente nel sistema");
        }

        Utente utenteRegistrato = Utils.getUtenteModel(null, nome,cognome,email,password,tipo,null);

        utenteDAO.insertUtente(utenteRegistrato);
    }
    public Utente accedi(String email, String password) throws InstanceNotFoundException {
        checkField("nome","cognome",email,password);

        UtenteImplentazionePostgresDAO utenteDAO = new UtenteImplentazionePostgresDAO();

        Utente utenteLoggato = utenteDAO.getUtenteByEmailAndPassword(email,password);

        if(Objects.isNull(utenteLoggato)){
            throw new InstanceNotFoundException("Accesso errato!");
        }

        setUtente(utenteLoggato);

        return utenteLoggato;
    }

    public void iscriviti(Long idPartecipante, Long idHackathon){
        System.out.println("iscrizione ad Hackathon in corso...");

        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        partecipanteDAO.addPartecipanteToHackathon(idPartecipante,idHackathon);

        HackathonImplementazionePostgresDAO hackathonDAO = new HackathonImplementazionePostgresDAO();
        hackathonDAO.addNumPartecipante(idHackathon);

    }
    public void creaTeam(String nome, List<Partecipante> listaPartecipanti){
        TeamImplementazionePostgresDAO teamDAO = new TeamImplementazionePostgresDAO();
        Long idTeam = teamDAO.insertTeam(nome,getIdHackathon());

        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        //inserisco l'utente loggato nel suo team
        partecipanteDAO.addTeamToPartecipante(getUtente().getId(), idTeam);

        invitaPartecipanti(listaPartecipanti,idTeam);


    }
    private void invitaPartecipanti(List<Partecipante> partecipanti, Long idTeam){
        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        partecipanti.forEach(partecipante -> invitoDAO.insertInvito(partecipante.getId(), idTeam));

    }
    public void richiestaIngressoTeam(Partecipante partecipante, Team team){
        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        invitoDAO.insertInvito(partecipante.getId(), team.getId());
    }

    public List<Invito> getInvitiPartecipante(Partecipante partecipante){
        System.out.println("visualizzazione inviti del partecipante");
        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        return invitoDAO.getInvitiPartecipante(partecipante);
    }

    public void accettaORifiutaInvitoTeam(boolean decisione,Partecipante partecipante, Long idTeam, Long idInvito){
        System.out.println("gestione invito in corso...");

        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();

        String statoInvito = null;

        if(decisione){
            statoInvito = Utils.STATO_ACCETTATO;
            partecipanteDAO.addTeamToPartecipante(partecipante.getId(), idTeam);
        }else{
            statoInvito = Utils.STATO_RIFIUTATO;
        }

        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        invitoDAO.updateStatoInvito(idInvito,statoInvito);


    }
    public String pubblicaProblema(String problema, Long idHackathon){
        System.out.println("pubblica problema in corso...");

        return "";

    }
    public int assegnaVoto(Voto voto, Long idTeam){
        System.out.println("pubblica voto in corso...");
        return 0;
    }


    public void pubblicaDocumento(String descrizione, String versione, LocalDate dataPubblicazione){

        DocumentoImplementazionePostgresDAO documentoDAO = new DocumentoImplementazionePostgresDAO();
        documentoDAO.addDocumento(descrizione,versione,dataPubblicazione, idTeam);
    }
    public void stampaClassifica(HashMap<String,Integer> classifica){}
    /*classifica l'ho pensata come una Map cosi che si possa
    facilmente ordinare in base al voto assegnato dal giudice*/

    public boolean checkPartecipanteHaveTeam(Partecipante partecipante, Long idHackathon){
        if (partecipante.getTeam() == null) {
            return false;
        }

        return partecipante.getTeam().stream()
                .anyMatch(team -> team.getHackathon().getId().equals(idHackathon));
    }

    public boolean hasParticipantSentInvite(Partecipante partecipante, Long  idTeam){
        List<Invito> listaInviti = getInvitiPartecipante(partecipante);

        return listaInviti.stream()
                .anyMatch(invito -> invito.getTeam().getId().equals(idTeam));
    }

    private void checkField(String nome, String cognome, String email, String password) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome è obbligatorio.");
        }
        if (cognome == null || cognome.isBlank()) {
            throw new IllegalArgumentException("Il cognome è obbligatorio.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("L'email è obbligatoria.");
        }
        if (!Utils.isValidEmail(email)) {
            throw new IllegalArgumentException("L'email non è valida.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La password è obbligatoria.");
        }
    }


    public String checkPartecipanteInvitatoIsUtenteLoggato(String email){
        if(email.equals(utente.getEmail())){
            return "TU";
        }else {
            return email;
        }
    }



    public String getNomeTeam() {
        return nomeTeam;
    }

    public void setNomeTeam(String nomeTeam) {
        this.nomeTeam = nomeTeam;
    }

    public Utente getUtente(){
        return utente;
    }

    private void setUtente(Utente utente){
        this.utente = utente;
    }

    public ActionButton getActionButton() {
        return actionButton;
    }

    public void setActionButton(ActionButton actionButton) {
        this.actionButton = actionButton;
    }

    public String getNomeButton() {
        return nomeButton;
    }

    public void setNomeButton(String nomeButton) {
        this.nomeButton = nomeButton;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }
}