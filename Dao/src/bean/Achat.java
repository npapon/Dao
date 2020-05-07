package bean;

import java.sql.Timestamp;

public class Achat {

    private int       id;
    private int       numeroAffaire;
    private int       numeroAchat;
    private char      verrouilleV2;
    private char      statutAchat;
    private String    utilisateur;
    private Timestamp date_creation;
    private Timestamp date_modification;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public int getNumeroAffaire() {
        return numeroAffaire;
    }

    public void setNumeroAffaire( int numeroAffaire ) {
        this.numeroAffaire = numeroAffaire;
    }

    public int getNumeroAchat() {
        return numeroAchat;
    }

    public void setNumeroAchat( int numeroAchat ) {
        this.numeroAchat = numeroAchat;
    }

    public char getVerrouilleV2() {
        return verrouilleV2;
    }

    public void setVerrouilleV2( char verrouilleV2 ) {
        this.verrouilleV2 = verrouilleV2;
    }

    public char getStatutAchat() {
        return statutAchat;
    }

    public void setStatutAchat( char statutAchat ) {
        this.statutAchat = statutAchat;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur( String utilisateur ) {
        this.utilisateur = utilisateur;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation( Timestamp date_creation ) {
        this.date_creation = date_creation;
    }

    public Timestamp getDate_modification() {
        return date_modification;
    }

    public void setDate_modification( Timestamp date_modification ) {
        this.date_modification = date_modification;
    }

}
