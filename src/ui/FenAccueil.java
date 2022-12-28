package ui;

import model.RegistrePersonnes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FenAccueil extends JFrame {

    private JLabel lblTitre1, lblTitre2, lblImage, txtheure, txtdate, lblheure, lbldate;
    private JButton btnAjouterdesPersonne, btnQuitter, btnListernonVaccinees, btnListerVaccinees, btnModifier ;
    private JPanel  panGeneral, panHaut, pImage, panMenue, panBas;
    private RegistrePersonnes registre ;

    public FenAccueil(RegistrePersonnes registre){
        this.registre = registre;
        setWidgets();
        setLiteners();
        DateHeure();
    }
    public void DateHeure(){
        Thread tr = new Thread(){
            public void run(){
                for (;;){
                    try{
                        Calendar cal = new GregorianCalendar();
                        //Date
                        int annee = cal.get(Calendar.YEAR);
                        int moi = cal.get(Calendar.MONTH);
                        int jour = cal.get(Calendar.DAY_OF_MONTH);

                        //Heure
                        int heure = cal.get(Calendar.HOUR_OF_DAY);
                        int minute = cal.get(Calendar.MINUTE);
                        int seconde = cal.get(Calendar.SECOND);

                        txtdate.setText(jour+"-"+(moi+1)+"-"+annee);
                        txtheure.setText(heure+":"+minute+":"+seconde);
                        sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        tr.start();
    }

    private void setLiteners() {
        btnAjouterdesPersonne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FenAjouterPersonne ajouterPersonne = new FenAjouterPersonne(registre);
                ajouterPersonne.setVisible(true);
                ajouterPersonne.setSize(650, 400);
                ajouterPersonne.setTitle("Registre de vaccination | service santé ");
                ajouterPersonne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ajouterPersonne.setLocationRelativeTo(null);
                ajouterPersonne.setResizable(false);
                setVisible(false);
            }
        });

        btnListernonVaccinees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RegistrePersonnes registre = new RegistrePersonnes();
                FenListerPersonnenonVaccinees listerPersonnenonVaccinees = new FenListerPersonnenonVaccinees(registre);
                listerPersonnenonVaccinees.setVisible(true);
                listerPersonnenonVaccinees.setSize(750, 400);
                listerPersonnenonVaccinees.setTitle("Liste des personnes non vaccinées | service santé ");
                listerPersonnenonVaccinees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                listerPersonnenonVaccinees.setLocationRelativeTo(null);
                listerPersonnenonVaccinees.setResizable(false);
                setVisible(false);
            }
        });

        btnListerVaccinees.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                RegistrePersonnes registre = new RegistrePersonnes();
                FenListerPersonneVaccinees listerPersonnesVaccinees = new FenListerPersonneVaccinees(registre);
                listerPersonnesVaccinees.setVisible(true);
                listerPersonnesVaccinees.setSize(750, 400);
                listerPersonnesVaccinees.setTitle("Liste des personnes vaccinées | service santé ");
                listerPersonnesVaccinees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                listerPersonnesVaccinees.setLocationRelativeTo(null);
                listerPersonnesVaccinees.setResizable(false);
                setVisible(false);

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
        panGeneral = new JPanel(new BorderLayout());

        //Date et heure
        txtheure = new JLabel();
        txtdate = new JLabel();

        txtheure.setFont(new java.awt.Font("Serif", 1, 15)); // NOI18N
        txtheure.setForeground(new java.awt.Color(0, 153, 153));
        lblheure = new JLabel("  Heure:");
        lblheure.setFont(new java.awt.Font("Serif", 1, 15));
        lblheure.setForeground(new java.awt.Color(0, 153, 153));

        txtdate.setFont(new java.awt.Font("Serif", 1, 15)); // NOI18N
        txtdate.setForeground(new java.awt.Color(0, 153, 153));
        lbldate = new JLabel("Date:");
        lbldate.setFont(new java.awt.Font("Serif", 1, 15));
        lbldate.setForeground(new java.awt.Color(0, 153, 153));
        //txtdate.setText("Date");

        //Zone titre
        lblTitre1 = new JLabel("Registre de vaccination ");
        lblTitre1.setFont(new java.awt.Font("Serif", 1, 25));
        lblTitre1.setForeground(new java.awt.Color(0, 153, 153));

        lblTitre2 = new JLabel("SERVICE SANTE");
        lblTitre2.setFont(new java.awt.Font("Serif", 1, 15));
        lblTitre2.setForeground(new java.awt.Color(0, 153, 153));
        panHaut = new JPanel(new GridBagLayout());

        //GridbagLayout disposition Haut
        GridBagConstraints gbcH = new GridBagConstraints();
        gbcH.insets = new Insets(5, 5, 5, 5);

        gbcH.gridx = 0;
        gbcH.gridy = 0;
        panHaut.add(lblTitre1, gbcH);

        gbcH.gridx = 0;
        gbcH.gridy = 1;
        panHaut.add(lblTitre2, gbcH);


        //Prise en charge image
        lblImage = new JLabel(new ImageIcon((getClass().getResource("/images/Logo-vaccination2-COVID-bleu.png"))));
        pImage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pImage.add(lblImage);

        //Button menu
        btnAjouterdesPersonne = new JButton("AJOUTER UNE PERSONNE                           ");
        btnAjouterdesPersonne.setBackground(Color.gray);
        btnAjouterdesPersonne.setForeground(Color.WHITE);

        btnListerVaccinees = new JButton("LISTER LES PERSONNES VACCINEES        ");
        btnListerVaccinees.setBackground(Color.gray);
        btnListerVaccinees.setForeground(Color.WHITE);

        btnListernonVaccinees = new JButton("LISTER LES PERSONNES NON VACCINEES");
        btnListernonVaccinees.setBackground(Color.gray);
        btnListernonVaccinees.setForeground(Color.WHITE);

        //btnModifier = new JButton("Modifier l'état d'une personne          ");
        btnQuitter = new JButton(" QUITTER ");
        btnQuitter.setBackground(Color.darkGray);
        btnQuitter.setForeground(Color.WHITE);

        panMenue = new JPanel(new GridBagLayout());

        //GridbagLayout disposition Menue
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panMenue.add(btnAjouterdesPersonne, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panMenue.add(btnListerVaccinees, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panMenue.add(btnListernonVaccinees, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panMenue.add(btnQuitter, gbc);

        //pan Date
        //GridbagLayout disposition Haut
        panBas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panBas.add(lbldate);
        panBas.add(txtdate);
        panBas.add(lblheure);
        panBas.add(txtheure);

        //Border Generale
        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        panGeneral.setBorder(brd);

        //Ajouter les panels
        panGeneral.add(panHaut, BorderLayout.NORTH);
        panGeneral.add(panMenue, BorderLayout.WEST);
        panGeneral.add(pImage, BorderLayout.EAST);
        panGeneral.add(panBas, BorderLayout.SOUTH);

        this.setContentPane(panGeneral);
    }
}

