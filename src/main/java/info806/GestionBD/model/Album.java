package info806.GestionBD.model;

import info806.GestionBD.dao.Format;
import info806.GestionBD.dao.Genre;

import javax.persistence.*;

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
    private Genre type;

    @Column(
            name = "isbn",
            nullable = false
    )
    private int isbn;

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
    private Format format;

    @Column(
            name = "ordre",
            nullable = false
    )
    private int ordre;

    public Album(Genre type, int isbn, String titre, String image, Format format, int ordre) {
        this.type = type;
        this.isbn = isbn;
        this.titre = titre;
        this.image = image;
        this.format = format;
        this.ordre = ordre;
    }

    public Album(){}

    public void setType(Genre type) {
        this.type = type;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Genre getType() {
        return type;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public String getImage() {
        return image;
    }

    public Format getFormat() {
        return format;
    }

    public int getOrdre() {
        return ordre;
    }

    @Override
    public String toString() {
        return "Album{" +
                "type=" + type +
                ", isbn=" + isbn +
                ", titre='" + titre + '\'' +
                ", image='" + image + '\'' +
                ", format=" + format +
                ", ordre=" + ordre +
                '}';
    }
}


