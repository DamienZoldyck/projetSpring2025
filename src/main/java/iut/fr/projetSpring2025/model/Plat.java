package iut.fr.projetSpring2025.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    // Constructeurs
    public Plat() {
    }

    public Plat(String nom, int nbCalories, int nbLipides, int nbProteines, int nbGlucides, Categorie categorie) {
        this.nom = nom;
        this.nbCalories = nbCalories;
        this.nbLipides = nbLipides;
        this.nbProteines = nbProteines;
        this.nbGlucides = nbGlucides;
        this.categorie = categorie;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNbCalories() {
        return nbCalories;
    }

    public int getNbLipides() {
        return nbLipides;
    }

    public int getNbProteines() {
        return nbProteines;
    }

    public int getNbGlucides() {
        return nbGlucides;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNbCalories(int nbCalories) {
        this.nbCalories = nbCalories;
    }

    public void setNbLipides(int nbLipides) {
        this.nbLipides = nbLipides;
    }

    public void setNbProteines(int nbProteines) {
        this.nbProteines = nbProteines;
    }

    public void setNbGlucides(int nbGlucides) {
        this.nbGlucides = nbGlucides;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    // Méthodes utilitaires
    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nbCalories=" + nbCalories +
                ", nbLipides=" + nbLipides +
                ", nbProteines=" + nbProteines +
                ", nbGlucides=" + nbGlucides +
                ", categorie=" + (categorie != null ? categorie.getNom() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plat plat = (Plat) o;
        return id != null && id.equals(plat.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
} 