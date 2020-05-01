package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Image;
import constante.ImageProfilTableNomsColonnes;
import constante.MessagesErreur;
import constante.RequetesSql;

public class ImageDaoImpl implements ImageDao {

    private DAOFactory daoFactory;

    public ImageDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Image rechercherImage( String email ) throws DAOException {
        Image imageProfil = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            System.out.println( "email pas ok " + email );
            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connexion, RequetesSql.IMAGEPROFIL_SELECT_PAR_MAIL,
                    false,
                    email );

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {
                imageProfil = map( resultSet );
            }
        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return imageProfil;
    }

    private Image map( ResultSet resultSet ) throws SQLException {
        Image image = new Image();
        image.setId( resultSet.getInt( ImageProfilTableNomsColonnes.ID ) );
        image.setEmail( resultSet.getString( ImageProfilTableNomsColonnes.EMAIL ) );
        image.setLibelle( resultSet.getString( ImageProfilTableNomsColonnes.LIBELLE ) );
        image.setDate_creation( resultSet.getTimestamp( ImageProfilTableNomsColonnes.DATE_CREATION ) );
        image.setDate_modification( resultSet.getTimestamp( ImageProfilTableNomsColonnes.DATE_MODIFICATION ) );
        image.setEmplacement( resultSet.getString( ImageProfilTableNomsColonnes.EMPLACEMENT ) );
        return image;
    }

    @Override
    public void creerImage( Image image ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSetPourStockerIdAutoGeneres = null;
        Object[] imageAttributs = { image.getLibelle(), image.getEmail(), image.getEmplacement() };

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connexion, RequetesSql.IMAGEPROFIL_INSERT, true,
                    imageAttributs );

            int nombreDeLignesInserees = preparedStatement.executeUpdate();

            if ( nombreDeLignesInserees == 0 ) {
                throw new DAOException( MessagesErreur.ECHEC_INSERT_IMAGEPROFIL );
            }
            resultSetPourStockerIdAutoGeneres = preparedStatement.getGeneratedKeys();
            if ( resultSetPourStockerIdAutoGeneres.next() ) {
                image.setId( resultSetPourStockerIdAutoGeneres.getInt( 1 ) );
            }

            else {
                throw new DAOException(
                        MessagesErreur.ECHEC_RECUPERATION_ID_IMAGEPROFIL );
            }
        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally

        {
            DAOUtilitaire.fermeturesSilencieuses( resultSetPourStockerIdAutoGeneres, preparedStatement, connexion );
        }
    }

    @Override
    public void modifierImage( Image image ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSetPourStockerIdAutoGeneres = null;
        Object[] imageAttributs = { image.getLibelle(), image.getEmplacement(), image.getEmail() };

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connexion, RequetesSql.IMAGEPROFIL_UPDATE, false,
                    imageAttributs );

            int nombreDeLignesUpdate = preparedStatement.executeUpdate();

            if ( nombreDeLignesUpdate == 0 ) {
                throw new DAOException( MessagesErreur.ECHEC_UPDATE_IMAGEPROFIL );
            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally

        {
            DAOUtilitaire.fermeturesSilencieuses( resultSetPourStockerIdAutoGeneres, preparedStatement, connexion );
        }

    }

}
