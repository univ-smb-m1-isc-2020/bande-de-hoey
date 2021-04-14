package info806.GestionBD.api;

import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequestMapping("utilisateur")
@RestController
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    @GetMapping(path = "all")
    public String getAllUsers(){
        return utilisateurService.getAllUsers().toString();
    }

    @PostMapping(path = "create")
    public void create(){
        Utilisateur u = new Utilisateur("vert", "passieux", "bertrand", "vert@gmail.com", "hoey");
        Utilisateur u2 = new Utilisateur("layton", "wagner", "robin", "layton@gmail.com", "hoey");
        Utilisateur u3 = new Utilisateur("hoz", "alrasheed", "hamze", "hamze@gmail.com", "hoey");
        var listUtilisateur = new ArrayList<Utilisateur>();
        listUtilisateur.add(u);
        listUtilisateur.add(u2);
        listUtilisateur.add(u3);
        utilisateurService.create(listUtilisateur);
    }

}
