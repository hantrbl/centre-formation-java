package centreformation.gui;

import centreformation.classes.CentreFormation;
import centreformation.classes.Etudiant;
import centreformation.classes.Formateur;
import java.awt.*;
import javax.swing.*;

public class AffichagePanel extends JPanel {

    private JTextArea areaEtudiants;
    private JTextArea areaFormateurs;
    private JTextArea areaFormations;
    private CentreFormation centre;

    public AffichagePanel(CentreFormation centre) {
        this.centre = centre;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Titre
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(60, 70, 80));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel headerLabel = new JLabel("Affichage des Données");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Panel principal avec onglets
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 12));

        // Onglet Étudiants
        areaEtudiants = createTextArea();
        JScrollPane scrollEtudiants = new JScrollPane(areaEtudiants);
        tabbedPane.addTab("Étudiants", scrollEtudiants);

        // Onglet Formateurs
        areaFormateurs = createTextArea();
        JScrollPane scrollFormateurs = new JScrollPane(areaFormateurs);
        tabbedPane.addTab("Formateurs", scrollFormateurs);

        // Onglet Formations
        areaFormations = createTextArea();
        JScrollPane scrollFormations = new JScrollPane(areaFormations);
        tabbedPane.addTab("Formations", scrollFormations);

        add(tabbedPane, BorderLayout.CENTER);

        // Panel de boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton refreshBtn = createStyledButton("Actualiser", new Color(70, 130, 180));
        
        buttonPanel.add(refreshBtn);
        
        add(buttonPanel, BorderLayout.SOUTH);

        refreshBtn.addActionListener(e -> afficher());

        // Affichage initial
        afficher();
    }

    private JTextArea createTextArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setMargin(new Insets(10, 10, 10, 10));
        area.setBackground(new Color(250, 250, 250));
        return area;
    }

    private void afficher() {
        // Afficher les étudiants
        areaEtudiants.setText("");
        areaEtudiants.append("LISTE DES ÉTUDIANTS\n");
        areaEtudiants.append("═══════════════════════════════════════════════════\n\n");
        
        long nbEtudiants = centre.getPersonnes().values().stream()
            .filter(p -> p instanceof Etudiant)
            .count();
        
        if (nbEtudiants == 0) {
            areaEtudiants.append("Aucun étudiant enregistré.\n");
        } else {
            areaEtudiants.append("Total: " + nbEtudiants + " étudiant(s)\n\n");
            centre.getPersonnes().values().stream()
                .filter(p -> p instanceof Etudiant)
                .forEach(p -> areaEtudiants.append(p + "\n"));
        }

        // Afficher les formateurs
        areaFormateurs.setText("");
        areaFormateurs.append("LISTE DES FORMATEURS\n");
        areaFormateurs.append("═══════════════════════════════════════════════════\n\n");
        
        long nbFormateurs = centre.getPersonnes().values().stream()
            .filter(p -> p instanceof Formateur)
            .count();
        
        if (nbFormateurs == 0) {
            areaFormateurs.append("Aucun formateur enregistré.\n");
        } else {
            areaFormateurs.append("Total: " + nbFormateurs + " formateur(s)\n\n");
            centre.getPersonnes().values().stream()
                .filter(p -> p instanceof Formateur)
                .forEach(p -> areaFormateurs.append(p + "\n"));
        }

        // Afficher les formations
        areaFormations.setText("");
        areaFormations.append("LISTE DES FORMATIONS\n");
        areaFormations.append("═══════════════════════════════════════════════════\n\n");
        
        if (centre.getFormations().isEmpty()) {
            areaFormations.append("Aucune formation enregistrée.\n");
        } else {
            areaFormations.append("Total: " + centre.getFormations().size() + " formation(s)\n\n");
            centre.getFormations().values()
                .forEach(f -> areaFormations.append(f + "\n"));
        }
    }

    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(130, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}