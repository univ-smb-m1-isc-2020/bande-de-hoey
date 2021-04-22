package info806.GestionBD.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import info806.GestionBD.dao.Format;
import info806.GestionBD.dao.Genre;
import info806.GestionBD.model.Album;
import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("album")
@RestController
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping("all")
    @GetMapping
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @RequestMapping("byId")
    @GetMapping
    public Album getById(@RequestBody Long id) {
        return albumService.getById(id);
    }


    @GetMapping(path = "byTitle")
    public Album getByTitle(@RequestParam(value = "title") String title) {
        return albumService.getByTitle(title);
    }

    @GetMapping(path = "byISBN")
    public Album getByISBN(@RequestParam(value = "isbn") String isbn) {
        return albumService.getByISBN(isbn);
    }

    @GetMapping(path = "byAuteur")
    public List<Album> getByAuteur(@RequestParam(value = "auteur") String auteur) {
        return albumService.getByAuteur(auteur);
    }

    @GetMapping(path ="bySerie" )
    public List<Album> getBySerie(@RequestParam(value = "serie") String title) {
        return albumService.getBySerie(title);
    }

    // POST
    @PostMapping(path = "add")
    public void addAlbum2(@RequestBody Album album){
        System.out.println(album + "yeeeees");
        albumService.addAlbum2(album);
    }

    @PostMapping(path = "create")
    public void create(){
        Album album1 = new Album("Action","12","hoey1","image", "BD",1);
        Album album2 = new Album("Action","13","hoey2","image", "BD",2);
        Album album3 = new Album("Comedie","14","hoey3","image", "BD",3);
        var listAlbum = new ArrayList<Album>();
        listAlbum.add(album1);
        listAlbum.add(album2);
        listAlbum.add(album3);
        albumService.create(listAlbum);
    }

    @PostMapping(path = "createBertrand")
    public void createBertrand(Album album){
        albumService.createBertrand(album);
    }

    public void addAuther(String title, Auteur auteur){
        albumService.addAuther(title,auteur);
    }


    public void addSerie(String title, Serie serie){
        albumService.addSerie(title, serie);
    }

}
