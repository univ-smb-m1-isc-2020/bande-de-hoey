package info806.GestionBD.api;

import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("utilisateur")
@RestController
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Get

    @GetMapping(path = "all")
    public String getAllUsers(){
        return utilisateurService.getAllUsers().toString();
    }

    @GetMapping(path = "byName")
    public String getByName(@RequestBody String name){
        return utilisateurService.getByName(name).toString();
    }

    @GetMapping(path = "byPseudo")
    public String getByPseudo(@RequestBody String pseudo){
        return utilisateurService.getByPseudo(pseudo).toString();
    }

    @GetMapping(path = "byMail")
    public String getByMail(@RequestBody String mail){
        return utilisateurService.getByMail(mail).toString();
    }

    @GetMapping(path = "login")
    public boolean login(String mail, String mdp){
        return utilisateurService.login(mail,mdp);
    }

    // Post
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

    @PostMapping(path = "inscreption")
    public void inscreption(Utilisateur user){
        utilisateurService.inscreption(user);
    }

}
