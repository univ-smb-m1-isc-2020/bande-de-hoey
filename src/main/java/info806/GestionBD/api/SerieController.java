package info806.GestionBD.api;

import info806.GestionBD.service.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("serie")
@RestController
public class SerieController {

    private SerieRepository serieRepository;

    @Autowired
    public SerieController(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @GetMapping
    public String getAllAlbums(){
        return serieRepository.findAll().toString();
    }


}
