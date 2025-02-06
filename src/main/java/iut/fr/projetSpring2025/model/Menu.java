package iut.fr.projetSpring2025.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private double prix;

    @ManyToMany
    @JoinTable(
        name = "menu_plat",
        joinColumns = @JoinColumn(name = "menu_id"),
        inverseJoinColumns = @JoinColumn(name = "plat_id")
    )
    private List<Plat> plats;

    // Getters et Setters
} 