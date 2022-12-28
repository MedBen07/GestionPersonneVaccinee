package control;
import io.ManipulationFichier;
import model.RegistrePersonnes;
import ui.FenAccueil;


import javax.swing.*;

public class AppCtr {
    public static void main(String[] args) {

        RegistrePersonnes registre = new RegistrePersonnes();
//        ManipulationFichier.lire("c:\\temp\\dataVaccinee.txt",registre);
//        ManipulationFichier.lire("c:\\temp\\dataNonVaccinee.txt",registre);
        FenAccueil fenetre = new FenAccueil(registre);
        fenetre.setSize(650, 400);
        fenetre.setTitle("Registre de vaccination | service santé ");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    }
}
