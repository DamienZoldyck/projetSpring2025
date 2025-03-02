package iut.fr.projetSpring2025.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import iut.fr.projetSpring2025.model.Plat;
import iut.fr.projetSpring2025.repository.PlatRepository;

@Service
public class PlatService {

    @Autowired
    private PlatRepository platRepository;

    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Optional<Plat> getPlatById(Long id) {
        return platRepository.findById(id);
    }

    public Plat savePlat(Plat plat) {
        return platRepository.save(plat);
    }

    public void deletePlat(Long id) {
        platRepository.deleteById(id);
    }

    public List<Plat> getPlatsWithFilters(String search, Long categorieId, Integer caloriesMin, Integer caloriesMax) {
        return platRepository.findWithFilters(search, categorieId, caloriesMin, caloriesMax, PageRequest.of(0, Integer.MAX_VALUE))
            .getContent();
    }

    public Page<Plat> getPlatsWithFilters(String search, Long categorieId, Integer caloriesMin, Integer caloriesMax, int page, int size) {
        return platRepository.findWithFilters(
            search, 
            categorieId, 
            caloriesMin, 
            caloriesMax, 
            PageRequest.of(page, size)
        );
    }
} 