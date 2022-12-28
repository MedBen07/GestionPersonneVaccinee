package ui;
import io.ManipulationFichier;
import model.Personnes;
import model.RegistrePersonnes;
import utils.PersonneDejaPresentException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenAjouterPersonne extends JFrame {
    private JTextField txtNom, txtPrenom, txtAge, txtTel;
    private JLabel lblTitreInfo, lblTitreInfoVaccin, lblTitre, lblTitre2, lblNom, lblPrenon, lblAge, lblTel, lblNombreDose, lblDose1, lblDose2, lblEtat;
    private JRadioButton radio1, radio2;
    private JPanel panelInfo, paneTitrelInfo, paneTitreVaccin, panelInfoVaccin, panGeneral, panHaut, panCentre, panBas;
    private JButton btnRetour, btnQuitter, btnValider;
    private JComboBox cb, cbndose1, cbndose2;
    private RegistrePersonnes registre;

    FenAccueil fenetre = new FenAccueil(registre);

    public FenAjouterPersonne(RegistrePersonnes registre) {
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
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (radio2.isSelected()  ){

                        registre.ajouterPersonne(new Personnes(txtNom.getText(), txtPrenom.getText(),Integer.parseInt(txtAge.getText()), txtTel.getText(), radio2.getText(), cb.getSelectedIndex(), cbndose1.getSelectedItem().toString(), cbndose2.getSelectedItem().toString()));
                        ManipulationFichier.ecrire("c:\\temp\\dataNonVaccinee.txt", registre);

                        JOptionPane.showMessageDialog(null, "Personne non vacciné(e) ajouter avec succes!", "Insertion", JOptionPane.INFORMATION_MESSAGE);
                        txtNom.setText(""); txtPrenom.setText(""); txtAge.setText(""); txtTel.setText("");
                    }
                    else if (radio1.isSelected()){

                        registre.ajouterPersonne(new Personnes(txtNom.getText(), txtPrenom.getText(),Integer.parseInt(txtAge.getText()), txtTel.getText(), radio1.getText(), cb.getSelectedIndex(), cbndose1.getSelectedItem().toString(), cbndose2.getSelectedItem().toString()));

                        ManipulationFichier.ecrire("c:\\temp\\dataVaccinee.txt", registre);
                        JOptionPane.showMessageDialog(null, "Personne vacciné(e) ajouter avec succes!", "Insertion", JOptionPane.INFORMATION_MESSAGE);

                        txtNom.setText(""); txtPrenom.setText(""); txtAge.setText(""); txtTel.setText(""); cb.setSelectedIndex(0); cbndose1.setSelectedIndex(0); cbndose2.setSelectedIndex(0);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (PersonneDejaPresentException ex){
                    JOptionPane.showMessageDialog(null, "Employer Existe dans la liste! "+ex.getPer(), "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs Correctement !", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
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

        //Zone titre
        panHaut = new JPanel(new GridBagLayout());
        lblTitre = new JLabel("Ajouter une personne");
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

        //Information persommel
        paneTitrelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblTitreInfo = new JLabel("           Informations Personnel");
        lblTitreInfo.setFont(new Font("Serif", 1, 15));
        paneTitrelInfo.add(lblTitreInfo);
        JPanel pNom = new JPanel();
        lblNom = new JLabel("Nom:           ");
        txtNom = new JTextField(20);
        pNom.add(lblNom);
        pNom.add(txtNom);

        JPanel pPrenom = new JPanel();
        lblPrenon = new JLabel("Prénom:     ");
        txtPrenom = new JTextField(20);
        pPrenom.add(lblPrenon);
        pPrenom.add(txtPrenom);

        JPanel pAge = new JPanel();
        lblAge = new JLabel("Age:           ");
        txtAge = new JTextField(20);
        pAge.add(lblAge);
        pAge.add(txtAge);

        JPanel pTel = new JPanel();
        lblTel = new JLabel("Téléphone:");
        txtTel = new JTextField(20);
        pTel.add(lblTel);
        pTel.add(txtTel);

        panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfo.add(paneTitrelInfo);
        panelInfo.add(pNom);
        panelInfo.add(pPrenom);
        panelInfo.add(pAge);
        panelInfo.add(pTel);

        //Border info
        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        panelInfo.setBorder(brd);

        //Information vaccin
        paneTitreVaccin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblTitreInfoVaccin = new JLabel("              Informations Vaccin");
        lblTitreInfoVaccin.setFont(new Font("Serif", 1, 15));
        paneTitreVaccin.add(lblTitreInfoVaccin);

        JPanel pRadio = new JPanel();
        lblEtat = new JLabel("Etat:            ");
        radio1 = new JRadioButton("Vacciné(e)");
        radio2 = new JRadioButton("Non vacciné(e)");
        ButtonGroup bgEtat=new ButtonGroup();
        bgEtat.add(radio1); bgEtat.add(radio2);
        pRadio.add(lblEtat);
        pRadio.add(radio1);
        pRadio.add(radio2);

        JPanel pNombreDose = new JPanel();
        lblNombreDose = new JLabel("Nombre de doses:");
        String nbdose[]={"0 Dose","1 Dose","2 Dose"};
        cb=new JComboBox(nbdose);

        pNombreDose.add(lblNombreDose);
        pNombreDose.add(cb);

        JPanel pNomDose1 = new JPanel();
        lblDose1 = new JLabel("Nom de la dose 1: ");
        String ndose1[]={"Sans vaccin","Pfizer-BioNTech","Moderna", "AstraZeneca", "Johnson ", "Novax"};
        cbndose1=new JComboBox(ndose1);
        pNomDose1.add(lblDose1);
        pNomDose1.add(cbndose1);

        JPanel pNomDose2 = new JPanel();
        lblDose2 = new JLabel("Nom de la dose 2: ");
        String ndose2[]={"Sans vaccin","Pfizer-BioNTech","Moderna", "AstraZeneca", "Johnson", "Novax"};
        cbndose2=new JComboBox(ndose2);
        pNomDose2.add(lblDose2);
        pNomDose2.add(cbndose2);

        panelInfoVaccin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoVaccin.add(paneTitreVaccin);
        panelInfoVaccin.add(pRadio);
        panelInfoVaccin.add(pNombreDose);
        panelInfoVaccin.add(pNomDose1);
        panelInfoVaccin.add(pNomDose2);

        //Border infoVaccin
        panelInfoVaccin.setBorder(brd);

        //Bouton
        panBas = new JPanel();
        btnRetour = new JButton("Retour");
        btnRetour.setBackground(Color.gray);
        btnRetour.setForeground(Color.WHITE);

        btnValider = new JButton("Valider");
        btnValider.setBackground(Color.green);

        btnQuitter = new JButton("Quitter");
        btnQuitter.setBackground(Color.gray);
        btnQuitter.setForeground(Color.WHITE);

        //Ajouter les boutons dans le panel

        panBas.add(btnRetour);
        panBas.add(btnValider);
        panBas.add(btnQuitter);

        //Organisation centre
        panCentre = new JPanel(new GridLayout(1,1));
        panCentre.add(panelInfo);
        panCentre.add(panelInfoVaccin);

        //Panel General
        panGeneral = new JPanel(new BorderLayout());
        panGeneral.add(panHaut, BorderLayout.NORTH);
        panGeneral.add(panCentre, BorderLayout.CENTER);
        panGeneral.add(panBas, BorderLayout.SOUTH);

        //Afficher
        this.setContentPane(panGeneral);

    }
}