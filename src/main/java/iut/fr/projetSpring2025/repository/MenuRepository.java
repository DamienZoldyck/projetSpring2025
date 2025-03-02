package iut.fr.projetSpring2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iut.fr.projetSpring2025.model.Menu;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m WHERE LOWER(m.nom) LIKE LOWER(CONCAT('%', :nom, '%'))")
    List<Menu> findByNom(@Param("nom") String nom);
}