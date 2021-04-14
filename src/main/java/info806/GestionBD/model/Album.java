package info806.GestionBD.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Album")
@Table(
        name = "Album",
        uniqueConstraints = {
                @UniqueConstraint(name ="album_titre_unique", columnNames = "titre")
        }
)
public class Album {

    @Id
    @SequenceGenerator(
            name = "album_sequence",
            sequenceName = "album_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "album_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "type",
            nullable = false
    )
    private String type;

    @Column(
            name = "isbn",
            nullable = false
    )
    private String isbn;

    @Column(
            name = "titre",
            nullable = false
    )
    private String titre;

    @Column(
            name = "image"
    )
    private String image;

    @Column(
            name = "format",
            nullable = false
    )
    private String format;

    @Column(
            name = "ordre",
            nullable = false
    )
    private int ordre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auteurs", referencedColumnName = "id")
    private List<Auteur> auteurs = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie", referencedColumnName = "id")
    private Serie serie;

    public Album(String type, String isbn, String titre, String image, String format, int ordre) {
        this.type = type;
        this.isbn = isbn;
        this.titre = titre;
        this.image = image;
        this.format = format;
        this.ordre = ordre;
    }


    public Album(){}

    public void setType(String type) {
        this.type = type;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public String getType() {
        return type;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public String getImage() {
        return image;
    }

    public String getFormat() {
        return format;
    }

    public int getOrdre() {
        return ordre;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", type=" + type +
                ", isbn=" + isbn +
                ", titre='" + titre + '\'' +
                ", image='" + image + '\'' +
                ", format=" + format +
                ", ordre=" + ordre +
                ", auteurs=" + toStringAuteur(auteurs) +
                ", serie=" + serie +
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
}


