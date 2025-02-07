package iut.fr.projetSpring2025.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

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
    private List<Plat> plats = new ArrayList<>();

    // Constructeurs
    public Menu() {
    }

    public Menu(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setPlats(List<Plat> plats) {
        this.plats = plats;
    }

    // Méthodes utilitaires pour la gestion des plats
    public void addPlat(Plat plat) {
        if (plats == null) {
            plats = new ArrayList<>();
        }
        plats.add(plat);
    }

    public void removePlat(Plat plat) {
        if (plats != null) {
            plats.remove(plat);
        }
    }

    // Méthode pour calculer le total des calories
    public int calculateTotalCalories() {
        return plats.stream().mapToInt(Plat::getNbCalories).sum();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", nombrePlats=" + (plats != null ? plats.size() : 0) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id != null && id.equals(menu.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
} 