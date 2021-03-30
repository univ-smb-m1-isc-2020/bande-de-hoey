package info806.GestionBD.model;

import info806.GestionBD.dao.Format;
import info806.GestionBD.dao.Genre;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Album")
public class Album {

    @Id
    private long id;
    private Genre type;
    private int isbn;
    private String titre;
    private String image;
    private Format format;
    private int ordre;

    public Album(Genre type, int isbn, String titre, String image, Format format, int ordre) {
        this.type = type;
        this.isbn = isbn;
        this.titre = titre;
        this.image = image;
        this.format = format;
        this.ordre = ordre;
    }

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

