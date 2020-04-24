package bean;

import java.sql.Timestamp;

public class Image {

    private int       id;
    private String    libelle;
    private String    email;
    private Timestamp date_creation;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle( String libelle ) {
        this.libelle = libelle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation( Timestamp date_creation ) {
        this.date_creation = date_creation;
    }

}
