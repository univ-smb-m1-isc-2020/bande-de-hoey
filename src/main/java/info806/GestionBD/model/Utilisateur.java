package info806.GestionBD.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Utilisateur")
@Table(
        name = "Utilisateur",
        uniqueConstraints = {
                @UniqueConstraint(name = "utilisateur_mail_unique", columnNames = "mail")
        }
)
public class Utilisateur{

    @Id
    @SequenceGenerator(
            name = "utilisateur_sequence",
            sequenceName = "utilisateur_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "utilisateur_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "pseudo",
            nullable = false
    )
    private String pseudo;

    @Column(
            name = "nom",
            nullable = false
    )
    private String nom;

    @Column(
            name = "prenom",
            nullable = false
    )
    private String prenom;

    @Column(
            name = "mail",
            nullable = false
    )
    private String mail;

    @Column(
            name = "mdp",
            nullable = false
    )
    private String mdp;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favoris", referencedColumnName = "id")
    private List<Favoris> favoris = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "suivis", referencedColumnName = "id")
    private List<Suivis> suivis = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "collections", referencedColumnName = "id")
    private List<Collection> collections = new ArrayList<>();


    public Utilisateur(String pseudo, String nom, String prenom, String mail, String mdp) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
    }

    public Utilisateur(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public List<Favoris> getFavoris() {
        return favoris;
    }

    public void setFavoris(List<Favoris> favoris) {
        this.favoris = favoris;
    }

    public List<Suivis> getSuivis() {
        return suivis;
    }

    public void setSuivis(List<Suivis> suivis) {
        this.suivis = suivis;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }
}
