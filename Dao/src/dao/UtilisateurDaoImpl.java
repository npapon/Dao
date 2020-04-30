package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Utilisateur;
import constante.MessagesErreur;
import constante.RequetesSql;
import constante.UtilisateurTableNomsColonnes;

public class UtilisateurDaoImpl implements UtilisateurDao {

    private DAOFactory daoFactory;

    public UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Utilisateur rechercherUtilisateur( String email ) throws DAOException {

        Utilisateur utilisateur = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();

            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connection, RequetesSql.UTILISATEUR_SELECT_PAR_MAIL,
                    false,
                    email );

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {

                utilisateur = map( resultSet );

            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }
        // TODO Auto-generated method stub
        return utilisateur;
    }

    @Override
    public void creerUtilisateur( Utilisateur utilisateur ) throws DAOException {
        // TODO Auto-generated method stub

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSetPourStockerIdAutoGeneres = null;
        Object[] utilisateurAttributs = { utilisateur.getLogin(), utilisateur.getEmail(), utilisateur.getMot_de_passe(),
                utilisateur.getNom() };

        try {

            connection = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connection, RequetesSql.UTILISATEUR_INSERT,
                    true,
                    utilisateurAttributs );

            int nombreDeLigneInserees = preparedStatement.executeUpdate();
            if ( nombreDeLigneInserees == 0 ) {
                throw new DAOException( MessagesErreur.ECHEC_INSERT_UTILISATEUR );
            }
            resultSetPourStockerIdAutoGeneres = preparedStatement.getGeneratedKeys();

            if ( resultSetPourStockerIdAutoGeneres.next() ) {
                utilisateur.setId( resultSetPourStockerIdAutoGeneres.getInt( 1 ) );
            }

            else {
                throw new DAOException(
                        MessagesErreur.ECHEC_RECUPERATION_ID_UTILISATEUR );
            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            fermeturesSilencieuses( resultSetPourStockerIdAutoGeneres, preparedStatement, connection );

        }

    }

    public static Utilisateur map( ResultSet resultset ) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId( resultset.getInt( UtilisateurTableNomsColonnes.ID ) );
        utilisateur.setLogin( resultset.getString( UtilisateurTableNomsColonnes.LOGIN ) );
        utilisateur.setEmail( resultset.getString( UtilisateurTableNomsColonnes.EMAIL ) );
        utilisateur.setMot_de_passe( resultset.getString( UtilisateurTableNomsColonnes.MOT_DE_PASSE ) );
        utilisateur.setNom( resultset.getString( UtilisateurTableNomsColonnes.NOM ) );
        utilisateur.setDate_creation( resultset.getTimestamp( UtilisateurTableNomsColonnes.DATE_CREATION ) );

        return utilisateur;
    }

}
