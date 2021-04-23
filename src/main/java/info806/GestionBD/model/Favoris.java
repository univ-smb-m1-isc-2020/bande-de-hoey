package info806.GestionBD.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Favoris")
@Table(
        name = "Favoris"
        /*uniqueConstraints = {
                @UniqueConstraint(name ="favoris_titre_unique", columnNames = "titreFavoris")
        }*/
)
public class Favoris {
    @Id
    @SequenceGenerator(
            name = "favoris_sequence",
            sequenceName = "favoirs_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "favoris_sequence"
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
    private List<Auteur> albums = new ArrayList<>();

    /*************************************************************************/

    public Favoris(String titre, int nbSeriesn, int nbAuteurs, int nbAlbums) {
        this.titre = titre;
        this.nbSeries = nbSeries;
    }

    public Favoris(){}

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

    public int getNbAlbums() {
        return nbAlbums;
    }

    public void setNbAlbums(int nbAlbums) {
        this.nbAlbums = nbAlbums;
    }

    public List<Auteur> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Auteur> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Favoris{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", nbSeries=" + nbSeries +
                ", nbAuteurs=" + nbAuteurs +
                ", nbAlbums=" + nbAlbums +
                ", series=" + series +
                ", auteurs=" + auteurs +
                ", albums=" + albums +
                '}';
    }
}
