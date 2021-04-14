package info806.GestionBD.service;

import info806.GestionBD.model.Collection;
import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<Collection> getAllCollections(){
        return collectionRepository.findAll();
    }
}
