package model;
import utils.PersonneDejaPresentException;
import java.util.ArrayList;

public class RegistrePersonnes {

    private ArrayList<Personnes> registre;

    public RegistrePersonnes(){
        this.registre = new ArrayList<>();
    }

    public void ajouterPersonne(Personnes per) throws PersonneDejaPresentException {
        //Faire appel à verifierDoublon
        if(verifierDoublon(per)){//doublon
            throw new PersonneDejaPresentException("Personne deja present dans la liste! : ",per);
        }else{
            registre.add(per);
        }
    }

    public boolean verifierDoublon(Personnes per){
        for (Personnes tmp : registre){
            if (per.equals(tmp)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Personnes> getRegistre() {
        return registre;
    }


}
