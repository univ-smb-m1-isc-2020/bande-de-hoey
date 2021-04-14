package info806.GestionBD.service;


import info806.GestionBD.model.Album;
import info806.GestionBD.model.Auteur;
import info806.GestionBD.model.Serie;
import info806.GestionBD.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbums(){
        return albumRepository.findAll();
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
            for (Auteur auteur: auteurs){
                if (auteur.getNom().matches(nom)){
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
}
