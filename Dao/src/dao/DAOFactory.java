package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import constante.DonneesDeConnexion;
import constante.MessagesErreur;

public class DAOFactory {

    // AVANT
    // private String url;
    // private String nomutilisateur;
    // private String motdepasse;

    BoneCP connexionPool = null;

    // AVANT
    // DAOFactory( String url, String nomutilisateur, String motdepasse ) {
    // this.url = url;
    // this.nomutilisateur = nomutilisateur;
    // this.motdepasse = motdepasse;

    // }

    DAOFactory( BoneCP connexionPool ) {
        this.connexionPool = connexionPool;
    }

    // INSTANCIE UN DAOFactory qui contient les param de connexions (qu'on
    // utilisera dans getConnexion)
    // et charge le Driver
    // cette fois en param le BoneCP qui va contenir les param de connexion
    public static DAOFactory getInstance() throws DAOConfigurationException {

        Properties properties = new Properties();

        String url;
        String nomutilisateur;
        String motdepasse;
        String driver;
        BoneCP connexionPool = null;

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

        // AVANT
        // DAOFactory instance = new DAOFactory( url, nomutilisateur, motdepasse
        // );
        // return instance;

        /*
         * Création d'une configuration de pool de connexions via l'objet
         * BoneCPConfig et les différents setters associés.
         */

        try {
            BoneCPConfig config = new BoneCPConfig();

            // Mise en place url, nom et mot de passeBOnn

            config.setJdbcUrl( url );
            config.setUsername( nomutilisateur );
            config.setPassword( motdepasse );
            // Paramètrage du pool de connexion

            config.setMinConnectionsPerPartition( 5 );
            config.setMaxConnectionsPerPartition( 15 );
            config.setPartitionCount( 2 );

            connexionPool = new BoneCP( config );

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DAOException( MessagesErreur.ERREUR_CONFIG_POOL_CONNEXIONS );

        }

        /*
         * Enregistrement du pool créé dans une variable d'instance via un appel
         * au constructeur de DAOFactory
         */
        DAOFactory instance = new DAOFactory( connexionPool );
        return instance;
    }

    // on se connecte à la bdd avec connexionPool.getConnexion
    public Connection getConnection() throws SQLException {

        // return DriverManager.getConnection( url, nomutilisateur, motdepasse
        // );
        return connexionPool.getConnection();

    }

    public UtilisateurDao getUtilisateurDao() {
        UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl( this );
        return utilisateurDaoImpl;
    }

    public ImageDao getImageDao() {
        ImageDaoImpl imageDaoImpl = new ImageDaoImpl( this );
        return imageDaoImpl;
    }

    public SessionDao getSessionDao() {
        SessionDaoImpl sessionDaoImpl = new SessionDaoImpl( this );
        return sessionDaoImpl;
    }

    public MenuDao getMenuDao() {
        MenuDaoImpl menuDaoImpl = new MenuDaoImpl( this );
        return menuDaoImpl;
    }

    public AchatDao getAchatDao() {
        AchatDaoImpl achatDaoImpl = new AchatDaoImpl( this );
        return achatDaoImpl;
    }

}
