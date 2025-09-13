package banque;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Client {
    String nom, prenom, adresse, numeroCompte;
    int age;
    double solde;

    public Client(String nom, String prenom, int age, String adresse, String numeroCompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.numeroCompte = numeroCompte;
        this.solde = 0.0;
    }
}

public class BanqueApp extends JFrame implements ActionListener {
    private JTextArea affichage;
    private ArrayList<Client> clients = new ArrayList<>();
    private JTextField nomField, prenomField, ageField, adresseField, compteField, montantField;
    private JButton creerBtn, deposerBtn, retirerBtn, infosBtn;

    public BanqueApp() {
        super("Banque MIKE HT");

        // Zone d'affichage
        affichage = new JTextArea(10, 40);
        affichage.setEditable(false);
        affichage.setFont(new Font("Arial", Font.PLAIN, 16));
        affichage.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Champs texte
        nomField = new JTextField(15);
        prenomField = new JTextField(15);
        ageField = new JTextField(5);
        adresseField = new JTextField(20);
        compteField = new JTextField(10);
        montantField = new JTextField(10);

        // Boutons
        creerBtn = new JButton("Créer Compte");
        deposerBtn = new JButton("Déposer");
        retirerBtn = new JButton("Retirer");
        infosBtn = new JButton("Infos Client");

        creerBtn.addActionListener(this);
        deposerBtn.addActionListener(this);
        retirerBtn.addActionListener(this);
        infosBtn.addActionListener(this);

        // Panel principal avec GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Ajout des labels et champs
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

        // Boutons
        gbc.gridx = 0; gbc.gridy = 6; panel.add(creerBtn, gbc);
        gbc.gridx = 1; panel.add(deposerBtn, gbc);
        gbc.gridx = 0; gbc.gridy = 7; panel.add(retirerBtn, gbc);
        gbc.gridx = 1; panel.add(infosBtn, gbc);

        // Design final
        setLayout(new BorderLayout(10, 10));
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(affichage), BorderLayout.CENTER);

        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Client trouverClient(String numeroCompte) {
        for (Client c : clients) {
            if (c.numeroCompte.equals(numeroCompte)) return c;
        }
        return null;
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

                if (trouverClient(numeroCompte) != null) {
                    affichage.setText("⚠️ Ce numéro de compte existe déjà !");
                    return;
                }

                Client client = new Client(nom, prenom, age, adresse, numeroCompte);
                clients.add(client);
                affichage.setText("✅ Compte créé avec succès pour " + nom + " " + prenom);

            } else if (cmd.equals("Déposer")) {
                Client c = trouverClient(compteField.getText());
                if (c == null) {
                    affichage.setText("⚠️ Compte introuvable !");
                    return;
                }
                double montant = Double.parseDouble(montantField.getText());
                c.solde += montant;
                affichage.setText("💰 Dépôt de " + montant + " HTG effectué.\nNouveau solde : " + c.solde + " HTG");

            } else if (cmd.equals("Retirer")) {
                Client c = trouverClient(compteField.getText());
                if (c == null) {
                    affichage.setText("⚠️ Compte introuvable !");
                    return;
                }
                double montant = Double.parseDouble(montantField.getText());
                if (c.solde >= montant) {
                    c.solde -= montant;
                    affichage.setText("💸 Retrait de " + montant + " HTG effectué.\nNouveau solde : " + c.solde + " HTG");
                } else {
                    affichage.setText("❌ Solde insuffisant !");
                }

            } else if (cmd.equals("Infos Client")) {
                Client c = trouverClient(compteField.getText());
                if (c == null) {
                    affichage.setText("⚠️ Compte introuvable !");
                    return;
                }
                affichage.setText("📋 Infos Client :\n" +
                        "Nom: " + c.nom + "\n" +
                        "Prénom: " + c.prenom + "\n" +
                        "Âge: " + c.age + "\n" +
                        "Adresse: " + c.adresse + "\n" +
                        "Compte: " + c.numeroCompte + "\n" +
                        "Solde: " + c.solde + " HTG");
            }
        } catch (NumberFormatException ex) {
            affichage.setText("⚠️ Veuillez entrer des valeurs numériques correctes !");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BanqueApp());
    }
}
