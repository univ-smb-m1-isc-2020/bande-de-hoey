package info806.GestionBD;

import info806.GestionBD.api.AlbumController;
import info806.GestionBD.api.AuteurController;
import info806.GestionBD.api.SerieController;
import info806.GestionBD.api.UtilisateurController;
import info806.GestionBD.dao.Format;
import info806.GestionBD.dao.Genre;
import info806.GestionBD.model.Album;
import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.repositories.AlbumRepository;
import info806.GestionBD.repositories.AuteurRepository;
import info806.GestionBD.repositories.SerieRepository;
import info806.GestionBD.repositories.UtilisateurRepository;
import info806.GestionBD.service.AlbumService;
import info806.GestionBD.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

/*@ComponentScan({"info806.GestionBD.api", "info806.GestionBD.repositories"})
@EnableJpaRepositories("info806.GestionBD.repositories")*/
@SpringBootApplication
public class GestionBdApplication implements CommandLineRunner{

	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private SerieRepository serieRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private AuteurRepository auteurRepository;


	@Autowired
	private AlbumController albumController;
	@Autowired
	private AuteurController auteurController;
	@Autowired
	private SerieController serieController;
	@Autowired
	private UtilisateurController utilisateurController;

	public static SerieRepository testS;
	public static void main(String[] args) {
		SpringApplication.run(GestionBdApplication.class, args);
		//System.out.println(albumRepository.findAll().get(0));
		System.out.println("hooey");

	}

	/*CommandLineRunner commandLineRunner(AlbumRepository albumRepository){
		return args -> {
			Album test = new Album(Genre.Action,12,"test","image", Format.BD,1);
			albumRepository.save(test);
		};
	}*/

	@Override
	public void run(String... args) throws Exception {
		//Create
		albumController.create();
		auteurController.create();
		serieController.create();
		utilisateurController.create();

		//Add
		/*var auteur = auteurController.getByName("clerc");

		var album1 = albumController.getByTitle("hoey1");
		var	album2 = albumController.getByTitle("hoey2");

		var serie1 = serieController.getByTitle("serie1");
		var serie2 = serieController.getByTitle("serie2");

		albumController.addAuther("hoey1",auteur);
		albumController.addAuther("hoey2",auteur);

		//doesn't work
		albumController.addSerie("hoey1",serie1);


		auteurController.addAlbum("clerc",album1);
		auteurController.addAlbum("clerc",album2);


		auteurController.addSerie("clerc", serie1);
		auteurController.addSerie("clerc", serie2);

		serieController.addAuteur("serie1", auteur );
		serieController.addAlbum("serie1", album1);*/



		/*test2.getAuteurs().add(au1);
		test2.getAuteurs().add(au2);
		test3.getAuteurs().add(au2);*/
		/*au1.getAlbums().add(test2);
		au2.getAlbums().add(test2);
		au2.getAlbums().add(test3);*/

		/*test2.setSerie(serie);
		test3.setSerie(serie);*/


		/*serie.getAlbumes().add(test2);
		serie.getAlbumes().add(test3);

		test.setSerie(serie);*/


		/*serieRepository.save(serie);

		albumRepository.save(test);*/

		//utilisateurRepository.save(u);

		//auteurRepository.save(au1);

		/*serieRepository.saveAll(ListSerie);

		albumRepository.saveAll(ListAlbum);

		auteurRepository.saveAll(listAuteur);
		//auteurRepository.save(au2);

		albumRepository.saveAll(ListAlbum);*/
		//albumRepository.save(test3);
	}
}
