package info806.GestionBD.api;

import info806.GestionBD.model.Album;
import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("auteur")
@RestController
public class AuteurController {

    private final AuteurService auteurService;

    @Autowired
    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }

    // Get
    @GetMapping(path = "all")
    public List<Auteur> getAllAuteurs(){
        return auteurService.getAllAuteurs();
    }

    @GetMapping("byName")
    public Auteur getByName(String nom){
        return auteurService.getByName(nom);
    }

    @GetMapping(path = "byAlbum")
    public List<Auteur> getByAlbum(@RequestBody String titre){
        return auteurService.getByAlbum(titre);
    }

    @GetMapping(path = "bySerie")
    public List<Auteur> getBySerie(@RequestBody String title){
        return auteurService.getBySerie(title);
    }

    // Post
    public void create(){
        Auteur au1 = new Auteur("clerc", "greg");
        Auteur au2 = new Auteur("hoey", "rob");
        var listAuteur = new ArrayList<Auteur>();
        listAuteur.add(au1);
        listAuteur.add(au2);
        auteurService.create(listAuteur);
    }

    @PostMapping(path = "addAlbum")
    public void addAlbum(String nom, Album album){
        auteurService.addAlbum(nom, album);
    }

    public void addSerie(String clerc, Serie serie1) {
        auteurService.addSerie(clerc, serie1);
    }
}
