package iut.fr.projetSpring2025.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import iut.fr.projetSpring2025.model.Menu;
import iut.fr.projetSpring2025.model.Plat;
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

    public byte[] generateMenusPdf() throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        
        document.open();
        
        Font titleFont = new Font(FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Liste des Menus", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        
        Stream.of("Nom", "Prix", "Nombre de plats", "Total calories")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(columnTitle));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setPadding(5);
                table.addCell(header);
            });
        
        for (Menu menu : getAllMenus()) {
            table.addCell(menu.getNom());
            table.addCell(String.format("%.2f €", menu.getPrix()));
            table.addCell(String.valueOf(menu.getPlats().size()));
            table.addCell(String.valueOf(menu.calculateTotalCalories()));
        }
        
        document.add(table);
        document.close();
        
        return outputStream.toByteArray();
    }

    public byte[] generateMenuPdf(Long menuId) throws DocumentException {
        Menu menu = getMenuById(menuId)
            .orElseThrow(() -> new IllegalArgumentException("Menu non trouvé"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        
        document.open();
        
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph(menu.getNom(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);
        
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        document.add(new Paragraph("Informations générales", subtitleFont));
        document.add(new Paragraph("Prix: " + String.format("%.2f €", menu.getPrix())));
        document.add(new Paragraph("Calories totales: " + menu.calculateTotalCalories()));
        document.add(new Paragraph("\n"));
        
        document.add(new Paragraph("Plats du menu", subtitleFont));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        
        Stream.of("Nom", "Catégorie", "Calories", "Valeurs nutritionnelles")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(columnTitle));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setPadding(5);
                table.addCell(header);
            });
        
        for (Plat plat : menu.getPlats()) {
            table.addCell(plat.getNom());
            table.addCell(plat.getCategorie().getNom());
            table.addCell(String.valueOf(plat.getNbCalories()));
            
            String nutritionalInfo = String.format(
                "Protéines: %dg\nLipides: %dg\nGlucides: %dg",
                plat.getNbProteines(),
                plat.getNbLipides(),
                plat.getNbGlucides()
            );
            table.addCell(nutritionalInfo);
        }
        
        document.add(table);
        document.close();
        
        return outputStream.toByteArray();
    }
} 