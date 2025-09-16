package banque;

import java.util.ArrayList;

public class Banque {
    private ArrayList<Client> clients;

    public Banque() {
        clients = new ArrayList<>();
    }

    public boolean ajouterClient(Client client) {
        if (trouverClient(client.getNumeroCompte()) != null) {
            return false; // Client déjà existant
        }
        clients.add(client);
        return true;
    }

    public Client trouverClient(String numeroCompte) {
        for (Client c : clients) {
            if (c.getNumeroCompte().equals(numeroCompte)) {
                return c;
            }
        }
        return null;
    }
}
