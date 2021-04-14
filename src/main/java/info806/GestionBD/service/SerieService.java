package info806.GestionBD.service;

import info806.GestionBD.dao.Genre;
import info806.GestionBD.model.Album;
import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    @Autowired
    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<Serie> getAllSeries(){
        return serieRepository.findAll();
    }

    public void create(ArrayList<Serie> listSerie) {
        serieRepository.saveAll(listSerie);
    }

    public Serie getByTitle(String title) {
        var series = getAllSeries();
        for (Serie s: series) {
            if(s.getTitre().matches(title)){
                return s;
            }
        };
        return null;
    }

    public void addAuteur(String titre, Auteur auteur) {
        var serie = getByTitle(titre);
        var flag = true;
        for(Auteur a: serie.getAuteurs()){
            if(a.getNom().matches(auteur.getNom()) && a.getPrenom().matches(auteur.getPrenom())){
                flag = false;
            }
        }if(flag) {
            serie.getAuteurs().add(auteur);
            serieRepository.save(serie);
        }
    }

    public void addAlbum(String titre, Album album) {
        var serie = getByTitle(titre);
        var flag = true;
        for(Album a: serie.getAlbumes()){
            if(a.getTitre().matches(album.getTitre())){
                flag = false;
            }
        }if(flag) {
            serie.getAlbumes().add(album);
            serieRepository.save(serie);
        }
    }

    public List<Serie> getByType(String type) {
        var series = getAllSeries();
        var res = new ArrayList<Serie>();
        for(Serie s:series){
            if(s.getType().toString().matches(type)){
                res.add(s);
            }
        }
        return res;
    }

    public List<Serie> getByFormat(String format) {
        var series = getAllSeries();
        var res = new ArrayList<Serie>();
        for(Serie s:series){
            if(s.getFormat().toString().matches(format)){
                res.add(s);
            }
        }
        return res;
    }

    public List<Serie> getByEtat(String etat) {
        var series = getAllSeries();
        var res = new ArrayList<Serie>();
        for(Serie s:series){
            if(s.getEtat().toString().matches(etat)){
                res.add(s);
            }
        }
        return res;
    }

    public List<Serie> getByAuteur(Auteur auteur) {
        var series = getAllSeries();
        var res = new ArrayList<Serie>();
        for (int i = 0; i<series.size(); i++) {
            var auteurs = series.get(i).getAuteurs();
            for (Auteur a: auteurs){
                if (a.getNom().matches(auteur.getNom()) && a.getPrenom().matches(auteur.getPrenom())){
                    res.add(series.get(i));
                }
            }
        }
        return res;
    }
}
