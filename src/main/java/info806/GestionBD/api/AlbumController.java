package info806.GestionBD.api;

import info806.GestionBD.model.User;
import info806.GestionBD.service.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("album")
@RestController
public class AlbumController {


    private AlbumRepository albumRepository;

    @Autowired
    public AlbumController(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    @GetMapping
    public String getAllAlbums(){
        return albumRepository.findAll().toString();
    }

}
