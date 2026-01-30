package centreformation.classes;

import centreformation.exceptions.AgeInvalideException;
import centreformation.exceptions.FormationIntrouvableException;
import centreformation.exceptions.FormationPleineException;
import centreformation.exceptions.PersonneDejaExistanteException;
import java.util.*;

public class CentreFormation {
    private Map<String, Personne> personnes;
    private Map<String, Formation> formations;
    private Scanner sc;

    public CentreFormation(){
        personnes = new HashMap<>();
        formations = new HashMap<>();
        sc = new Scanner(System.in);
    }

    //PERSONNES
    public void ajouterPersonne(Personne p) throws PersonneDejaExistanteException, AgeInvalideException {
        if (personnes.containsKey(p.getId()))
            throw new PersonneDejaExistanteException("Personne déjà existante");
        personnes.put(p.getId(), p);
    }
    public void supprimerPersonne(String id){personnes.remove(id);}
    public Personne getPersonne(String id) {return personnes.get(id);}

    //FORMATIONS
    public void ajouterFormation(Formation f) {
        formations.put(f.getIdFormation(), f);
    }
    public void supprimerFormation(String idFormation) {formations.remove(idFormation);}
    public Formation getFormation(String idFormation) {return formations.get(idFormation);}
    public void modifierFormation(String idFormation, String intitule, int capacite) throws FormationIntrouvableException{
        Formation f = formations.get(idFormation);
        if (f == null) 
            throw new FormationIntrouvableException("Formation introuvable");
        f.setIntitule(intitule);
        f.setCapacite(capacite);
    }

    //INSCRIPTION - MODIF
    public void inscrireEtudiant(String idEtudiant, String idFormation)
            throws FormationIntrouvableException, FormationPleineException {
                Personne p = personnes.get(idEtudiant);
                if (!(p instanceof Etudiant))
                    throw new IllegalArgumentException("Étudiant introuvable");

                Formation f = formations.get(idFormation);
                if (f == null)
                    throw new FormationIntrouvableException("Formation introuvable");

                f.ajouterEtudiant(idEtudiant);
                ((Etudiant) p).inscrire(idFormation);
    }

    public void desinscrireEtudiant(String idEtudiant, String idFormation) {
        Personne p = personnes.get(idEtudiant);
        if (!(p instanceof Etudiant))
            throw new IllegalArgumentException("Étudiant introuvable");
        Formation f = formations.get(idFormation);
        if (f == null)
            throw new IllegalArgumentException("Formation introuvable");
        f.retirerEtudiant(idEtudiant);
        ((Etudiant) p).desinscrire(idFormation);
    }

    public void modifierEtudiant(String id, String nom, int age, String niveau) {
        Personne p = personnes.get(id);
        if (!(p instanceof Etudiant))
            throw new IllegalArgumentException("Étudiant introuvable");
        //downcasting
        Etudiant e = (Etudiant) p;
        e.setNom(nom);
        e.setAge(age);
        e.setNiveau(niveau);
    }

    public void modifierFormateur(String id, String nom, int age, String specialite) {
        Personne p = personnes.get(id);
        if (!(p instanceof Formateur))
            throw new IllegalArgumentException("Formateur introuvable");
        //downcasting
        Formateur frmteur = (Formateur) p;
        frmteur.setNom(nom);
        frmteur.setAge(age);
        frmteur.setSpecialite(specialite);
    }

    
    //GETTERS POUR LA GUI
    public Map<String, Personne> getPersonnes() {return personnes;}

    public Map<String, Formation> getFormations() {return formations;}

