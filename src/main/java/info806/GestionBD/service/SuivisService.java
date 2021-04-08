package info806.GestionBD.service;

import info806.GestionBD.model.Suivis;
import info806.GestionBD.repositories.SuivisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuivisService {

    private final SuivisRepository suivisRepository;

    @Autowired
    public SuivisService(SuivisRepository suivisRepository) {
        this.suivisRepository = suivisRepository;
    }

    public List<Suivis> getAllSuivis(){
        return suivisRepository.findAll();
    }
}
