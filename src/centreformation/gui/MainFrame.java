package centreformation.gui;

import centreformation.classes.CentreFormation;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CentreFormation centre;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        centre = new CentreFormation();

        setTitle("Gestion Centre de Formation");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de navigation à gauche
        JPanel navPanel = createNavigationPanel();
        add(navPanel, BorderLayout.WEST);

        // Panel de contenu avec CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        contentPanel.add(new AccueilPanel(), "accueil");
        contentPanel.add(new AjouterEtudiantPanel(centre), "etudiants");
        contentPanel.add(new AjouterFormateurPanel(centre), "formateurs");
        contentPanel.add(new AjouterFormationPanel(centre), "formations");
        contentPanel.add(new InscriptionPanel(centre), "inscriptions");
        contentPanel.add(new AffichagePanel(centre), "affichage");

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(60, 70, 80));
        panel.setPreferredSize(new Dimension(180, 600));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Titre
        JLabel titre = new JLabel("MENU");
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titre);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Boutons de navigation
        addNavButton(panel, "Accueil", "accueil");
        addNavButton(panel, "Étudiants", "etudiants");
        addNavButton(panel, "Formateurs", "formateurs");
        addNavButton(panel, "Formations", "formations");
        addNavButton(panel, "Inscriptions", "inscriptions");
        addNavButton(panel, "Affichage", "affichage");

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private void addNavButton(JPanel panel, String text, String cardName) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(160, 40));
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(80, 90, 100));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addActionListener(e -> cardLayout.show(contentPanel, cardName));

        // Effet hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(100, 110, 120));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(80, 90, 100));
            }
        });

        panel.add(btn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}