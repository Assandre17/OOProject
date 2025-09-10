package gui;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import controller.Controller;
import model.Hackathon;
import model.Organizzatore;
import model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

import static utils.Utils.*;

public class DettagliHackathon {
    public JFrame dettaglioFrame;
    public JFrame hcoFrame;
    private JButton tornaIndietroButton;
    private JPanel panel1;
    private JButton pubblicaButton;
    private JButton invitaGiudiciButton;
    private JButton apriRegistrazioniButton;
    private JButton assegnaVotoButton;
    private JButton visualizzaDocumentiButton;
    private JLabel sedeField;
    private JLabel numPartecipantiField;
    private JLabel numMaxPartField;
    private JLabel inizioIscrizioniField;
    private JLabel fineIscrizioniField;
    private JLabel descrizioneField;
    private JLabel sedeLabel;
    private JLabel numPartecipantiLabel;
    private JLabel numMaxPartecipantiLabel;
    private JLabel inizioIscrizioniLabel;
    private JLabel fineIscrizioniLabel;
    private JLabel descrizioneLabel;
    private Controller controller;
    private Utente utente;

    public DettagliHackathon(Controller controller, JFrame hcoFrame) {
        this.dettaglioFrame = new JFrame("DettagliHackathon");
        this.controller = controller;
        dettaglioFrame.setContentPane(panel1);
        dettaglioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dettaglioFrame.pack();
        assegnaVotoButton.setVisible(false);
        visualizzaDocumentiButton.setVisible(false);
        pubblicaButton.setVisible(false);
        apriRegistrazioniButton.setVisible(false);
        invitaGiudiciButton.setVisible(false);
        System.out.println("DettagliHackathon id: " + controller.getIdHackathon());
        System.out.println("debug: utente è un " + controller.getUtente().getClass());

        Hackathon hackathon = controller.getHackathonById(controller.getIdHackathon());
        sedeField.setText(hackathon.getSede());
        numPartecipantiField.setText(hackathon.getNumPartecipanti().toString());
        numMaxPartField.setText(hackathon.getNumMaxPartecipanti().toString());
        inizioIscrizioniField.setText(hackathon.getInizioIscrizioni() != null ? hackathon.getInizioIscrizioni().toString() : null);
        fineIscrizioniField.setText(hackathon.getFineIscrizioni() != null ? hackathon.getFineIscrizioni().toString() : null);
        descrizioneField.setText(hackathon.getDescrizione());

        tornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hcoFrame.setVisible(true);
                dettaglioFrame.setVisible(false);
                dettaglioFrame.dispose();
            }
        });

        seeUserButtons(controller.getUtente());

        pubblicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pubblicazione pubblicazione = new Pubblicazione(controller, dettaglioFrame);
                pubblicazione.pubblicazioneFrame.setVisible(true);
                dettaglioFrame.setVisible(false);
                dettaglioFrame.dispose();
            }
        });

        assegnaVotoButton.addActionListener(new ActionListener() {
            final ActionButton actionButton = new ActionButton() {
                @Override
                public void doAction() {
                    AssegnaVoto assegnaVoto = new AssegnaVoto(controller);
                    assegnaVoto.assegnaVotoFrame.setVisible(true);
                }
            };

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (hackathon.getDataFine().isAfter(LocalDate.now())) {
                        throw new IllegalAccessException("Non puoi assegnare un voto se l'hackathon non è ancora terminato!");
                    }
                } catch (IllegalAccessException ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage());
                    throw new RuntimeException(ex);
                }
                controller.setActionButton(actionButton);
                controller.setNomeButton("Assegna Voto");
                InviaRichiestaPartecipante inviaRichiestaPartecipante = new InviaRichiestaPartecipante(dettaglioFrame, controller);
                inviaRichiestaPartecipante.inviaRichiestaPartecipanteFrame.setVisible(true);
                dettaglioFrame.setVisible(false);
                dettaglioFrame.dispose();
            }
        });

        visualizzaDocumentiButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ListaDocumenti listaDocumenti = new ListaDocumenti(dettaglioFrame, controller);
                listaDocumenti.listaDocumentiFrame.setVisible(true);
                dettaglioFrame.setVisible(false);
                dettaglioFrame.dispose();
            }
        });


        apriRegistrazioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate fineIscrizioni = hackathon.getDataInizio().minusDays(2);
                try {
                    controller.checkApriRegistrazioni(fineIscrizioni);
                    controller.apriRegistrazioni(hackathon.getId(), LocalDate.now(), fineIscrizioni);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel1, ex.getMessage());
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(panel1, "Registrazioni aperte con successo.");
                hcoFrame.setVisible(true);
                dettaglioFrame.setVisible(false);
                dettaglioFrame.dispose();
            }
        });

        invitaGiudiciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaGiudici listaGiudici = new ListaGiudici(dettaglioFrame, controller);
                listaGiudici.listaGiudiciFrame.setVisible(true);
                dettaglioFrame.setVisible(false);
                dettaglioFrame.dispose();
            }
        });

    }

    private void seeUserButtons(Utente user){
        String userType = getTipo(user);
        switch (userType){
            case TIPO_GIUDICE:
                assegnaVotoButton.setVisible(true);
                pubblicaButton.setVisible(true);
                visualizzaDocumentiButton.setVisible(true);
                break;
            case TIPO_ORGANIZZATORE:
                apriRegistrazioniButton.setVisible(true);
                invitaGiudiciButton.setVisible(true);
                break;
            case TIPO_PARTECIPANTE:
                pubblicaButton.setVisible(true);
                visualizzaDocumentiButton.setVisible(true);
                break;
            default:
                break;
        }
    }

    private boolean seeOrganizzatoreButtons(Utente utente) {
        if (utente instanceof Organizzatore) {

            return true;
        }
        return false;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 9, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(2, 0, 1, 9, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pubblicaButton = new JButton();
        pubblicaButton.setText("Pubblica");
        panel3.add(pubblicaButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        assegnaVotoButton = new JButton();
        assegnaVotoButton.setText("Assegna voto");
        panel3.add(assegnaVotoButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apriRegistrazioniButton = new JButton();
        apriRegistrazioniButton.setText("Apri Registrazioni");
        panel3.add(apriRegistrazioniButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        visualizzaDocumentiButton = new JButton();
        visualizzaDocumentiButton.setText("Visualizza documenti");
        panel3.add(visualizzaDocumentiButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        invitaGiudiciButton = new JButton();
        invitaGiudiciButton.setText("Invita Giudici");
        panel3.add(invitaGiudiciButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tornaIndietroButton = new JButton();
        tornaIndietroButton.setText("torna indietro");
        panel4.add(tornaIndietroButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Dettagli Hackathon");
        panel1.add(label1, new GridConstraints(0, 0, 1, 9, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel5, new GridConstraints(1, 0, 1, 9, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        numMaxPartecipantiLabel = new JLabel();
        numMaxPartecipantiLabel.setText("numero massimo partecipanti:");
        panel5.add(numMaxPartecipantiLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inizioIscrizioniLabel  = new JLabel();
        inizioIscrizioniLabel.setText("inizio iscrizioni:");
        panel5.add(inizioIscrizioniLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fineIscrizioniLabel = new JLabel();
        fineIscrizioniLabel.setText("fine iscrizioni:");
        panel5.add(fineIscrizioniLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descrizioneLabel = new JLabel();
        descrizioneLabel.setText("descrizione:");
        panel5.add(descrizioneLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inizioIscrizioniField = new JLabel();
        panel5.add(inizioIscrizioniField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fineIscrizioniField = new JLabel();
        panel5.add(fineIscrizioniField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descrizioneField = new JLabel();
        panel5.add(descrizioneField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sedeLabel = new JLabel();
        sedeLabel.setText("sede:");
        panel5.add(sedeLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(34, 55), null, 0, false));
        sedeField = new JLabel();
        panel5.add(sedeField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numMaxPartField = new JLabel();
        panel5.add(numMaxPartField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numPartecipantiLabel = new JLabel();
        numPartecipantiLabel.setText("numero partecipanti:");
        panel5.add(numPartecipantiLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numPartecipantiField = new JLabel();
        panel5.add(numPartecipantiField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
