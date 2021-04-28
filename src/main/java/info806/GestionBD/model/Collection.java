package info806.GestionBD.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Collection")
@Table(
        name = "Collection"
/*        uniqueConstraints = {
                @UniqueConstraint(name ="collection_titre_unique", columnNames = "titre")
        }*/
)
public class Collection {
    @Id
    @SequenceGenerator(
            name = "collection_sequence",
            sequenceName = "collection_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "collection_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "titre"
    )
    private String titre;

    @Column(
            name = "nb_series",
            nullable = false
    )
    private int nbSeries;

    @Column(
            name = "nb_auteurs",
            nullable = false
    )
    private int nbAuteurs;

    @Column(
            name = "nb_albums",
            nullable = false
    )
    private int nbAlbums;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "series", referencedColumnName = "id")
    private List<Serie> series = new ArrayList<>();

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "auteurs", referencedColumnName = "id")
    private List<Auteur> auteurs = new ArrayList<>();

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "albums", referencedColumnName = "id")
    private List<Album> albums = new ArrayList<>();


    /*************************************************************************/

    public Collection(String titre, int nbSeries, int nbAuteurs, int nbAlbums) {
        this.titre = titre;
        this.nbSeries = nbSeries;
        this.nbAuteurs = nbAuteurs;
        this.nbAlbums = nbAlbums;
    }

    public Collection(){}

    public int getNbAlbums() {
        return nbAlbums;
    }

    public void setNbAlbums(int nbAlbums) {
        this.nbAlbums = nbAlbums;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbSeries() {
        return nbSeries;
    }

    public void setNbSeries(int nbSeries) {
        this.nbSeries = nbSeries;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public int getNbAuteurs() {
        return nbAuteurs;
    }

    public void setNbAuteurs(int nbAuteurs) {
        this.nbAuteurs = nbAuteurs;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", nbSeries=" + nbSeries +
                ", nbAuteurs=" + nbAuteurs +
                ", series=" + series +
                ", auteurs=" + auteurs +
                '}';
    }
}
