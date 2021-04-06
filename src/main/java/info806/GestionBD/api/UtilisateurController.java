package info806.GestionBD.api;

import info806.GestionBD.service.repositories.AlbumRepository;
import info806.GestionBD.service.repositories.UtilisateurRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("utilisateur")
@RestController
public class UtilisateurController {

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    private String getAllUsers(){
        return utilisateurRepository.findAll().toString();
    }
}
