package info806.GestionBD.api;

import info806.GestionBD.service.FavorisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("favoris")
@RestController
public class FavorisController {

    private final FavorisService favorisService;

    @Autowired
    public FavorisController(FavorisService favorisService) {
        this.favorisService = favorisService;
    }

    @GetMapping
    public String getAllFavoris(){
        return favorisService.getAllFavoris().toString();
    }
}
