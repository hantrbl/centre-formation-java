package centreformation.classes;

import java.util.ArrayList;
import java.util.List;

import centreformation.exceptions.AgeInvalideException;
import centreformation.interfaces.Inscription;

public class Etudiant extends Personne implements Inscription{
    private String niveau;
    private List<String> formationsInscrites;

    public Etudiant(String id, String nom, int age, String niveau) throws AgeInvalideException{
        super(id, nom, age); 
        if(age < 18)
            throw new AgeInvalideException("Etudiant est mineur !");
        this.niveau = niveau;
        this.formationsInscrites = new ArrayList<>();
    }


    public String getNiveau(){ return niveau; }
    public void setNiveau(String niveau) {this.niveau = niveau;}

    public List<String> getFormationsInscrites() {
        return formationsInscrites;
    }

    @Override
    public String getType(){return "Etudiant";}

    @Override
    public void inscrire(String idFormation) {
        if (!formationsInscrites.contains(idFormation)) {
            formationsInscrites.add(idFormation);
        }
    }

    @Override
    public void desinscrire(String idFormation) {
        formationsInscrites.remove(idFormation);
    }

    @Override
    public String toString() {
        return super.toString() + ", Niveau: " + niveau;
    }
}
