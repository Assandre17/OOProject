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
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import static utils.Utils.*;

/**
 * The type Controller.
 */
public class Controller {
    private String nomeTeam;
    private Utente utente;
    private Long idHackathon;
    private Long idTeam;
    private Long idDocumento;
    private ActionButton actionButton;
    private String nomeButton;

    /**
     * Gets id hackathon.
     * getter
     * @return the id hackathon
     */
    public Long getIdHackathon() {
        return idHackathon;
    }

    /**
     * Sets id hackathon.
     * setter
     * @param idHackathon the id hackathon
     */
    public void setIdHackathon(Long idHackathon) {
        this.idHackathon = idHackathon;
    }

    /**
     * Crea hackathon.
     *
     * @param sede               the sede
     * @param nome               the nome
     * @param numMaxPartecipanti the num max partecipanti
     * @param inizioIscrizioni   the inizio iscrizioni
     * @param fineIscrizioni     the fine iscrizioni
     * @param descrizione        the descrizione
     * @throws SQLException the sql exception
     */
    public void creaHackathon(String sede, String nome, String numMaxPartecipanti, String inizioIscrizioni, String fineIscrizioni, String descrizione) throws SQLException {
        System.out.println("creazione Hackathon in corso...");

        checkFieldHackathon(sede,nome,descrizione,numMaxPartecipanti);
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

        long idor = utente.getId();

        Organizzatore organizzatore = new Organizzatore();
        organizzatore.setId(idor);

        Hackathon hackathon = new Hackathon();
        hackathon.setSede(sede);
        hackathon.setNome(nome);
        hackathon.setOrganizzatore(organizzatore);
        hackathon.setNumMaxPartecipanti(Integer.parseInt(numMaxPartecipanti));
        hackathon.setDataInizio(LocalDate.parse(inizioIscrizioni));
        hackathon.setDataFine(LocalDate.parse(fineIscrizioni));
        hackathon.setDescrizione(descrizione);
        OrganizzatoreImplementazionePostgresDAO organizzatoreDAO = new OrganizzatoreImplementazionePostgresDAO();
        organizzatoreDAO.insertHackathon(hackathon);
    }

    /**
     * Vedi hackathon creati list.
     *
     * @param user the user
     * @return the list
     * @throws SQLException the sql exception
     */
    public List<Hackathon> vediHackathonCreati(Utente user) throws SQLException {
        System.out.println("visualizzazione Hackathon creati");
        OrganizzatoreImplementazionePostgresDAO organizzatoreDAO = new OrganizzatoreImplementazionePostgresDAO();
        return organizzatoreDAO.getListHackathon(user); //visualizzazione hackathon creati fatta
    }

    /**
     * Gets all hackathon.
     *
     * @return the all hackathon
     */
    public List<Hackathon> getAllHackathon() {
        HackathonImplementazionePostgresDAO hackathonDAO = new HackathonImplementazionePostgresDAO();
        return hackathonDAO.getAllHackathon();
    }


    /**
     * Gets hackathon where partecipante joined.
     *
     * @param user the user
     * @return the hackathon partecipante
     * @throws SQLException the sql exception
     */
    public List<Hackathon> getHackathonPartecipante(Partecipante user) throws SQLException {
        System.out.println("visualizzazione Hackathon del partecipante");
        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        return partecipanteDAO.getListHackathon(user);
    }

    /**
     * Gets hackathon where giudice joined.
     *
     * @param user the user
     * @return the hackathon giudice
     * @throws SQLException the sql exception
     */
    public List<Hackathon> getHackathonGiudice(Giudice user) throws SQLException {
        System.out.println("visualizzazione Hackathon del giudice");
        GiudiceImplementazionePostgresDAO giudiceDAO = new GiudiceImplementazionePostgresDAO();
        return giudiceDAO.getListHackathon(user);
    }

    /**
     * Gets hackathon liberi.
     *
     * @param user the user
     * @return the hackathon liberi
     * @throws SQLException the sql exception
     */
    public List<Hackathon> getHackathonLiberi(Partecipante user) throws SQLException {
        System.out.println("visualizzazione Hackathon  a cui può iscriversi il partecipante");
        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        return partecipanteDAO.getHackathonLiberi(user);

    }

