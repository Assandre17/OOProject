package gui;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import controller.Controller;
import model.Partecipante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePartecipante {
    private JPanel panel1;
    private JButton creaTeamButton;
    private JButton gestioneInvitiButton;
    private JButton inviaRichiestaButton;
    private JButton iscrivitiAdHackathonButton;
    private JButton pubblicaProgressoButton;
    public JFrame homePartecipanteFrame;
    private Controller controller;

    public HomePartecipante(Controller controller) {
        this.controller = controller;
        this.homePartecipanteFrame = new JFrame("HomePartecipante");
        homePartecipanteFrame.setContentPane(panel1);
        homePartecipanteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePartecipanteFrame.pack();

        creaTeamButton.addActionListener(new ActionListener() {
            final ActionButton actionButton = new ActionButton() {
                @Override
                public void doAction() {
                    CreaTeam creaTeam = new CreaTeam(homePartecipanteFrame, controller);
                    creaTeam.creaTeamFrame.setVisible(true);
                }
            };

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: DA FARE IL CHECK
                if (controller.checkPartecipanteHaveTeam((Partecipante) controller.getUtente())) {
                    JOptionPane.showMessageDialog(panel1, "Non puoi creare un team in quanto già sei presente in un altro team!");
                    return;
                }



                controller.setActionButton(actionButton);
                controller.setNomeButton("Avanti");
                HackathonCreatiOrganizzatore hackathonCreatiOrganizzatore = new HackathonCreatiOrganizzatore(homePartecipanteFrame, controller);
                hackathonCreatiOrganizzatore.hcoFrame.setVisible(true);
                homePartecipanteFrame.setVisible(false);

            }
        });

        inviaRichiestaButton.addActionListener(new ActionListener() {

            final ActionButton actionButton = new ActionButton() {
                @Override
                public void doAction() {
                    //INVIA RICHIESTA DI ACCESSO A UN TEAM
                    controller.setNomeButton("Invia richiesta");
                    HackathonCreatiOrganizzatore hackathonCreatiOrganizzatore = new HackathonCreatiOrganizzatore(homePartecipanteFrame, controller);
                    InviaRichiestaPartecipante inviaRichiestaPartecipante = new InviaRichiestaPartecipante(hackathonCreatiOrganizzatore.hcoFrame, controller);
                    inviaRichiestaPartecipante.inviaRichiestaPartecipanteFrame.setVisible(true);
                }
            };

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setActionButton(actionButton);
                controller.setNomeButton("Avanti");
                HackathonCreatiOrganizzatore hackathonCreatiOrganizzatore = new HackathonCreatiOrganizzatore(homePartecipanteFrame, controller);
                hackathonCreatiOrganizzatore.hcoFrame.setVisible(true);
                homePartecipanteFrame.setVisible(false);

            }

        });

        gestioneInvitiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioneInvitiPartecipante gestioneInvitiPartecipante = new GestioneInvitiPartecipante(homePartecipanteFrame, controller);
                gestioneInvitiPartecipante.gestioneInvitiPartecipanteFrame.setVisible(true);
                homePartecipanteFrame.setVisible(false);

            }
        });

        iscrivitiAdHackathonButton.addActionListener(new ActionListener() {
            final ActionButton actionButton = new ActionButton() {
                @Override
                public void doAction() {
                    controller.iscriviti();
                    JOptionPane.showMessageDialog(homePartecipanteFrame, "Iscrizione effettuata con successo!");
                    homePartecipanteFrame.setVisible(true);
                }
            };

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setActionButton(actionButton);
                controller.setNomeButton("Iscriviti");
                HackathonCreatiOrganizzatore hackathonCreatiOrganizzatore = new HackathonCreatiOrganizzatore(homePartecipanteFrame, controller);
                hackathonCreatiOrganizzatore.hcoFrame.setVisible(true);
                homePartecipanteFrame.setVisible(false);

            }
        });

        pubblicaProgressoButton.addActionListener(new ActionListener() {

            final ActionButton actionButton = new ActionButton() {
                @Override
                public void doAction() {
                    //INVIA RICHIESTA DI ACCESSO A UN TEAM
                    HackathonCreatiOrganizzatore hackathonCreatiOrganizzatore = new HackathonCreatiOrganizzatore(homePartecipanteFrame, controller);
                    controller.setNomeButton("Pubblica Progresso");
                    Pubblicazione pubblicazione = new Pubblicazione(controller, hackathonCreatiOrganizzatore.hcoFrame);
                    pubblicazione.pubblicazioneFrame.setVisible(true);
                }
            };

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setActionButton(actionButton);
                controller.setNomeButton("Avanti");
                HackathonCreatiOrganizzatore hackathonCreatiOrganizzatore = new HackathonCreatiOrganizzatore(homePartecipanteFrame, controller);
                hackathonCreatiOrganizzatore.hcoFrame.setVisible(true);
                homePartecipanteFrame.setVisible(false);

            }

        });
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
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("PARTECIPANTE");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        creaTeamButton = new JButton();
        creaTeamButton.setText("Crea Team");
        panel4.add(creaTeamButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inviaRichiestaButton = new JButton();
        inviaRichiestaButton.setText("Invia Richiesta");
        panel4.add(inviaRichiestaButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gestioneInvitiButton = new JButton();
        gestioneInvitiButton.setText("Gestione Inviti");
        panel4.add(gestioneInvitiButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        iscrivitiAdHackathonButton = new JButton();
        iscrivitiAdHackathonButton.setText("Iscriviti ad Hackathon");
        panel4.add(iscrivitiAdHackathonButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pubblicaProgressoButton = new JButton();
        pubblicaProgressoButton.setText("Pubblica progresso");
        panel4.add(pubblicaProgressoButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
