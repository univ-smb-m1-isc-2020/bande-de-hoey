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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("serie")
@RestController
public class SerieController {

    private final SerieService serieService;

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping(path = "all")
    public List<Serie> getAllSeries(){
        return serieService.getAllSeries();
    }

    @PostMapping(path = "create")
    public void create(){
        Serie serie =  new Serie( "Abandonee", "serie1", "Action",2 , "BD");
        Serie serie2 =  new Serie( "EnCours", "serie2", "Comic",1 , "BD");
        Serie serie3 =  new Serie( "Terminee", "serie3", "Action",5 , "Livre");
        var listSerie = new ArrayList<Serie>();
        listSerie.add(serie);
        listSerie.add(serie2);
        listSerie.add(serie3);
        serieService.create(listSerie);
    }

    // Get
    @GetMapping(path = "byTitle")
    public Serie getByTitle(@RequestParam(value = "title") String title) {
        return serieService.getByTitle(title);
    }

    @GetMapping(path = "byType")
    public List<Serie> getByType(@RequestParam(value = "type") String type){
        return serieService.getByType(type);
    }

    @GetMapping(path = "byFormat")
    public List<Serie> getByFormat(@RequestParam(value = "format") String format){
        return serieService.getByFormat(format);
    }

    @GetMapping(path = "byEtat")
    public List<Serie> getByEtat(@RequestParam(value = "etat") String etat){
        return serieService.getByEtat(etat);
    }

    @GetMapping(path = "byAuteur")
    public List<Serie> getByAuteur(@RequestParam(value = "auteur") String auteur){
        return serieService.getByAuteur(auteur);
    }

    //Post
    public void addAuteur(String serie, Auteur auteur) {
        serieService.addAuteur(serie,auteur);
    }

    public void addAlbum(String titre, Album album) {
        serieService.addAlbum(titre, album);
    }


}