    /**
     * Gets team by id hackathon.
     *
     * @param idHackathon the id hackathon
     * @return the team by id hackathon
     */
    public List<Team> getTeamByIdHackathon(Long idHackathon) {
        System.out.println("visualizzazione dei team appartenenti all'hackathon con id " + idHackathon);
        TeamImplementazionePostgresDAO teamDAO = new TeamImplementazionePostgresDAO();
        return teamDAO.getTeamByIdHackathon(idHackathon);
    }

    /**
     * Gets team by id.
     *
     * @param id the id
     * @return the team by id
     */
    public Team getTeamById(Long id) {
        TeamImplementazionePostgresDAO teamDAO = new TeamImplementazionePostgresDAO();
        return teamDAO.getTeamById(id);
    }

    /**
     * Get partecipanti without team list.
     *
     * @return the list
     */
    public List<Partecipante> getPartecipantiWithoutTeam(){
        System.out.println("visualizzazione partecipanti senza team");
        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        return partecipanteDAO.getPartecipantiWithoutTeam(getUtente().getId(), getIdHackathon());
    }

    /**
     * Get list giudici list.
     *
     * @return the list
     */
    public List<Giudice> getListGiudici(){
        OrganizzatoreImplementazionePostgresDAO organizzatoreDAO = new OrganizzatoreImplementazionePostgresDAO();
        return organizzatoreDAO.getListGiudice(getIdHackathon());
    }

    /**
     * Apri registrazioni allows registrazioni from partecipanti.
     *
     * @param idHackathon      the id hackathon
     * @param inizioIscrizioni the inizio iscrizioni
     * @param fineIscrizioni   the fine iscrizioni
     * @throws SQLException the sql exception
     */
    public void apriRegistrazioni(Long idHackathon, LocalDate inizioIscrizioni, LocalDate fineIscrizioni) throws SQLException {
        HackathonImplementazionePostgresDAO hackathonDAO = new HackathonImplementazionePostgresDAO();
        hackathonDAO.apriRegistrazioni(idHackathon, inizioIscrizioni, fineIscrizioni);
    }

    /**
     * Registrati.
     *
     * @param nome     the nome
     * @param cognome  the cognome
     * @param email    the email
     * @param password the password
     * @param tipo     the tipo
     * @throws InstanceAlreadyExistsException the instance already exists exception
     */
    public void registrati(String nome,String cognome, String email, String password, String tipo) throws InstanceAlreadyExistsException {
        checkField(nome, cognome, email, password);

        UtenteImplentazionePostgresDAO utenteDAO = new UtenteImplentazionePostgresDAO();
        //verifica se la mail inserita in fase di registrazione è già esistente
        if(Objects.nonNull(utenteDAO.getUtenteByEmail(email))){
            throw new InstanceAlreadyExistsException("Utente già esistente nel sistema");
        }

        Utente utenteRegistrato = Utils.getUtenteModel(null, nome,cognome,email,password,tipo,null, null);

        utenteDAO.insertUtente(utenteRegistrato);
    }

    /**
     * Accedi utente.
     *
     * @param email    the email
     * @param password the password
     * @return the utente
     * @throws InstanceNotFoundException the instance not found exception
     */
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

    /**
     * Iscriviti.
     *
     * @param idPartecipante the id partecipante
     * @param idHackathon    the id hackathon
     */
    public void iscriviti(Long idPartecipante, Long idHackathon){
        System.out.println("iscrizione ad Hackathon in corso...");

        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();
        partecipanteDAO.addPartecipanteToHackathon(idPartecipante,idHackathon);

        HackathonImplementazionePostgresDAO hackathonDAO = new HackathonImplementazionePostgresDAO();
        hackathonDAO.addNumPartecipante(idHackathon);

    }

