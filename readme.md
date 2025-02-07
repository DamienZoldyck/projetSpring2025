# Système de Gestion de Menu de Restaurant
Damien Laboute et Antoine Leboucher

## Fonctionnalités Implémentées

### 1. Implémentation du Modèle de Données
- ✅ Implémentation complète de toutes les entités requises :
  - Menu (avec id, nom, prix)
  - Plat (avec id, nom, calories, lipides, protéines, glucides)
  - Catégorie (avec id, nom)
- ✅ Toutes les relations requises implémentées :
  - Many-to-Many entre Menu et Plat
  - Many-to-One entre Plat et Catégorie

### 2. Gestion des Plats
- ✅ Opérations CRUD complètes :
  - Création de nouveaux plats
  - Lecture/Liste des plats
  - Mise à jour des plats existants
  - Suppression des plats
- ✅ Système de filtrage implémenté :
  - Filtre par catégorie
  - Filtre par plage de calories (min/max)

### 3. Gestion des Menus
- ✅ Opérations CRUD :
  - Création de nouveaux menus
  - Lecture/Liste des menus
  - Mise à jour des menus existants
  - Suppression des menus
- ✅ Fonctionnalités avancées des menus :
  - Calcul automatique des calories totales pour chaque menu
  - Affichage du nombre de plats dans chaque menu
  - Gestion des prix
- ✅ Système de filtrage des menus :
  - Filtre par plage de prix
  - Filtre par plage de calories
  - Filtre par nom de menu

### 4. Interface Utilisateur
- ✅ Design responsive utilisant Bootstrap
- ✅ Barre de navigation avec structure de menu claire
- ✅ Formulaires propres et conviviaux
- ✅ Sélection améliorée des plats dans les menus utilisant Select2 pour une meilleure expérience utilisateur

### 5. Exigences Techniques
- ✅ Configuration correcte du port (8086)
- ✅ Configuration de la base de données MariaDB
- ✅ Nom correct de la base de données (prjspring2025)