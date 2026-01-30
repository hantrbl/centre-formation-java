package centreformation.classes;

import centreformation.exceptions.FormationPleineException;
import java.util.HashSet;
import java.util.Set;

public class Formation {
    private final String idFormation;
    private String intitule;
    private int capacite;
    
    private Set<String> etudiantsInscrits;

    public Formation(String idFormation, String intitule, int capacite){
        this.idFormation = idFormation;
        this.intitule = intitule;
        this.capacite = capacite;
        this.etudiantsInscrits = new HashSet<>();
    }
    //getters
    public String getIdFormation() {return idFormation;}
    public String getIntitule() {return intitule;}
    public int getCapacite() {return capacite;}

    //setters
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    public void setCapacite(int capacite) {
        if (capacite < etudiantsInscrits.size())
            throw new IllegalArgumentException("Capacité inférieure au nombre d'étudiants inscrits");
        this.capacite = capacite;
    }

    public void ajouterEtudiant(String idEtudiant) throws FormationPleineException {
        if (estPleine())
            throw new FormationPleineException("Formation pleine !");
        etudiantsInscrits.add(idEtudiant);
    }

    public void retirerEtudiant(String idEtudiant){
        etudiantsInscrits.remove(idEtudiant);
    }

    public boolean estPleine(){
        return etudiantsInscrits.size() >= capacite;
    }

    @Override
    public String toString() {
        return idFormation + " - " + intitule + " (" +
                etudiantsInscrits.size() + "/" + capacite + ")";
    }
}