    /**
     * Crea team.
     *
     * @param nome              the nome
     * @param listaPartecipanti the lista partecipanti
     */
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
        partecipanti.forEach(partecipante -> invitoDAO.insertInvito(partecipante.getId(), idTeam, null, TIPO_TEAM));

    }

    /**
     * Richiesta ingresso team.
     *
     * @param partecipante the partecipante
     * @param team         the team
     */
    public void richiestaIngressoTeam(Partecipante partecipante, Team team){
        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        invitoDAO.insertInvito(partecipante.getId(), team.getId(), null, TIPO_PARTECIPANTE);
    }

    /**
     * Get inviti partecipante list.
     *
     * @param partecipante the partecipante
     * @return the list
     */
    public List<Invito> getInvitiPartecipante(Partecipante partecipante){
        System.out.println("visualizzazione inviti del partecipante");
        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        return invitoDAO.getInvitiPartecipante(partecipante);
    }

    /**
     * Gets inviti giudice.
     *
     * @param giudice the giudice
     * @return the inviti giudice
     */
    public List<Invito> getInvitiGiudice(Giudice giudice) {
        System.out.println("visualizzazione inviti del partecipante");
        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        return invitoDAO.getInvitiGiudice(giudice);
    }


    /**
     * Gets documenti by id hackathon.
     *
     * @param idHackathon the id hackathon
     * @return the documenti by id hackathon
     */
    public List<Documento> getDocumentiByIdHackathon(Long idHackathon) {
        System.out.println("visualizzazione documenti dell'hackathon con id: " + idHackathon);
        DocumentoImplementazionePostgresDAO documentoDAO =  new DocumentoImplementazionePostgresDAO();
        return documentoDAO.getDocumentiByIdHackathon(idHackathon);
    }

    /**
     * Gets hackathon by id.
     *
     * @param idHackathon the id hackathon
     * @return the hackathon by id
     */
    public Hackathon getHackathonById(Long idHackathon) {
        try {
            HackathonImplementazionePostgresDAO hackathonDAO = new HackathonImplementazionePostgresDAO();
            Hackathon hackathon = hackathonDAO.getHackathonById(idHackathon);

            if (hackathon == null) {
                throw new InstanceNotFoundException("Hackathon non trovato con id: " + idHackathon);
            }

            return hackathon;
        } catch (InstanceNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets documenti by id team.
     *
     * @param idTeam the id team
     * @return the documenti by id team
     */
    public List<Documento> getDocumentiByIdTeam(Long idTeam) {
        System.out.println("visualizzazione documenti del team con id: " + idTeam);
        DocumentoImplementazionePostgresDAO documentoDAO =  new DocumentoImplementazionePostgresDAO();
        return documentoDAO.getDocumentiByIdTeam(idTeam);
    }

    /**
     * Gets documento by id.
     *
     * @param idDocumento the id documento
     * @return the documento by id
     */
    public Documento getDocumentoById(Long idDocumento) {
        DocumentoImplementazionePostgresDAO documentoDAO =  new DocumentoImplementazionePostgresDAO();
        return documentoDAO.getDocumentoById(idDocumento);
    }

    /**
     * Accetta o rifiuta invito team.
     *
     * @param decisione    the decisione
     * @param partecipante the partecipante
     * @param idTeam       the id team
     * @param idInvito     the id invito
     */
    public void accettaORifiutaInvitoTeam(boolean decisione,Partecipante partecipante, Long idTeam, Long idInvito){
        System.out.println("gestione invito in corso...");

        PartecipanteImplementazionePostgresDAO partecipanteDAO = new PartecipanteImplementazionePostgresDAO();

        String statoInvito;

        if(decisione){
            statoInvito = Utils.STATO_ACCETTATO;
            partecipanteDAO.addTeamToPartecipante(partecipante.getId(), idTeam);
        }else{
            statoInvito = Utils.STATO_RIFIUTATO;
        }

        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        invitoDAO.updateStatoInvito(idInvito,statoInvito);


    }

    /**
     * Accetta o rifiuta invito organizzatore.
     *
     * @param decisione   the decisione
     * @param giudice     the giudice
     * @param idHackathon the id hackathon
     * @param idInvito    the id invito
     */
    public void accettaORifiutaInvitoOrganizzatore(boolean decisione, Giudice giudice, Long idHackathon, Long idInvito) {
        System.out.println("gestione invito in corso...");

        GiudiceImplementazionePostgresDAO giudiceDAO = new GiudiceImplementazionePostgresDAO();

        String statoInvito;

        if(decisione){
            statoInvito = Utils.STATO_ACCETTATO;
            giudiceDAO.addHackathonToGiudice(giudice.getId(), idHackathon);
        }else{
            statoInvito = Utils.STATO_RIFIUTATO;
        }

        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        invitoDAO.updateStatoInvito(idInvito,statoInvito);

    }

    /**
     * Pubblica problema.
     *
     * @param problema    the problema
     * @param idHackathon the id hackathon
     */
    public void pubblicaProblema(String problema, Long idHackathon){
        System.out.println("pubblica problema in corso...");
        HackathonImplementazionePostgresDAO hackathonDAO = new HackathonImplementazionePostgresDAO();
        hackathonDAO.addProblemaToHackathon(problema, idHackathon);

    }

    /**
     * Assegna voto.
     *
     * @param valutazione the valutazione
     * @param commento    the commento
     * @param idTeam      the id team
     */
    public void assegnaVoto(Integer valutazione, String commento, Long idTeam){
        System.out.println("pubblica voto in corso...");
        VotoImplementazionePostgresDAO votoDAO = new VotoImplementazionePostgresDAO();
        votoDAO.insertVoto(valutazione,commento, idTeam);

    }


    /**
     * Pubblica documento.
     *
     * @param descrizione       the descrizione
     * @param versione          the versione
     * @param dataPubblicazione the data pubblicazione
     */
    public void pubblicaDocumento(String descrizione, String versione, LocalDate dataPubblicazione){

        DocumentoImplementazionePostgresDAO documentoDAO = new DocumentoImplementazionePostgresDAO();
        documentoDAO.addDocumento(descrizione,versione,dataPubblicazione, idTeam);
    }

    /**
     * Pubblica commento.
     *
     * @param commento    the commento
     * @param idDocumento the id documento
     */
    public void pubblicaCommento(String commento, Long idDocumento) {
        DocumentoImplementazionePostgresDAO documentoDAO = new DocumentoImplementazionePostgresDAO();
        documentoDAO.addCommentoToDocumento(commento, idDocumento);
    }

    /**
     * Stampa classifica list.
     *
     * @param idHackathon the id hackathon
     * @return the list
     */
    public List<Team> stampaClassifica(Long idHackathon){
       List<Team> listaTeam =  getTeamByIdHackathon(idHackathon);

        //inserisce le medie in una map chiave valore (la chiave è l'id del team che ha quella media voto) e
        // poi compara tutte le medie voto prendendosi la media voto con quell id team dalla map
        Map<Long, Double> medie = new HashMap<>();
        for (Team team : listaTeam) {
            List<Voto> votiTeam = getVotiByIdTeam(team.getId());
            medie.put(team.getId(), calculateMediaVoto(votiTeam));
        }

        listaTeam.sort((t1, t2) -> medie.get(t2.getId()).compareTo(medie.get(t1.getId())));

        return listaTeam;


    }

    /**
     * Check partecipante have team boolean.
     *
     * @param partecipante the partecipante
     * @param idHackathon  the id hackathon
     * @return the boolean
     */
    public boolean checkPartecipanteHaveTeam(Partecipante partecipante, Long idHackathon){
        UtenteImplentazionePostgresDAO utenteDAO = new UtenteImplentazionePostgresDAO();
        partecipante =(Partecipante) utenteDAO.getUtenteByEmailAndPassword(partecipante.getEmail(), partecipante.getPassword());
        if (partecipante.getTeam() == null) {
            return false;
        }

        return partecipante.getTeam().stream()
                .anyMatch(team -> team.getHackathon().getId().equals(idHackathon));
    }

    /**
     * Has participant sent invite boolean.
     *
     * @param partecipante the partecipante
     * @param idTeam       the id team
     * @return the boolean
     */
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

    private void checkFieldHackathon(String sede, String nome, String descrizione, String numMaxPartecipanti) {
        if (sede == null || sede.isBlank()) {
            throw new IllegalArgumentException("La sede è obbligatoria.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome è obbligatorio.");
        }
        if (descrizione == null || descrizione.isBlank()) {
            throw new IllegalArgumentException("La descrizione è obbligatoria.");
        }

        if (numMaxPartecipanti == null || numMaxPartecipanti.isBlank()) {
            throw new IllegalArgumentException("il numero massimo partecipanti è obbligatorio.");
        }
    }

    /**
     * Check apri registrazioni.
     *
     * @param fineIscrizioni the fine iscrizioni
     */
    public void checkApriRegistrazioni(LocalDate fineIscrizioni){
        LocalDate today = LocalDate.now();
        if (today.isAfter(fineIscrizioni)) {
            throw new IllegalArgumentException("Errore, impossibile aprire le registrazioni meno di due giorni prima dell'inizio dell'Hackathon!");
        }
    }

    /**
     * Check invitato is utente loggato string.
     *
     * @param email the email
     * @return the string
     */
    public String checkInvitatoIsUtenteLoggato(String email){
        if(email.equals(utente.getEmail())){
            return "TU";
        }else {
            return email;
        }
    }

    /**
     * Calculate media voto double.
     *
     * @param voti the voti
     * @return the double
     */
    public Double calculateMediaVoto(List<Voto> voti){
        return voti.stream()
                .map(Voto::getValutazione)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

    }


    /**
     * Gets medie.
     *
     * @param listaTeam the lista team
     * @return the medie
     */
    public Map<Long, Double> getMedie(List<Team> listaTeam) {
        Map<Long, Double> medie = new HashMap<>();
        for (Team team : listaTeam) {
            List<Voto> votiTeam = getVotiByIdTeam(team.getId());
            medie.put(team.getId(), calculateMediaVoto(votiTeam));
        }
        return medie;
    }


    /**
     * Gets nome team.
     *
     * @return the nome team
     */
    public String getNomeTeam() {
        return nomeTeam;
    }

    /**
     * Sets nome team.
     *
     * @param nomeTeam the nome team
     */
    public void setNomeTeam(String nomeTeam) {
        this.nomeTeam = nomeTeam;
    }

    /**
     * Get utente utente.
     *
     * @return the utente
     */
    public Utente getUtente(){
        UtenteImplentazionePostgresDAO utenteDAO = new UtenteImplentazionePostgresDAO();
        return utenteDAO.getUtenteByEmailAndPassword(utente.getEmail(), utente.getPassword());
    }

    private void setUtente(Utente utente){
        this.utente = utente;
    }

    /**
     * Gets action button.
     *
     * @return the action button
     */
    public ActionButton getActionButton() {
        return actionButton;
    }

    /**
     * Sets action button.
     *
     * @param actionButton the action button
     */
    public void setActionButton(ActionButton actionButton) {
        this.actionButton = actionButton;
    }

    /**
     * Gets nome button.
     *
     * @return the nome button
     */
    public String getNomeButton() {
        return nomeButton;
    }

    /**
     * Sets nome button.
     *
     * @param nomeButton the nome button
     */
    public void setNomeButton(String nomeButton) {
        this.nomeButton = nomeButton;
    }

    /**
     * Gets id team.
     *
     * @return the id team
     */
    public Long getIdTeam() {
        return idTeam;
    }

    /**
     * Sets id team.
     *
     * @param idTeam the id team
     */
    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    /**
     * Gets id documento.
     *
     * @return the id documento
     */
    public Long getIdDocumento() {return idDocumento;}

    /**
     * Sets id documento.
     *
     * @param idDocumento the id documento
     */
    public void setIdDocumento(Long idDocumento) {this.idDocumento = idDocumento;}

    /**
     * Invita giudice.
     *
     * @param idHackathon    the id hackathon
     * @param giudiciChecked the giudici checked
     */
    public void invitaGiudice(Long idHackathon, List<Giudice> giudiciChecked) {
        InvitoImplementazionePostgresDAO invitoDAO = new InvitoImplementazionePostgresDAO();
        giudiciChecked.forEach(giudice -> invitoDAO.insertInvito(giudice.getId(), null, idHackathon, TIPO_ORGANIZZATORE));
    }

    /**
     * Gets voti by id team.
     *
     * @param id the id
     * @return the voti by id team
     */
    public List<Voto> getVotiByIdTeam(Long id) {
        VotoImplementazionePostgresDAO votoDao = new VotoImplementazionePostgresDAO();
        return votoDao.getVotiByIdTeam(id);
    }
}