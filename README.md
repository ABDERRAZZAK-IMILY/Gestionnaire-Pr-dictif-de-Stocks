# Gestionnaire Prédictif de Stocks

## 🧾 Description du projet

**Système Intelligent de Prévision et Gestion Sécurisée des Stocks Multi-Entrepôts**

Une entreprise de distribution gère plusieurs entrepôts répartis géographiquement. Elle fait face à deux problématiques majeures :  

1. **Problème de prévision** : Ruptures de stock fréquentes dans certains entrepôts pour certains produits alors que d'autres entrepôts sont en surstock, entraînant des coûts élevés (stockage inutile, pertes, ventes manquées, transferts inter-entrepôts inefficaces).  
2. **Problème de sécurité** : Données sensibles (prix d'achat, marges, stratégies d'approvisionnement) accessibles à tous les employés sans distinction de rôle, et accès non restreint aux données des autres entrepôts.

---

## 🎯 Objectifs du projet

### Volet IA
- Prédire quand un produit va manquer  
- Afficher une recommandation de quantité à commander  

### Volet Sécurité
- 2 types d'utilisateurs avec des droits différents  

---

## 👥 Rôles et droits

### 1. ADMIN
- Voit tous les entrepôts et toutes les informations (prix, marges, prévisions)  
- Gère les produits (créer, modifier, supprimer)  
- Gère les entrepôts  
- Gère les GESTIONNAIRE et leur assignation aux entrepôts  
- Consulte l'historique de tous les entrepôts  

### 2. GESTIONNAIRE
- Voit uniquement son entrepôt  
- Met à jour les stocks de son entrepôt  
- Consulte l'historique des ventes de son entrepôt  
- Ne peut pas gérer les utilisateurs  
- Ne voit pas les autres entrepôts, prix d'achat ni marges  

---

## 📦 Entités principales

### User
- login, password (hashé), nom, prénom, email  
- role (ADMIN, GESTIONNAIRE)  
- actif (boolean)  
- entrepot_assigne (relation vers Entrepot - NULL si ADMIN)  

### Produit
- nom, description, catégorie  
- prix_vente (visible par tous)  
- prix_achat (chiffré - visible ADMIN uniquement)  
- marge (chiffrée - visible ADMIN uniquement)  
- poids, unité (kg, litre, unité)  

### Stock
- Produit concerné  
- quantité_disponible  
- Entrepot concerné
  
### HistoriqueVente
- Produit, Entrepot concerné  
- date_vente, quantite_vendue  
- jour_semaine, mois, annee  

### Prevision
- Produit, Entrepot concerné  
- date_prevision  


### Entrepot
- nom, ville, adresse  

---

## 🛠️ Architecture et couches applicatives
- Controller, Service, Repository, DTO, Mapper,Security ,Config , Exception, Validation, Tests  

---

## 💾 Base de données
- MongoDB/PostgreSQL 

---

## 🚀 CI/CD et Conteneurisation
- Jenkins (build + test)  
- Dockerfile pour conteneurisation  
- Docker Compose (app + SGBD)  

---

## ⚙️ Technologies et exigences techniques
- REST API  
- Spring Data  
- Tests unitaires (JUnit et Mockito)  
- Validations métier  
- Gestion des transactions  
- Java Stream API, Optional, Collection API  
- Gestion des exceptions (@ControllerAdvice, exceptions personnalisées)  
- Spring Security avec Authentification stateless  
- Multi-profil (minimum 2)  


## Diagramme de classe  
<img width="1514" height="856" alt="diagGPDS" src="https://github.com/user-attachments/assets/64fbed45-d3f4-4533-8d44-20f6fa4345ce" />

## Diagramme de Use Case


## 🔗 Endpoints API

### Auth
- POST `/api/auth/login` - Authentification JWT  
- POST `/api/auth/register` - Inscription (optionnel)  

### Users (ADMIN)
- GET `/api/users`  
- GET `/api/users/{id}`  
- POST `/api/users`  
- PUT `/api/users/{id}`  
- DELETE `/api/users/{id}`  

### Entrepots
- GET `/api/entrepots`  
- GET `/api/entrepots/{id}`  
- POST `/api/entrepots`  
- PUT `/api/entrepots/{id}`  
- DELETE `/api/entrepots/{id}`  

### Produits
- GET `/api/produits`  
- GET `/api/produits/{id}`  
- POST `/api/produits`  
- PUT `/api/produits/{id}`  
- DELETE `/api/produits/{id}`  

### Stocks
- GET `/api/stocks`  
- GET `/api/stocks/{id}`  
- GET `/api/stocks/entrepot/{entrepotId}`  
- POST `/api/stocks`  
- PUT `/api/stocks/{id}`  
- DELETE `/api/stocks/{id}`
- 
---

## 📅 Modalités
- Projet collaboratif : ABDERRAZZAK IMILY / Mohamed Elasri / Nada Zirari
- Durée : 7 jours (22/12/25 → 30/12/25)  

---

