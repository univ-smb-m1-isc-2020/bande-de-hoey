package info806.GestionBD.model;

import info806.GestionBD.dao.Etat;
import info806.GestionBD.dao.Format;
import info806.GestionBD.dao.Genre;

import javax.persistence.*;
import java.util.*;
import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Serie")
@Table(
        name = "Serie",
        uniqueConstraints = {
                @UniqueConstraint(name = "Serie_titre_unique", columnNames = "titre")
        }
)
public class Serie {

    @Id
    @SequenceGenerator(
            name = "serie_sequence",
            sequenceName = "serie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "serie_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "etat",
            nullable = false
    )
    private Etat etat;

    @Column(
            name = "titre",
            nullable = false
    )
    private String titre;

    @Column(
            name = "type",
            nullable = false
    )
    private Genre type;

    @Column(
            name = "nb_album",
            nullable = false
    )
    private int nbAlbum;

    @Column(
            name = "format",
            nullable = false
    )
    private Format format;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "albumes", referencedColumnName = "id")
    private List<Album> albumes = new ArrayList<>() ;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "auteurs", referencedColumnName = "id")
    private List<Auteur> auteurs = new ArrayList<>() ;

    public Serie(){}

    public Serie(Etat etat, String titre, Genre type, int nbAlbum, Format format) {
        this.etat = etat;
        this.titre = titre;
        this.type = type;
        this.nbAlbum = nbAlbum;
        this.format = format;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Genre getType() {
        return type;
    }

    public void setType(Genre type) {
        this.type = type;
    }

    public int getNbAlbum() {
        return nbAlbum;
    }

    public void setNbAlbum(int nbAlbum) {
        this.nbAlbum = nbAlbum;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", etat='" + etat + '\'' +
                ", titre='" + titre + '\'' +
                ", type=" + type +
                ", nbAlbum=" + nbAlbum +
                ", format=" + format +
                ", albumes=" + toStringAlbum(albumes) +
                ", auteurs=" + toStringAuteur(auteurs) +
                '}';
    }

    public ArrayList<Object> toStringAuteur(List<Auteur> l ){
        var res = new ArrayList<>();
        for(Auteur a: l){
            Dictionary dico = new Hashtable();
            dico.put("nom", a.getNom());
            dico.put("prenom", a.getPrenom());
            res.add(dico);
        }
        return res;
    }

    public ArrayList<Object> toStringAlbum(List<Album> l ){
        var res = new ArrayList<>();
        for(Album a: l){
            Dictionary dico = new Hashtable();
            dico.put("nom", a.getTitre());
            res.add(dico);
        }
        return res;
    }
}
