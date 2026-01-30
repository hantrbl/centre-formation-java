package centreformation.classes;

public abstract class Personne {
    protected String id;
    protected String nom;
    protected int age;

    public Personne(String id, String nom, int age){
        this.id = id;
        this.nom = nom;
        this.age = age;
    }
    //Getters
    public String getId(){ return id; }
    public String getNom(){ return nom; }
    public int getAge(){ return age; }

    //Setters
    public void setNom(String nom) {this.nom = nom;}
    public void setAge(int age) {this.age = age;}

    public abstract String getType();

    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + ", Age: " + age + ", Type: " + getType();
    }
}
