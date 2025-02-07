package iut.fr.projetSpring2025.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iut.fr.projetSpring2025.model.Menu;
import iut.fr.projetSpring2025.repository.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public List<Menu> getMenusWithFilters(Double prixMin, Double prixMax, Integer caloriesMin, Integer caloriesMax, String nom) {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .filter(menu -> (prixMin == null || menu.getPrix() >= prixMin) &&
                                (prixMax == null || menu.getPrix() <= prixMax) &&
                                (caloriesMin == null || menu.calculateTotalCalories() >= caloriesMin) &&
                                (caloriesMax == null || menu.calculateTotalCalories() <= caloriesMax) &&
                                (nom == null || menu.getNom().toLowerCase().contains(nom.toLowerCase())))
                .toList();
    }

    public List<Menu> findByNom(String nom) {
        return menuRepository.findByNom(nom);
    }

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
} 