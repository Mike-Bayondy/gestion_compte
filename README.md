# 💼 BanqueApp Java (Projet NetBeans)

Cette application simule une interface bancaire simple avec Java et Swing.

---

## ✅ Fonctionnalités

- Création de compte client
- Dépôt d’argent
- Retrait d’argent
- Consultation des informations client

---

## 🔧 Refactorisation du code (2025-09)

Le projet a été **amélioré** pour respecter les bonnes pratiques de développement logiciel :

### 🔹 Avant :
Tout le code était contenu dans une seule classe `BanqueApp`.

### 🔹 Après :
Le code est maintenant organisé en **3 classes séparées** :

| Classe | Rôle |
|--------|------|
| `Client` | Stocke les informations du client (nom, âge, solde, etc.) |
| `Banque` | Gère la liste des clients, recherche de compte |
| `BanqueApp` | Interface graphique (Swing) et interactions utilisateur |

---

## 📁 Organisation des fichiers
src/banque/
├── BanqueApp.java # Interface principale
├── Banque.java # Logique de gestion des clients
└── Client.java # Classe représentant un client

---

## 📦 Technologies

- Java (JDK 8+)
- NetBeans
- Swing (interface graphique)
- Git + GitHub

---

## ✍️ Auteur

Edouard – Projet Java


