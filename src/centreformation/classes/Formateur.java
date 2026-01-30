package centreformation.classes;
public class Formateur extends Personne{
    private String specialite;

    public Formateur(String id, String nom, int age, String specialite){
        super(id, nom, age);
        this.specialite = specialite;
    }

    public String getSpecialite(){ return specialite; }
    public void setSpecialite(String specialite){
        this.specialite = specialite;
    }

    @Override
    public String getType(){
        return "Formateur";
    }

    @Override
    public String toString(){
        return super.toString() + ", Spécialité: " +specialite;
    }
}
