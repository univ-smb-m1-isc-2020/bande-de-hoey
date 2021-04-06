package info806.GestionBD;

import info806.GestionBD.dao.Format;
import info806.GestionBD.dao.Genre;
import info806.GestionBD.model.Album;
import info806.GestionBD.model.Serie;
import info806.GestionBD.model.Utilisateur;
import info806.GestionBD.service.repositories.AlbumRepository;
import info806.GestionBD.service.repositories.SerieRepository;
import info806.GestionBD.service.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GestionBdApplication implements CommandLineRunner{

	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private SerieRepository serieRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;

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
		//test 1
		Album test = new Album(Genre.Action,12,"test","image", Format.BD,1);
		albumRepository.save(test);

		//test2
		Album test2 = new Album(Genre.Action,12,"test2","image", Format.BD,1);
		Album test3 = new Album(Genre.Action,12,"test3","image", Format.BD,1);

		Serie serie =  new Serie("etat", "serie1",Genre.Action,2 ,Format.BD);

		serie.getAlbumes().add(test2);
		serie.getAlbumes().add(test3);

		serieRepository.save(serie);

		Utilisateur u = new Utilisateur("vert", "passieux", "bertrand", "vert@gmail.com", "hoey");

		utilisateurRepository.save(u);

	}
}
