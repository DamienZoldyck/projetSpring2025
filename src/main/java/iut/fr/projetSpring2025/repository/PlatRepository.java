package iut.fr.projetSpring2025.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iut.fr.projetSpring2025.model.Plat;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {

    @Query("SELECT p FROM Plat p WHERE " +
           "(:categorieId IS NULL OR p.categorie.id = :categorieId) AND " +
           "(:caloriesMin IS NULL OR p.nbCalories >= :caloriesMin) AND " +
           "(:caloriesMax IS NULL OR p.nbCalories <= :caloriesMax)")
    List<Plat> findWithFilters(
        @Param("categorieId") Long categorieId,
        @Param("caloriesMin") Integer caloriesMin,
        @Param("caloriesMax") Integer caloriesMax
    );
} 