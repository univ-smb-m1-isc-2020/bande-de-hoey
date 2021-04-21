package info806.GestionBD.service;


import info806.GestionBD.model.Album;
import info806.GestionBD.model.Utilisateur;
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
        System.out.println(user.toString());
        utilisateurRepository.save(user);
    }

    public boolean login(String mail, String mdp) {
        var user = getByMail(mail);
        boolean res = false;
        if(user != null){
            res = user.getMdp().matches(mdp);
        }
        return res;
    }
}
