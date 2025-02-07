package iut.fr.projetSpring2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;

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
    public String listMenus(
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        
        int pageSize = 10;
        Page<Menu> menuPage = menuService.getAllMenusPaginated(page, pageSize);
        
        model.addAttribute("menus", menuPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", menuPage.getTotalPages());
        model.addAttribute("totalItems", menuPage.getTotalElements());
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

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportMenusPdf() {
        try {
            byte[] pdfBytes = menuService.generateMenusPdf();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "menus.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/export/pdf")
    public ResponseEntity<byte[]> exportMenuPdf(@PathVariable Long id) {
        try {
            byte[] pdfBytes = menuService.generateMenuPdf(id);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "menu-" + id + ".pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
} 