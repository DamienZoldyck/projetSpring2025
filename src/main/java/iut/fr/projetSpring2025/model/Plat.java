package iut.fr.projetSpring2025.model;

import javax.persistence.*;

@Entity
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int nbCalories;
    private int nbLipides;
    private int nbProteines;
    private int nbGlucides;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    // Getters et Setters
} 