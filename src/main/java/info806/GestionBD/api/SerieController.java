package info806.GestionBD.api;

import info806.GestionBD.dao.Etat;
import info806.GestionBD.dao.Format;
import info806.GestionBD.dao.Genre;
import info806.GestionBD.model.Album;
import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("serie")
@RestController
public class SerieController {

    private final SerieService serieService;

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping(path = "all")
    public String getAllSeries(){
        return serieService.getAllSeries().toString();
    }

    @PostMapping(path = "create")
    public void create(){
        Serie serie =  new Serie( Etat.Abandonnee, "serie1", Genre.Action,2 , Format.BD);
        Serie serie2 =  new Serie( Etat.EnCours, "serie2", Genre.Comic,1 , Format.BD);
        Serie serie3 =  new Serie( Etat.Terminee, "serie3", Genre.Action,5 , Format.Livre);
        var listSerie = new ArrayList<Serie>();
        listSerie.add(serie);
        listSerie.add(serie2);
        listSerie.add(serie3);
        serieService.create(listSerie);
    }

    // Get
    @GetMapping(path = "byTitle")
    public Serie getByTitle(@RequestBody String title) {
        return serieService.getByTitle(title);
    }

    @GetMapping(path = "byType")
    public String getByType(@RequestBody String type){
        return serieService.getByType(type).toString();
    }

    @GetMapping(path = "byFormat")
    public String getByFormat(@RequestBody String format){
        return serieService.getByFormat(format).toString();
    }

    @GetMapping(path = "byEtat")
    public String getByEtat(@RequestBody String etat){
        return serieService.getByEtat(etat).toString();
    }

    @GetMapping(path = "byAuteur")
    public String getByAuteur(@RequestBody Auteur auteur){
        return serieService.getByAuteur(auteur).toString();
    }

    //Post
    public void addAuteur(String serie, Auteur auteur) {
        serieService.addAuteur(serie,auteur);
    }

    public void addAlbum(String titre, Album album) {
        serieService.addAlbum(titre, album);
    }


}
