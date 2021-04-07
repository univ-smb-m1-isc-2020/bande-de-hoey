package info806.GestionBD.model;

import info806.GestionBD.dao.Format;
import info806.GestionBD.dao.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String etat;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "albumes", referencedColumnName = "id")
    private List<Album> albumes = new ArrayList<>() ;

    public Serie(){}

    public Serie(String etat, String titre, Genre type, int nbAlbum, Format format) {
        this.etat = etat;
        this.titre = titre;
        this.type = type;
        this.nbAlbum = nbAlbum;
        this.format = format;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "etat='" + etat + '\'' +
                ", titre='" + titre + '\'' +
                ", type=" + type +
                ", nbAlbum=" + nbAlbum +
                ", format=" + format +
                ", albumes=" + albumes +
                '}';
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
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
}
