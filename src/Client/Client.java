package banque;

public class Client {
    private String nom;
    private String prenom;
    private String adresse;
    private String numeroCompte;
    private int age;
    private double solde;

    public Client(String nom, String prenom, int age, String adresse, String numeroCompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.numeroCompte = numeroCompte;
        this.solde = 0.0;
    }

    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getAdresse() { return adresse; }
    public String getNumeroCompte() { return numeroCompte; }
    public int getAge() { return age; }
    public double getSolde() { return solde; }

    // Setters
    public void deposer(double montant) {
        this.solde += montant;
    }

    public boolean retirer(double montant) {
        if (this.solde >= montant) {
            this.solde -= montant;
            return true;
        }
        return false;
    }
}
