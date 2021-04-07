package info806.GestionBD.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Suivis")
@Table(
        name = "Suivis",
        uniqueConstraints = {
                @UniqueConstraint(name ="suivis_titre_unique", columnNames = "titre")
        }
)
public class Suivis {
    @Id
    @SequenceGenerator(
            name = "suivis_sequence",
            sequenceName = "suivis_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "suivis_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "titre",
            nullable = false
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "series", referencedColumnName = "id")
    private List<Serie> series = new ArrayList<>();

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "auteurs", referencedColumnName = "id")
    private List<Auteur> auteurs = new ArrayList<>();


    /*************************************************************************/

    public Suivis(String titre, int nbSeries, int nbAuteurs) {
        this.titre = titre;
        this.nbSeries = nbSeries;
        this.nbAuteurs = nbAuteurs;
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
        return "Suivis{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", nbSeries=" + nbSeries +
                ", nbAuteurs=" + nbAuteurs +
                ", series=" + series +
                ", auteurs=" + auteurs +
                '}';
    }
}
