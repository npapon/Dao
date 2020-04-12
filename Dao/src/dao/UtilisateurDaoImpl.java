package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

        int id = 0;
        String login = null;
        String e_mail = null;
        String mot_de_passe = null;
        String nom = null;
        Timestamp date_creation;

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
                id = resultSet.getInt( "id" );
                System.out.println( resultSet.getString( "login" ) );
                login = resultSet.getString( "login" );
                System.out.println( resultSet.getString( "email" ) );
                e_mail = resultSet.getString( "email" );
                System.out.println( resultSet.getString( "mot_de_passe" ) );
                mot_de_passe = resultSet.getString( "mot_de_passe" );
                System.out.println( resultSet.getString( "nom" ) );
                nom = resultSet.getString( "nom" );
                System.out.println( resultSet.getTimestamp( "date_creation" ) );
                date_creation = resultSet.getTimestamp( "date_creation" );

            }
            utilisateur.setId( id );
            utilisateur.setLogin( login );
            utilisateur.setMot_de_passe( mot_de_passe );
            utilisateur.setNom( nom );
            utilisateur.setEmail( e_mail );

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

}
