package iut.fr.projetSpring2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iut.fr.projetSpring2025.model.Plat;
import iut.fr.projetSpring2025.service.PlatService;

@Controller
@RequestMapping("/plats")
public class PlatController {

    @Autowired
    private PlatService platService;

    @GetMapping
    public String listPlats(Model model) {
        model.addAttribute("plats", platService.getAllPlats());
        return "plats/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("plat", new Plat());
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