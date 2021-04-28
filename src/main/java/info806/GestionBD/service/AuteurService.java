package info806.GestionBD.service;

import info806.GestionBD.model.Album;
import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.repositories.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuteurService {

    private final AuteurRepository auteurRepository;

    @Autowired
    public AuteurService(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }

    public List<Auteur> getAllAuteurs(){
        var l = auteurRepository.findAll();
        List<Auteur> res = new ArrayList<>();
        res.add(l.get(0));
        for(var i:l){
            boolean canAdd = true;
            for(var j:res){
                if(i.getNom().matches(j.getNom()) && i.getPrenom().matches(j.getPrenom())){
                    canAdd = false;
                }
            }
            if(canAdd) res.add(i);
        }
        return res;
    }

    public List<Auteur> getByAlbum(String titre) {
        var auteurs = getAllAuteurs();
        var res = new ArrayList<Auteur>();
        for (int i = 0; i<auteurs.size(); i++) {
            var albums = auteurs.get(i).getAlbums();
            for (int j = 0; j<albums.size(); j++) {
                if(albums.get(i).getTitre().matches(titre)){
                    res.add(auteurs.get(i));
                }
            }
        }
        return res;
    }

    public Auteur getByName(String nom) {
        var auteurs = getAllAuteurs();
        for (Auteur a: auteurs) {
            if(a.getNom().matches(nom)){
                return a;
            }
        };
        return null;
    }

    public List<Auteur> getBySerie(String title) {
        var auteurs = getAllAuteurs();
        var res = new ArrayList<Auteur>();
        for (int i = 0; i<auteurs.size(); i++) {
            var series = auteurs.get(i).getSeries();
            for (int j = 0; j<series.size(); j++) {
                if(series.get(j).getTitre().matches(title)){
                    res.add(auteurs.get(i));
                }
            }
        }
        return res;
    }

    public void create(List<Auteur> list) {
        auteurRepository.saveAll(list);
    }

    public void addAlbum(String nom, Album album) {
        var auteur = getByName(nom);
        var flag = true;
        if (auteur.getAlbums() != null){
            for (int i = 0; i < auteur.getAlbums().size(); i++) {
                if (auteur.getAlbums().get(i).getTitre().matches(album.getTitre())) {
                    flag = false;
                }
            }
            if (flag) {
                auteur.getAlbums().add(album);
                auteurRepository.save(auteur);
            }
        }
    }

    public void addSerie(String name, Serie serie) {
        var auteur = getByName(name);
        var flag = true;
        for(Serie s: auteur.getSeries()){
            if(s.getTitre().matches(serie.getTitre())){
                flag = false;
            }
        }if(flag) {
            auteur.getSeries().add(serie);
            auteurRepository.save(auteur);
        }
    }
}
