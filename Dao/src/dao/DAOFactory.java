package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import constante.DonneesDeConnexion;
import constante.MessagesErreur;

public class DAOFactory {

    private String url;
    private String nomutilisateur;
    private String motdepasse;

    DAOFactory( String url, String nomutilisateur, String motdepasse ) {
        this.url = url;
        this.nomutilisateur = nomutilisateur;
        this.motdepasse = motdepasse;

    }

    public static DAOFactory getInstance() throws DAOConfigurationException {

        Properties properties = new Properties();

        String url;
        String nomutilisateur;
        String motdepasse;
        String driver;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( DonneesDeConnexion.FICHIER_PROPERTIES_EMPLACEMENT );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException(
                    "Le fichier properties est introuvable " + DonneesDeConnexion.FICHIER_PROPERTIES_EMPLACEMENT );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( DonneesDeConnexion.NOM_PROPRIETE_URL );
            nomutilisateur = properties.getProperty( DonneesDeConnexion.NOM_PROPRIETE_UTILISATEUR );
            motdepasse = properties.getProperty( DonneesDeConnexion.NOM_PROPRIETE_MOTDEPASSE );
            driver = properties.getProperty( DonneesDeConnexion.NOM_PROPRIETE_DRIVER );
        } catch ( IOException e ) {

            throw new DAOConfigurationException(
                    MessagesErreur.FICHIER_PROPRIETE_INTROUVABLE + DonneesDeConnexion.FICHIER_PROPERTIES_EMPLACEMENT, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( MessagesErreur.DRIVER_INTROUVABLE, e );
        }

        DAOFactory instance = new DAOFactory( url, nomutilisateur, motdepasse );
        return instance;
    }

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection( url, nomutilisateur, motdepasse );

    }

    public UtilisateurDao getUtilisateurDao() {
        UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl( this );
        return utilisateurDaoImpl;
    }

}
