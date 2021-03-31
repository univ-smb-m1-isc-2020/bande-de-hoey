package info806.GestionBD.model;

import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Auteur")
@Table(
        name = "Auteur"
)
public class Auteur {

    @Id
    @SequenceGenerator(
            name = "auteur_sequence",
            sequenceName = "auteur_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "auteur_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "series", referencedColumnName = "id")
    List<Serie> series = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public Auteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}
