package bean;

import java.sql.Timestamp;

public class Image {

    private int       id;
    private String    libelle;
    private String    email;
    private Timestamp date_creation;
    private String    emplacement;
    private Timestamp date_modification;

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

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement( String emplacement ) {
        this.emplacement = emplacement;
    }

    public Timestamp getDate_modification() {
        return date_modification;
    }

    public void setDate_modification( Timestamp date_modification ) {
        this.date_modification = date_modification;
    }

}
