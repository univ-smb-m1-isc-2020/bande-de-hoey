package info806.GestionBD.api;

import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.repositories.AuteurRepository;
import info806.GestionBD.repositories.SerieRepository;
import info806.GestionBD.service.AuteurService;
import info806.GestionBD.service.SerieService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SerieControllerTest {

    @Autowired
    private SerieRepository serieRepository;

    private SerieController serieController;

    @BeforeAll
    void setUp() {
        var serieService = new SerieService(serieRepository);
        serieController = new SerieController(serieService);
        serieController.create();
    }

    @Test
    void getAllSeries() {
        var list = serieController.getAllSeries();
        for (Serie s : list) {
            if (s.getTitre().matches("serie1")) {
                assertTrue(s.getTitre().matches("serie1"));
            } else if (s.getTitre().matches("serie2")) {
                assertTrue(s.getTitre().matches("serie2"));
            } else if (s.getTitre().matches("serie3")) {
                assertTrue(s.getTitre().matches("serie3"));
            } else {
                assertFalse(true, "test getAllSerie failed");
            }
        }
    }

    @Test
    void getByTitle() {
        String title = "serie1";
        var serie = serieController.getByTitle(title);
        assertNotNull(serie);
    }

    @Test
    void getByType() {
        var type = "Action";
        var list = serieController.getByType(type);
        for (Serie s : list) {
            assertTrue(s.getType().toString().matches(type));
        }
    }

    @Test
    void getByFormat() {
        var format = "BD";
        var list = serieController.getByFormat(format);
        for (Serie s : list) {
            assertTrue(s.getFormat().toString().matches(format));
        }
        assertTrue(list.size() == 2);
    }

    @Test
    void getByEtat() {
        var etat = "Terminee";
        var list = serieController.getByEtat(etat);
        for (Serie s : list) {
            assertTrue(s.getEtat().toString().matches(etat));
        }
        assertTrue(list.size() == 1);
    }

    @Test
    void getByAuteur() {
    }

    @Test
    void addAuteur() {
    }

    @Test
    void addAlbum() {
    }
}