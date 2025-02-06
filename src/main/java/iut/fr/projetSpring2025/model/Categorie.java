package iut.fr.projetSpring2025.model;

import javax.persistence.*;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    // Pas de CRUD, donc pas de méthodes supplémentaires

    // Getters et Setters
} 