package iut.fr.projetSpring2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iut.fr.projetSpring2025.model.Plat;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {
    // Les méthodes de base CRUD sont automatiquement fournies par JpaRepository
} 