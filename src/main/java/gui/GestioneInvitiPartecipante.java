package gui;


import com.intellij.uiDesigner.core.GridLayoutManager;
import controller.Controller;
import model.Invito;
import model.Partecipante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        List<Invito> listaInviti = controller.getInvitiPartecipante((Partecipante) controller.getUtente());

        Object[][] datiTable = new Object[listaInviti.size()][4];

        for (int i = 0; i < listaInviti.size(); i++) {
            datiTable[i][0] = listaInviti.get(i).getId();
            datiTable[i][1] = listaInviti.get(i).getTeam().getNome();
            datiTable[i][2] = controller.checkPartecipanteInvitatoIsUtenteLoggato(listaInviti.get(i).getPartecipanteInvitato().getEmail());
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
                    if (invitoDaAccettare.get().getPartecipanteInvitato().getId().equals(controller.getUtente().getId()) && controller.checkPartecipanteHaveTeam((Partecipante) controller.getUtente(), invitoDaAccettare.get().getTeam().getHackathon().getId())) {
                        JOptionPane.showMessageDialog(panel1, "Non puoi accettare l'invito di un team in quanto già sei presente in un altro team!");
                        return;
                    }
                    controller.accettaORifiutaInvitoTeam(true, invitoDaAccettare.get().getPartecipanteInvitato(), invitoDaAccettare.get().getTeam().getId(), invitoDaAccettare.get().getId());
                    JOptionPane.showMessageDialog(panel1, "Invito Accettato");
                    homePartecipanteFrame.setVisible(true);
                    gestioneInvitiPartecipanteFrame.setVisible(false);
                    gestioneInvitiPartecipanteFrame.dispose();
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
                    controller.accettaORifiutaInvitoTeam(false, invitoDaAccettare.get().getPartecipanteInvitato(), invitoDaAccettare.get().getTeam().getId(), invitoDaAccettare.get().getId());
                    JOptionPane.showMessageDialog(panel1, "Invito rifiutato");
                    homePartecipanteFrame.setVisible(true);
                    gestioneInvitiPartecipanteFrame.setVisible(false);
                    gestioneInvitiPartecipanteFrame.dispose();
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
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
