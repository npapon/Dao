package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

    private String              url;
    private String              nomutilisateur;
    private String              motdepasse;

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
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties est introuvable " + FICHIER_PROPERTIES );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            nomutilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motdepasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
            driver = properties.getProperty( PROPERTY_DRIVER );
        } catch ( IOException e ) {

            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Driver introuvable dans le classath ", e );
        }

        System.out.println( "Info de connexion"
                + "url : " + url
                + "nomutilisateur :" + nomutilisateur
                + "motdepasse :" + motdepasse
                + "driver :" + driver );
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
