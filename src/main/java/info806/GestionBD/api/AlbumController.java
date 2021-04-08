package info806.GestionBD.api;

import info806.GestionBD.repositories.AlbumRepository;
import info806.GestionBD.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("album")
@RestController
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @GetMapping
    public String getAllAlbums(){
        return albumService.getAllAlbums().toString();
    }

}
