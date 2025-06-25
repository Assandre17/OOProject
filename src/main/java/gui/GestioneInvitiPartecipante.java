package gui;


import controller.Controller;
import model.Hackathon;
import model.Invito;
import model.Partecipante;
import model.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utils.Utils.COLONNE_LISTA_INVITI;


public class GestioneInvitiPartecipante {
    private JPanel panel1;
    private JTable table1;
    public JFrame gestioneInvitiPartecipanteFrame;
    public JFrame homePartecipanteFrame;
    private Controller controller;
    private JButton accettaButton;
    private JButton rifiutaButton;
    private JButton tornaAllaHomeButton;



    public GestioneInvitiPartecipante(JFrame homePartecipanteFrame, Controller controller) {
        this.homePartecipanteFrame = homePartecipanteFrame;
        this.gestioneInvitiPartecipanteFrame = new JFrame("GestioneInvitiPartecipante");
        this.controller = controller;
        gestioneInvitiPartecipanteFrame.setContentPane(panel1);
        gestioneInvitiPartecipanteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gestioneInvitiPartecipanteFrame.pack();

        List<Invito> listaInviti = getMockInviti();

        Object[][] datiTable = new Object[listaInviti.size()][4];

        for (int i = 0; i < listaInviti.size(); i++) {
            datiTable[i][0] = listaInviti.get(i).getId();
            datiTable[i][1] = listaInviti.get(i).getTeam().getNome();
            datiTable[i][2] = listaInviti.get(i).getPartecipanteInvitato().getEmail();
            datiTable[i][3] = listaInviti.get(i).getTeam().getHackathon().getNome();
        }


        DefaultTableModel tabellaInviti = new DefaultTableModel(datiTable, COLONNE_LISTA_INVITI) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> Long.class;
                    default -> String.class;
                };
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        accettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rigaSelezionata = table1.getSelectedRow();
                if (rigaSelezionata < 0) {
                    JOptionPane.showMessageDialog(panel1, "Seleziona un invito da accettare");
                    return;
                }
                Long idInvito = (Long) table1.getValueAt(rigaSelezionata, 0);
                Optional<Invito> invitoDaAccettare = listaInviti.stream()
                        .filter(invito -> invito.getId().equals(idInvito))
                        .findFirst();
                if (invitoDaAccettare.isPresent()) {
                    controller.accettaORifiutaInvitoTeam(true, invitoDaAccettare.get().getPartecipanteInvitato(), invitoDaAccettare.get().getTeam());
                    JOptionPane.showMessageDialog(panel1, "invito Accettato");
                }
            }
        });

        rifiutaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rigaSelezionata = table1.getSelectedRow();
                if (rigaSelezionata < 0) {
                    JOptionPane.showMessageDialog(panel1, "Seleziona un invito da rifiutare");
                    return;
                }
                Long idInvito = (Long) table1.getValueAt(rigaSelezionata, 0);
                Optional<Invito> invitoDaAccettare = listaInviti.stream()
                        .filter(invito -> invito.getId().equals(idInvito))
                        .findFirst();
                if (invitoDaAccettare.isPresent()) {
                    controller.accettaORifiutaInvitoTeam(false, invitoDaAccettare.get().getPartecipanteInvitato(), invitoDaAccettare.get().getTeam());
                    JOptionPane.showMessageDialog(panel1, "invito rifiutato");
                }
            }
        });

        tornaAllaHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePartecipanteFrame.setVisible(true);
                gestioneInvitiPartecipanteFrame.setVisible(false);
                gestioneInvitiPartecipanteFrame.dispose();
            }
        });
        table1.setModel(tabellaInviti);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private List<Invito> getMockInviti() {
        Hackathon hackathon = new Hackathon();
        hackathon.setNome("Hackathon 1");
        Team team = new Team("Team 1");
        team.setHackathon(hackathon);
        Invito invito1 = new Invito();
        invito1.setId(1L);
        invito1.setTeam(team);
        Partecipante partecipante1 = new Partecipante("Marco", "Rossi", "prova", "prova");
        partecipante1.setEmail("prova@prova.it");
        invito1.setPartecipanteInvitato(partecipante1);

        Team team2 = new Team("Team 2");
        team2.setHackathon(hackathon);
        Invito invito2 = new Invito();
        invito2.setId(2L);
        invito2.setTeam(team2);
        Partecipante partecipante2 = new Partecipante("Pippo", "Pluto",  "prova", "prova");
        partecipante2.setEmail("prova1@prova1.it");
        invito2.setPartecipanteInvitato(partecipante2);

        List<Invito> listaInviti = new ArrayList<>();
        listaInviti.add(invito1);
        listaInviti.add(invito2);
        return listaInviti;
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
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("LISTA INVITI RICEVUTI");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        table1 = new JTable();
        JScrollPane scrollPane = new JScrollPane(table1);
        panel2.add(scrollPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        accettaButton = new JButton();
        accettaButton.setText("Accetta");
        panel1.add(accettaButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rifiutaButton = new JButton();
        rifiutaButton.setText("Rifiuta");
        panel1.add(rifiutaButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tornaAllaHomeButton = new JButton();
        tornaAllaHomeButton.setText("torna alla home");
        panel2.add(tornaAllaHomeButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
