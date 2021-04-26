package info806.GestionBD.service;


import info806.GestionBD.model.*;
import info806.GestionBD.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    public void create(ArrayList<Utilisateur> listUtilisateur) {
        utilisateurRepository.saveAll(listUtilisateur);
    }

    public List<Utilisateur> getByName(String name) {
        var users = getAllUsers();
        var res = new ArrayList<Utilisateur>();
        for (Utilisateur u: users) {
            if(u.getNom().matches(name)){
                res.add(u);
            }
        };
        return res;
    }

    public List<Utilisateur> getByPseudo(String pseudo) {
        var users = getAllUsers();
        var res = new ArrayList<Utilisateur>();
        for (Utilisateur u: users) {
            if(u.getPseudo().matches(pseudo)){
                res.add(u);
            }
        }
        return res;
    }

    public Utilisateur getByMail(String mail) {
        var users = getAllUsers();
        for (Utilisateur u: users) {
            if(u.getMail().matches(mail)){
                return u;
            }
        }
        return null;
    }

    public void inscreption(Utilisateur user) {
        utilisateurRepository.save(user);
    }

    public boolean login(String mail, String mdp) {
        var user = getByMail(mail);
        boolean res = false;
        if(user != null){
            res = user.getMdp().matches(mdp);
        }
        Utilisateur.setConnectedUser(user);
        return res;
    }

    public Utilisateur getConnectedUser(){
        return Utilisateur.getConnectedUser();
    }

    public void delete(Utilisateur user){
        try{
            utilisateurRepository.delete(user);
        }catch (Exception e){
            System.out.println("Exception : "+e);
        }
    }

    public void addSerieToFavoris(Serie serie) {
        if(Utilisateur.getConnectedUser().getFavoris() == null){
            Favoris f = new Favoris(null,0,0,0);
            var u =Utilisateur.getConnectedUser();
            u.setFavoris(f);
            Utilisateur.setConnectedUser(u);
        }
        Utilisateur.getConnectedUser().getFavoris().getSeries().add(serie);
        var nb = Utilisateur.getConnectedUser().getFavoris().getSeries().size();
        Utilisateur.getConnectedUser().getFavoris().setNbSeries(nb);
        utilisateurRepository.save(Utilisateur.getConnectedUser());
    }

    public void addAlbumToFavoris(Album album) {
        if(Utilisateur.getConnectedUser().getFavoris() == null){
            Favoris f = new Favoris(null,0,0,0);
            var u =Utilisateur.getConnectedUser();
            u.setFavoris(f);
            Utilisateur.setConnectedUser(u);
        }
        Utilisateur.getConnectedUser().getFavoris().getAlbums().add(album);
        var nb = Utilisateur.getConnectedUser().getFavoris().getAlbums().size();
        Utilisateur.getConnectedUser().getFavoris().setNbAlbums(nb);
        utilisateurRepository.save(Utilisateur.getConnectedUser());
    }

    public void addAuteurToFavoris(Auteur auteur) {
        if(Utilisateur.getConnectedUser().getFavoris() == null){
            Favoris f = new Favoris(null,0,0,0);
            var u =Utilisateur.getConnectedUser();
            u.setFavoris(f);
            Utilisateur.setConnectedUser(u);
        }
        Utilisateur.getConnectedUser().getFavoris().getAuteurs().add(auteur);
        var nb = Utilisateur.getConnectedUser().getFavoris().getAuteurs().size();
        Utilisateur.getConnectedUser().getFavoris().setNbAuteurs(nb);
        utilisateurRepository.save(Utilisateur.getConnectedUser());
    }

    public void addAlbumToSuivis(Album album) {
        if(Utilisateur.getConnectedUser().getSuivis() == null){
            Suivis s = new Suivis(null,0,0,0);
            var u =Utilisateur.getConnectedUser();
            u.setSuivis(s);
            Utilisateur.setConnectedUser(u);
        }
        Utilisateur.getConnectedUser().getSuivis().getAlbums().add(album);
        var nb = Utilisateur.getConnectedUser().getSuivis().getAlbums().size();
        Utilisateur.getConnectedUser().getSuivis().setNbAlbums(nb);
        utilisateurRepository.save(Utilisateur.getConnectedUser());
    }

    public void addAlbumToCollections(Album album) {
        if(Utilisateur.getConnectedUser().getCollections() == null){
            Collection c = new Collection(null,0,0,0);
            var u =Utilisateur.getConnectedUser();
            u.setCollections(c);
            Utilisateur.setConnectedUser(u);
        }
        Utilisateur.getConnectedUser().getCollections().getAlbums().add(album);
        var nb = Utilisateur.getConnectedUser().getCollections().getAlbums().size();
        Utilisateur.getConnectedUser().getCollections().setNbAlbums(nb);
        utilisateurRepository.save(Utilisateur.getConnectedUser());
    }
}
