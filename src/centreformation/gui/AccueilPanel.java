package centreformation.gui;

import javax.swing.*;
import java.awt.*;

public class AccueilPanel extends JPanel {

    public AccueilPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(80, 50, 50, 50));

        // Titre principal
        JLabel titre = new JLabel("Centre de Formation");
        titre.setFont(new Font("Arial", Font.BOLD, 36));
        titre.setForeground(new Color(60, 70, 80));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titre);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Sous-titre
        JLabel sousTitre = new JLabel("Système de Gestion");
        sousTitre.setFont(new Font("Arial", Font.PLAIN, 20));
        sousTitre.setForeground(new Color(120, 120, 120));
        sousTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(sousTitre);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        // Instructions
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
        instructionsPanel.setBackground(new Color(245, 245, 245));
        instructionsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        instructionsPanel.setMaximumSize(new Dimension(600, 300));

        JLabel instructionTitre = new JLabel("Cette application implémente :");
        instructionTitre.setFont(new Font("Arial", Font.BOLD, 18));
        instructionTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsPanel.add(instructionTitre);

        instructionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] items = {
            "La Gestion des étudiants",
            "La Gestion des formateurs",
            "La Gestion des formations",
            "L'Inscription d'un étudiant à une formation",
            "L'Affichage de toutes les données"
        };

        for (String item : items) {
            JLabel label = new JLabel("• " + item);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            instructionsPanel.add(label);
            instructionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        centerPanel.add(instructionsPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));
        footer.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel footerLabel = new JLabel("ⓒ2025 BOULILA HANIA Mini Projet P.O.O");
        footerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        footerLabel.setForeground(new Color(120, 120, 120));

        footer.add(footerLabel);
        add(footer, BorderLayout.SOUTH);
    }
}