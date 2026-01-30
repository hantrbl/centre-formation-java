package centreformation.gui;

import centreformation.classes.CentreFormation;
import javax.swing.*;
import java.awt.*;

public class InscriptionPanel extends JPanel {

    public InscriptionPanel(CentreFormation centre) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Titre
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(60, 70, 80));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel headerLabel = new JLabel("Inscription aux Formations");
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

        JTextField idEtudiant = new JTextField(20);
        JTextField idFormation = new JTextField(20);

        addFormRow(formPanel, gbc, 0, "ID Étudiant :", idEtudiant);
        addFormRow(formPanel, gbc, 1, "ID Formation :", idFormation);

        centerPanel.add(formPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton inscrireBtn = createStyledButton("Inscrire", new Color(70, 130, 180));
        JButton desinscrireBtn = createStyledButton("Désinscrire", new Color(180, 80, 80));

        buttonPanel.add(inscrireBtn);
        buttonPanel.add(desinscrireBtn);

        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Action Inscrire
        inscrireBtn.addActionListener(e -> {
            try {
                centre.inscrireEtudiant(
                    idEtudiant.getText().trim(), 
                    idFormation.getText().trim()
                );
                JOptionPane.showMessageDialog(this, 
                    "Inscription réussie", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields(idEtudiant, idFormation);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action Désinscrire
        desinscrireBtn.addActionListener(e -> {
            try {
                String idEtud = idEtudiant.getText().trim();
                String idForm = idFormation.getText().trim();
                if (idEtud.isEmpty() || idForm.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Veuillez remplir tous les champs", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Êtes-vous sûr de vouloir désinscrire l'étudiant " + idEtud + 
                    " de la formation " + idForm + " ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    centre.desinscrireEtudiant(idEtud, idForm);
                    JOptionPane.showMessageDialog(this, 
                        "Désinscription réussie", 
                        "Succès", 
                        JOptionPane.INFORMATION_MESSAGE);
                    clearFields(idEtudiant, idFormation);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
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
        btn.setPreferredSize(new Dimension(150, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}