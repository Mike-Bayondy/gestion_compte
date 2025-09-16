package banque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BanqueApp extends JFrame implements ActionListener {
    private final JTextArea affichage;
    private final JTextField nomField;
    private final JTextField prenomField;
    private final JTextField ageField;
    private final JTextField adresseField;
    private final JTextField compteField;
    private final JTextField montantField;
    private final JButton creerBtn;
    private final JButton deposerBtn;
    private final JButton retirerBtn;
    private final JButton infosBtn;
    private final Banque banque;

    public BanqueApp() {
        super("Banque MIKE HT");
        banque = new Banque();

        // Interface graphique
        affichage = new JTextArea(10, 40);
        affichage.setEditable(false);
        affichage.setFont(new Font("Arial", Font.PLAIN, 16));
        affichage.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        nomField = new JTextField(15);
        prenomField = new JTextField(15);
        ageField = new JTextField(5);
        adresseField = new JTextField(20);
        compteField = new JTextField(10);
        montantField = new JTextField(10);

        creerBtn = new JButton("Créer Compte");
        deposerBtn = new JButton("Déposer");
        retirerBtn = new JButton("Retirer");
        infosBtn = new JButton("Infos Client");

        creerBtn.addActionListener(this);
        deposerBtn.addActionListener(this);
        retirerBtn.addActionListener(this);
        infosBtn.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; panel.add(nomField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 1; panel.add(prenomField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Âge:"), gbc);
        gbc.gridx = 1; panel.add(ageField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Adresse:"), gbc);
        gbc.gridx = 1; panel.add(adresseField, gbc);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Numéro Compte:"), gbc);
        gbc.gridx = 1; panel.add(compteField, gbc);
        gbc.gridx = 0; gbc.gridy = 5; panel.add(new JLabel("Montant:"), gbc);
        gbc.gridx = 1; panel.add(montantField, gbc);
        gbc.gridx = 0; gbc.gridy = 6; panel.add(creerBtn, gbc);
        gbc.gridx = 1; panel.add(deposerBtn, gbc);
        gbc.gridx = 0; gbc.gridy = 7; panel.add(retirerBtn, gbc);
        gbc.gridx = 1; panel.add(infosBtn, gbc);

        setLayout(new BorderLayout(10, 10));
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(affichage), BorderLayout.CENTER);

        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            if (cmd.equals("Créer Compte")) {
                if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() ||
                    ageField.getText().isEmpty() || adresseField.getText().isEmpty() ||
                    compteField.getText().isEmpty()) {
                    affichage.setText("⚠️ Veuillez remplir tous les champs !");
                    return;
                }

                String nom = nomField.getText();
                String prenom = prenomField.getText();
                int age = Integer.parseInt(ageField.getText());
                String adresse = adresseField.getText();
                String numeroCompte = compteField.getText();

                Client nouveau = new Client(nom, prenom, age, adresse, numeroCompte);
                if (banque.ajouterClient(nouveau)) {
                    affichage.setText("✅ Compte créé pour " + nom + " " + prenom);
                } else {
                    affichage.setText("⚠️ Ce numéro de compte existe déjà !");
                }

            } else if (cmd.equals("Déposer")) {
                Client c = banque.trouverClient(compteField.getText());
                if (c == null) {
                    affichage.setText("⚠️ Compte introuvable !");
                    return;
                }

                double montant = Double.parseDouble(montantField.getText());
                c.deposer(montant);
                affichage.setText("💰 Dépôt de " + montant + " HTG effectué.\nNouveau solde : " + c.getSolde() + " HTG");

            } else if (cmd.equals("Retirer")) {
                Client c = banque.trouverClient(compteField.getText());
                if (c == null) {
                    affichage.setText("⚠️ Compte introuvable !");
                    return;
                }

                double montant = Double.parseDouble(montantField.getText());
                if (c.retirer(montant)) {
                    affichage.setText("💸 Retrait de " + montant + " HTG effectué.\nNouveau solde : " + c.getSolde() + " HTG");
                } else {
                    affichage.setText("❌ Solde insuffisant !");
                }

            } else if (cmd.equals("Infos Client")) {
                Client c = banque.trouverClient(compteField.getText());
                if (c == null) {
                    affichage.setText("⚠️ Compte introuvable !");
                    return;
                }

                affichage.setText("📋 Infos Client :\n" +
                        "Nom: " + c.getNom() + "\n" +
                        "Prénom: " + c.getPrenom() + "\n" +
                        "Âge: " + c.getAge() + "\n" +
                        "Adresse: " + c.getAdresse() + "\n" +
                        "Compte: " + c.getNumeroCompte() + "\n" +
                        "Solde: " + c.getSolde() + " HTG");
            }
        } catch (NumberFormatException ex) {
            affichage.setText("⚠️ Veuillez entrer des valeurs numériques valides !");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BanqueApp::new);
    }
}
