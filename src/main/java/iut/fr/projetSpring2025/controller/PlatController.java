package iut.fr.projetSpring2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iut.fr.projetSpring2025.model.Plat;
import iut.fr.projetSpring2025.service.CategorieService;
import iut.fr.projetSpring2025.service.PlatService;

@Controller
@RequestMapping("/plats")
public class PlatController {

    @Autowired
    private PlatService platService;

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public String listPlats(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String categorie,
            @RequestParam(required = false) Integer caloriesMin,
            @RequestParam(required = false) Integer caloriesMax,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        
        Long categorieId = null;
        try {
            if (categorie != null && !categorie.isEmpty()) {
                categorieId = Long.parseLong(categorie);
            }
        } catch (NumberFormatException e) {
            // Ignorer l'erreur de parsing
        }

        int pageSize = 10;
        Page<Plat> platPage = platService.getPlatsWithFilters(search, categorieId, caloriesMin, caloriesMax, page, pageSize);
        
        model.addAttribute("plats", platPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", platPage.getTotalPages());
        model.addAttribute("totalItems", platPage.getTotalElements());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "plats/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("plat", new Plat());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "plats/form";
    }

    @PostMapping("/create")
    public String createPlat(@ModelAttribute Plat plat) {
        platService.savePlat(plat);
        return "redirect:/plats";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Plat plat = platService.getPlatById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid plat Id:" + id));
        model.addAttribute("plat", plat);
        model.addAttribute("categories", categorieService.getAllCategories());
        return "plats/form";
    }

    @PostMapping("/edit/{id}")
    public String updatePlat(@PathVariable Long id, @ModelAttribute Plat plat) {
        plat.setId(id);
        platService.savePlat(plat);
        return "redirect:/plats";
    }

    @GetMapping("/delete/{id}")
    public String deletePlat(@PathVariable Long id) {
        platService.deletePlat(id);
        return "redirect:/plats";
    }
} 