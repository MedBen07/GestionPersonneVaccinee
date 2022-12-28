package ui;

import model.RegistrePersonnes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FenListerPersonneVaccinees extends JFrame {
    private JButton btnRetour, btnQuitter, btnSupprimer;
    private JLabel  lblTitre, lblTitre2;
    private JPanel panGeneral, panHaut, panCentre, panBas;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private RegistrePersonnes registre ;

    FenAccueil fenetre = new FenAccueil(registre);

    public FenListerPersonneVaccinees(RegistrePersonnes registre) {
        this.registre = registre;
        setWidgets();
        setLiteners();
    }

    private void setLiteners() {
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                fenetre.setSize(650, 400);
                fenetre.setTitle("Registre de vaccination | service santé ");
                fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenetre.setLocationRelativeTo(null);
                fenetre.setResizable(false);
                fenetre.setVisible(true);
            }
        });
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                if(jTable1.getSelectedRow() != -1)
                {
                    // supprimer la ligne sélectionnée du modèle de table
                    model.removeRow(jTable1.getSelectedRow());
                    JOptionPane.showMessageDialog(null,  "Personne vacciné(e) supprimé avec succès", "Suppression", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void setWidgets() {
        String filePath = "c:\\temp\\dataVaccinee.txt";
        File file = new File(filePath);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [][] {

                }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable1);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //get the first line
            //get the columns name from the first line
            String firstLine = br.readLine().trim();
            String[] columnsName = firstLine.split(",");
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setColumnIdentifiers(columnsName);

            //get line from txt file
            Object[] tableLines = br.lines().toArray();

            //extraction data
            //set data to jtable model
            for(int i = 0; i < tableLines.length; i++){
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split("/");
                model.addRow(dataRow);
            }

        } catch (Exception ex) {
            Logger.getLogger(FenListerPersonneVaccinees.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ajout dans un scrollpane
        JScrollPane p = new JScrollPane(jTable1);
        this.add(p);

    //Zone titre
        panHaut = new JPanel(new GridBagLayout());
        lblTitre = new JLabel("Liste des personnes vaccinées");
        lblTitre.setFont(new Font("Serif", 1, 20));
        lblTitre2 = new JLabel("");

        GridBagConstraints gbcH = new GridBagConstraints();
        gbcH.insets = new Insets(5, 5, 5, 5);

        gbcH.gridx = 0;
        gbcH.gridy = 0;
        panHaut.add(lblTitre, gbcH);
        gbcH.gridx = 0;
        gbcH.gridy = 1;
        panHaut.add(lblTitre2, gbcH);

        //Bouton
        panBas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnRetour = new JButton(" RETOUR ");
        btnRetour.setBackground(Color.gray);
        btnRetour.setForeground(Color.WHITE);

        btnSupprimer = new JButton("SUPPRIMER");
        btnSupprimer.setBackground(Color.orange);

        btnQuitter = new JButton(" QUITTER ");
        btnQuitter.setBackground(Color.gray);
        btnQuitter.setForeground(Color.WHITE);


        //Ajouter les boutons dans le panel
        panBas.add(btnRetour);
        panBas.add(btnSupprimer);
        panBas.add(btnQuitter);

        //Organisation centre

        panCentre = new JPanel(new GridLayout(1,1));
        panCentre.add(p);

        //Panel General
        panGeneral = new JPanel(new BorderLayout());
        panGeneral.add(panHaut, BorderLayout.NORTH);
        panGeneral.add(panCentre, BorderLayout.CENTER);
        panGeneral.add(panBas, BorderLayout.SOUTH);

        //Afficher
        this.setContentPane(panGeneral);


    }

}
