package info806.GestionBD.api;


import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("collection")
@RestController
public class CollectionController {

    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping(path = "all")
    public String getAllCollections(){
        return collectionService.getAllCollections().toString();
    }

}
