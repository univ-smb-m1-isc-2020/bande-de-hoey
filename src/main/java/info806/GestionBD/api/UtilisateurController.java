package info806.GestionBD.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public List<Utilisateur> getAllUsers(){
        return utilisateurService.getAllUsers();
    }

    @GetMapping(path = "byName")
    public List<Utilisateur> getByName(@RequestBody String name){
        return utilisateurService.getByName(name);
    }

    @GetMapping(path = "byPseudo")
    public List<Utilisateur> getByPseudo(@RequestBody String pseudo){
        return utilisateurService.getByPseudo(pseudo);
    }

    @GetMapping(path = "byMail")
    public Utilisateur getByMail(@RequestBody String mail){
        return utilisateurService.getByMail(mail);
    }

    @GetMapping(path = "login")
    public boolean login(@RequestParam(value = "mail") String mail,@RequestParam(value = "mdp") String mdp){
        System.out.println("mail and mdp : "+ mail +" "+ mdp );
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

//    @PostMapping(path = "inscription")
    @RequestMapping(method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE, path = "inscription")
    public void inscreption(@RequestBody Utilisateur user){
        utilisateurService.inscreption(user);
    }

}
