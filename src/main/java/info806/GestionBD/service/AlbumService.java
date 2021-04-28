package info806.GestionBD.service;


import info806.GestionBD.model.Album;
import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {


    private AlbumRepository albumRepository;


    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public AlbumService(){}

    public String test(){
        return "hello";
    }
    //public AlbumService(){}
    public List<Album> getAllAlbums(){
        var l = albumRepository.findAll();
        List<Album> res = new ArrayList<>();
        res.add(l.get(0));
        for(var i:l){
            boolean canAdd = true;
            for(var j:res){
                if(i.getTitre().matches(j.getTitre()) ){
                    canAdd = false;
                }
            }
            if(canAdd) res.add(i);
        }
        return res;
    }

    public Album getById(Long id){
        return albumRepository.findById(id).get();
    }


    public Album getByTitle(String title){
        var albums = getAllAlbums();
        for (Album a: albums) {
            if(a.getTitre().matches(title)){
                return a;
            }
        };
        return null;
    }

    public Album getByISBN(String isbn) {
        var albums = getAllAlbums();
        for (Album a: albums) {
            if(a.getIsbn().matches(isbn)){
                return a;
            }
        };
        return null;
    }

    public List<Album> getByAuteur(String nom) {
        var albums = getAllAlbums();
        var res = new ArrayList<Album>();
        for (int i = 0; i<albums.size(); i++) {
            var auteurs = albums.get(i).getAuteurs();
            for (Auteur a: auteurs){
                if (a.getNom().matches(nom)){
                    res.add(albums.get(i));
                }
            }
        }
        return res;
    }

    public List<Album> getBySerie(String title) {
        var albums = getAllAlbums();
        var res = new ArrayList<Album>();
        for (int i = 0; i<albums.size(); i++) {
            if(albums.get(i).getSerie() != null){
                if(albums.get(i).getSerie().getTitre().matches(title)){
                    res.add(albums.get(i));
                }
            }
        }
        return res;
    }

    public void addAlbum(Album album) {
        albumRepository.save(album);
    }

    public void addAlbum2(Album album) {
        albumRepository.save(album);
    }

    public void create(ArrayList<Album> listAlbum) {
        albumRepository.saveAll(listAlbum);
    }

    public void addAuther(String title, Auteur auteur) {
        var album = getByTitle(title);
        var flag = true;
        for(Auteur a: album.getAuteurs()){
            if(a.getPrenom().matches(auteur.getPrenom()) && a.getNom().matches(auteur.getNom())){
                flag = false;
            }
        }if(flag){
            album.getAuteurs().add(auteur);
            albumRepository.save(album);
        }
    }

    public void addSerie(String title, Serie serie){
        var album = getByTitle(title);
        album.setSerie(serie);
        albumRepository.save(album);
    }

    public void createBertrand(Album album) {
        albumRepository.save(album);
    }
}
