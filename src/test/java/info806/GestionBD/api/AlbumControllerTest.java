package info806.GestionBD.api;

import info806.GestionBD.model.Album;
import info806.GestionBD.repositories.AlbumRepository;
import info806.GestionBD.repositories.AuteurRepository;
import info806.GestionBD.service.AlbumService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.PrivateKey;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlbumControllerTest {

    @Autowired
    private AlbumRepository albumRepository;

    private AlbumController albumController;

    @BeforeAll
    void setup() {
        var albumService = new AlbumService(albumRepository);
        albumController = new AlbumController(albumService);
        albumController.create();
    }

    @Test
    void getAllAlbums() {
        var list = albumController.getAllAlbums();
        for (Album a : list) {
            if (a.getTitre().matches("hoey1")) {
                assertTrue(a.getTitre().matches("hoey1"));
            } else if (a.getTitre().matches("hoey2")) {
                assertTrue(a.getTitre().matches("hoey2"));
            } else if (a.getTitre().matches("hoey3")) {
                assertTrue(a.getTitre().matches("hoey3"));
            } else {
                assertFalse(true, "test getAllAlbums failed");
            }
        }
    }

    @Test
    void getById() throws Exception {
        Long id = 1L;
        var album = albumController.getById(id);
        assertNotNull(album);
    }

    @Test
    void getByTitle() {
        String title = "hoey1";
        var album = albumController.getByTitle(title);
        assertNotNull(album);
    }

    @Test
    void getByISBN() {
        String isbn = "12";
        var album = albumController.getByISBN(isbn);
        assertNotNull(album);
    }

    @Test
    void getByAuteur() {
    }

    @Test
    void getBySerie() {
    }


    @Test
    void addAuther() {
    }

    @Test
    void addSerie() {
    }
}