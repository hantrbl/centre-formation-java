package centreformation.gui;

import centreformation.classes.*;
import javax.swing.*;
import java.awt.*;

public class AjouterEtudiantPanel extends JPanel {

    public AjouterEtudiantPanel(CentreFormation centre) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Titre
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(60, 70, 80));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel headerLabel = new JLabel("Gestion des Étudiants");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        // Formulaire
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField id = new JTextField(20);
        JTextField nom = new JTextField(20);
        JTextField age = new JTextField(20);
        JTextField niveau = new JTextField(20);

        addFormRow(formPanel, gbc, 0, "ID :", id);
        addFormRow(formPanel, gbc, 1, "Nom :", nom);
        addFormRow(formPanel, gbc, 2, "Âge :", age);
        addFormRow(formPanel, gbc, 3, "Niveau :", niveau);

        centerPanel.add(formPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton ajouterBtn = createStyledButton("Ajouter", new Color(100, 150, 100));
        JButton modifierBtn = createStyledButton("Modifier", new Color(70, 130, 180));
        JButton supprimerBtn = createStyledButton("Supprimer", new Color(180, 80, 80));

        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);

        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Action Ajouter
        ajouterBtn.addActionListener(e -> {
            try {
                centre.ajouterPersonne(
                    new Etudiant(
                        id.getText().trim(),
                        nom.getText().trim(),
                        Integer.parseInt(age.getText().trim()),
                        niveau.getText().trim()
                    )
                );
                JOptionPane.showMessageDialog(this, 
                    "Étudiant ajouté avec succès", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields(id, nom, age, niveau);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "L'âge doit être un nombre valide", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action Modifier
        modifierBtn.addActionListener(e -> {
            try {
                String idText = id.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Veuillez entrer l'ID de l'étudiant à modifier", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                centre.modifierEtudiant(
                    idText,
                    nom.getText().trim(),
                    Integer.parseInt(age.getText().trim()),
                    niveau.getText().trim()
                );
                JOptionPane.showMessageDialog(this, 
                    "Étudiant modifié avec succès", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields(id, nom, age, niveau);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "L'âge doit être un nombre valide", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action Supprimer
        supprimerBtn.addActionListener(e -> {
            String idText = id.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Veuillez entrer l'ID de l'étudiant à supprimer", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Êtes-vous sûr de vouloir supprimer l'étudiant " + idText + " ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                centre.supprimerPersonne(idText);
                JOptionPane.showMessageDialog(this, 
                    "Étudiant supprimé avec succès", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields(id, nom, age, niveau);
            }
        });
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(250, 30));
        panel.add(field, gbc);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(120, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}