    //CONSOLE   
    public void menu() {
        int choix;
        do {
            System.out.println("\n--- MENU CENTRE DE FORMATION ---");
            System.out.println("1. Gestion des étudiants");
            System.out.println("2. Gestion des formateurs");
            System.out.println("3. Gestion des formations");
            System.out.println("4. Inscription étudiant");
            System.out.println("5. Désinscription étudiant");
            System.out.println("6. Affichage");
            System.out.println("0. Quitter");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> menuEtudiants();
                case 2 -> menuFormateurs();
                case 3 -> menuFormations();
                case 4 -> inscriptionConsole();
                case 5 -> desinscrireConsole();
                case 6 -> affichageConsole();
            }
        } while (choix != 0);
    }

    //ETUDIANTS

    private void menuEtudiants() {
        System.out.println("\n1. Ajouter étudiant");
        System.out.println("2. Supprimer étudiant");
        System.out.println("3. Modifier étudiant");
        int choix = sc.nextInt();
        sc.nextLine();

        try {
            if (choix == 1) {
                System.out.print("ID : ");
                String id = sc.nextLine();
                System.out.print("Nom : ");
                String nom = sc.nextLine();
                System.out.print("Age : ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Niveau : ");
                String niveau = sc.nextLine();
                ajouterPersonne(new Etudiant(id, nom, age, niveau));

            } else if (choix == 2) {
                System.out.print("ID étudiant : ");
                supprimerPersonne(sc.nextLine());

            } else if (choix == 3) {
                System.out.print("ID étudiant : ");
                String id = sc.nextLine();
                System.out.print("Nouveau nom : ");
                String nom = sc.nextLine();
                System.out.print("Nouvel âge : ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Nouveau niveau : ");
                String niveau = sc.nextLine();
                modifierEtudiant(id, nom, age, niveau);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //FORMATEURS

    private void menuFormateurs() {
        System.out.println("\n1. Ajouter formateur");
        System.out.println("2. Supprimer formateur");
        System.out.println("3. Modifier formateur");
        int choix = sc.nextInt();
        sc.nextLine();

        try {
            if (choix == 1) {
                System.out.print("ID : ");
                String id = sc.nextLine();
                System.out.print("Nom : ");
                String nom = sc.nextLine();
                System.out.print("Age : ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Spécialité : ");
                String sp = sc.nextLine();
                ajouterPersonne(new Formateur(id, nom, age, sp));

            } else if (choix == 2) {
                System.out.print("ID formateur : ");
                supprimerPersonne(sc.nextLine());

            } else if (choix == 3) {
                System.out.print("ID formateur : ");
                String id = sc.nextLine();
                System.out.print("Nouveau nom : ");
                String nom = sc.nextLine();
                System.out.print("Nouvel âge : ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Nouvelle spécialité : ");
                String sp = sc.nextLine();
                modifierFormateur(id, nom, age, sp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //FORMATIONS

    private void menuFormations() {
        System.out.println("\n1. Ajouter formation");
        System.out.println("2. Supprimer formation");
        System.out.println("3. Modifier formation");
        int choix = sc.nextInt();
        sc.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("ID formation : ");
                String id = sc.nextLine();
                System.out.print("Intitulé : ");
                String intitule = sc.nextLine();
                System.out.print("Capacité : ");
                int cap = sc.nextInt();
                sc.nextLine();
                ajouterFormation(new Formation(id, intitule, cap));
            }
            case 2 -> {
                System.out.print("ID formation : ");
                supprimerFormation(sc.nextLine());
            }
            case 3 -> {
                System.out.print("ID formation : ");
                String id = sc.nextLine();
                System.out.print("Nouvel intitulé : ");
                String intitule = sc.nextLine();
                System.out.print("Nouvelle capacité : ");
                int cap = sc.nextInt();
                sc.nextLine();
                try {
                    modifierFormation(id, intitule, cap);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        }
    }

    //INSCRIPTION

    private void inscriptionConsole() {
        try {
            System.out.print("ID étudiant : ");
            String idE = sc.nextLine();
            System.out.print("ID formation : ");
            String idF = sc.nextLine();
            inscrireEtudiant(idE, idF);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void desinscrireConsole() {
        try {
            System.out.print("ID étudiant : ");
            String idE = sc.nextLine();
            System.out.print("ID formation : ");
            String idF = sc.nextLine();
            desinscrireEtudiant(idE, idF);
            System.out.println("Désinscription réussie !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //AFFICHAGE

    private void affichageConsole() {
        System.out.println("\n--- Étudiants ---");
        personnes.values().stream()
                .filter(p -> p instanceof Etudiant)
                .forEach(System.out::println);

        System.out.println("\n--- Formateurs ---");
        personnes.values().stream()
                .filter(p -> p instanceof Formateur)
                .forEach(System.out::println);

        System.out.println("\n--- Formations ---");
        formations.values().forEach(System.out::println);
    }

    //MAIN

    public static void main(String[] args) {
        new CentreFormation().menu(); // pour tester en console
    }
}
