package iut.fr.projetSpring2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iut.fr.projetSpring2025.model.Menu;
import iut.fr.projetSpring2025.service.MenuService;
import iut.fr.projetSpring2025.service.PlatService;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private PlatService platService;

    @GetMapping
    public String listMenus(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        return "menus/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("plats", platService.getAllPlats());
        return "menus/form";
    }

    @PostMapping("/create")
    public String createMenu(@ModelAttribute Menu menu) {
        menuService.saveMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Menu menu = menuService.getMenuById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu Id:" + id));
        model.addAttribute("menu", menu);
        model.addAttribute("plats", platService.getAllPlats());
        return "menus/form";
    }

    @PostMapping("/edit/{id}")
    public String updateMenu(@PathVariable Long id, @ModelAttribute Menu menu) {
        menu.setId(id);
        menuService.saveMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return "redirect:/menus";
    }
} 