package info806.GestionBD.service;

import info806.GestionBD.model.Favoris;
import info806.GestionBD.repositories.FavorisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorisService {

    private final FavorisRepository favorisRepository;

    @Autowired
    public FavorisService(FavorisRepository favorisRepository) {
        this.favorisRepository = favorisRepository;
    }

    public List<Favoris> getAllFavoris(){
        return favorisRepository.findAll();
    }
}
