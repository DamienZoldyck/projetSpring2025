package iut.fr.projetSpring2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iut.fr.projetSpring2025.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
} 