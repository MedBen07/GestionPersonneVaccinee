package io;
import model.Personnes;
import model.RegistrePersonnes;
import utils.PersonneDejaPresentException;

import java.io.*;

public class ManipulationFichier {

    public static void lire(String fichier, RegistrePersonnes registre) {
        //lire du fichier
        File file = new File(fichier);

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            //lecture du fichier
            String c;
            while ((c = br.readLine()) != null) {
                //System.out.println(c);
                Personnes personnes = parsePersonnes(c);
                //Ajouter objet dans registre
                registre.ajouterPersonne(personnes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PersonneDejaPresentException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


     public static Personnes parsePersonnes(String s) {
        //deserialisation
        String[] token = s.split(" ");
        int age = Integer.parseInt(token[2]);
        int dose = Integer.parseInt(token[5]);
        return new Personnes(token[0], token[1], age, token[3], token[4], dose, token[6], token[7]);
    }

    public static void ecrire(String fichier, RegistrePersonnes registre) {
        //ecrire dans un fichier
        File file = new File(fichier);
        FileWriter fw = null;
        BufferedWriter bw = null;
        String contenu;
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            for (Personnes personnes : registre.getRegistre()) {
                contenu = formerLigne(personnes);
                bw.write(contenu);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String formerLigne(Personnes personnes) {
        //Serialisation
        return personnes.getNom() + "/" + personnes.getPrenom() + "/" + personnes.getAge()+ "/" + personnes.getNumTel()+ "/" + personnes.getEtat()+ "/" + personnes.getNombreDose()+ "/" + personnes.getNomDose1()+ "/" + personnes.getNomDose2();
    }
}
