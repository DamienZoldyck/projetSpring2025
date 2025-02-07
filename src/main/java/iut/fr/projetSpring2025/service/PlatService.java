package iut.fr.projetSpring2025.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Plat> getPlatsWithFilters(Long categorieId, Integer caloriesMin, Integer caloriesMax) {
        return platRepository.findWithFilters(categorieId, caloriesMin, caloriesMax);
    }
} 