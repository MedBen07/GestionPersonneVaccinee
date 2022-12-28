package model;

import java.util.Objects;

public class Personnes {

    //Liste des attribut
    private String nom, prenom;
    private int age;
    private String numTel;
    private String etat;
    private int nombreDose;
    private String nomDose1, nomDose2;

    //constructeur

    public Personnes(String nom, String prenom, int age, String numTel, String etat, int nombreDose, String nomDose1, String nomDose2) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.numTel = numTel;
        this.etat = etat;
        this.nombreDose = nombreDose;
        this.nomDose1 = nomDose1;
        this.nomDose2 = nomDose2;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNombreDose() {
        return nombreDose;
    }

    public void setNombreDose(int nombreDose) {
        this.nombreDose = nombreDose;
    }

    public String getNomDose1() {
        return nomDose1;
    }

    public void setNomDose1(String nomDose1) {
        this.nomDose1 = nomDose1;
    }

    public String getNomDose2() {
        return nomDose2;
    }

    public void setNomDose2(String nomDose2) {
        this.nomDose2 = nomDose2;
    }

    @Override
    public String toString() {
        return "Personnes{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", numTel='" + numTel + '\'' +
                ", etat='" + etat + '\'' +
                ", nombreDose=" + nombreDose +
                ", nomDose1='" + nomDose1 + '\'' +
                ", nomDose2='" + nomDose2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personnes)) return false;
        Personnes personnes = (Personnes) o;
        return getAge() == personnes.getAge() && getNombreDose() == personnes.getNombreDose() && getNom().equals(personnes.getNom()) && getPrenom().equals(personnes.getPrenom()) && getNumTel().equals(personnes.getNumTel()) && getEtat().equals(personnes.getEtat()) && getNomDose1().equals(personnes.getNomDose1()) && getNomDose2().equals(personnes.getNomDose2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getPrenom(), getAge(), getNumTel(), getEtat(), getNombreDose(), getNomDose1(), getNomDose2());
    }
}
