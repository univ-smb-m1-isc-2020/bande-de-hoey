package info806.GestionBD.api;

import info806.GestionBD.model.Serie;
import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.repositories.SerieRepository;
import info806.GestionBD.repositories.UtilisateurRepository;
import info806.GestionBD.service.SerieService;
import info806.GestionBD.service.UtilisateurService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UtilisateurControllerTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private UtilisateurController utilisateurController;

    @BeforeAll
    void setUp() {
        var utilisateurService = new UtilisateurService(utilisateurRepository);
        utilisateurController = new UtilisateurController(utilisateurService);
        utilisateurController.create();
    }

    @Test
    void getAllUsers() {
        var list = utilisateurController.getAllUsers();
        for (Utilisateur u : list) {
            if (u.getNom().matches("passieux")) {
                assertTrue(u.getNom().matches("passieux"));
            } else if (u.getNom().matches("wagner")) {
                assertTrue(u.getNom().matches("wagner"));
            } else if (u.getNom().matches("alrasheed")) {
                assertTrue(u.getNom().matches("alrasheed"));
            } else {
                assertFalse(true, "test getAllUsers failed");
            }
        }
    }

    @Test
    void getByName() {
        String name = "passieux";
        var list = utilisateurController.getByName(name);
        assertTrue(list.get(0).getNom().matches(name));
    }

    @Test
    void getByPseudo() {
        String pseudo = "vert";
        var list = utilisateurController.getByPseudo(pseudo);
        assertTrue(list.get(0).getPseudo().matches(pseudo));
    }

    @Test
    void getByMail() {
        String mail = "vert@gmail.com";
        var u = utilisateurController.getByMail(mail);
        assertTrue(u.getMail().matches(mail));
    }

    @Test
    void login() {
    }

    @Test
    void inscreption() {
    }
}