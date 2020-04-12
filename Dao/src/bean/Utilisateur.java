package bean;
import java.sql.Timestamp;

public class Utilisateur {

    private int       id;
    private String    login;
    private String    email;
    private String    mot_de_passe;
    private String    nom;
    private Timestamp date_creation;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe( String mot_de_passe ) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation( Timestamp date_creation ) {
        this.date_creation = date_creation;
    }

}
