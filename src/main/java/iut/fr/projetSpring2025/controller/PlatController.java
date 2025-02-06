package iut.fr.projetSpring2025.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plats")
public class PlatController {

    @GetMapping
    public String listPlats(Model model) {
        return "plats/list";
    }
} 