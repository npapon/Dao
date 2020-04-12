package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

    private final static String SQL_SELECT_PAR_MAIL = "select * from utilisateur where utilisateur.email = ?";
    private DAOFactory          daoFactory;

    public UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Utilisateur rechercherUtilisateur( String email ) throws DAOException {

        Utilisateur utilisateur = new Utilisateur();

        ////////////////////////

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();

            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connection, SQL_SELECT_PAR_MAIL, false,
                    email );

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {

                utilisateur = map( resultSet );

                System.out.println( resultSet.getString( "email" ) );

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

    }

    public static Utilisateur map( ResultSet resultset ) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId( resultset.getInt( "id" ) );
        utilisateur.setLogin( resultset.getString( "login" ) );
        utilisateur.setEmail( resultset.getString( "email" ) );
        utilisateur.setMot_de_passe( resultset.getString( "mot_de_passe" ) );
        utilisateur.setNom( resultset.getString( "nom" ) );
        utilisateur.setDate_creation( resultset.getTimestamp( "date_creation" ) );

        return utilisateur;
    }

}
