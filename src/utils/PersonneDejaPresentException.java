package utils;

import model.Personnes;

public class PersonneDejaPresentException extends Exception {
    private Personnes per;

    public PersonneDejaPresentException(Personnes per) {
        this.per = per;
    }

    public PersonneDejaPresentException(String message, Personnes per) {
        super(message);
        this.per = per;
    }

    public Personnes getPer() {
        return per;
    }

    public void setPer(Personnes per) {
        this.per = per;
    }
}
