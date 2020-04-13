package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

    private final static String SQL_SELECT_PAR_MAIL    = "select * from utilisateur where utilisateur.email = ?";
    private final static String SQL_INSERT_UTILISATEUR = "insert into utilisateur (login, email, mot_de_passe, nom, date_creation) values (?,?,?,?,now())";
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

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSetPourStockerIdAutoGeneres = null;
        Object[] utilisateurAttributs = { utilisateur.getLogin(), utilisateur.getEmail(), utilisateur.getMot_de_passe(),
                utilisateur.getNom() };

        try {

            connection = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connection, SQL_INSERT_UTILISATEUR, true,
                    utilisateurAttributs );

            int nombreDeLigneInserees = preparedStatement.executeUpdate();
            if ( nombreDeLigneInserees == 0 ) {
                throw new DAOException( "échec de l'insert" );
            }
            resultSetPourStockerIdAutoGeneres = preparedStatement.getGeneratedKeys();

            if ( resultSetPourStockerIdAutoGeneres.next() ) {
                utilisateur.setId( resultSetPourStockerIdAutoGeneres.getInt( 1 ) );
            }

            else {
                throw new DAOException( "aucun id unique trouvé car par de lignes insérées" );
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
        utilisateur.setId( resultset.getInt( "id" ) );
        utilisateur.setLogin( resultset.getString( "login" ) );
        utilisateur.setEmail( resultset.getString( "email" ) );
        utilisateur.setMot_de_passe( resultset.getString( "mot_de_passe" ) );
        utilisateur.setNom( resultset.getString( "nom" ) );
        utilisateur.setDate_creation( resultset.getTimestamp( "date_creation" ) );

        return utilisateur;
    }

}
