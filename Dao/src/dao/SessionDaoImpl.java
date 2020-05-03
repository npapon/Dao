package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import bean.Session;
import constante.Cryptage;
import constante.RequetesSql;
import constante.UtilisateurTableNomsColonnes;

public class SessionDaoImpl implements SessionDao {
    private DAOFactory daoFactory;

    public SessionDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;

    }

    @Override
    public Session rechercherSession( String login, String motDePasse ) throws DAOException {
        Session session = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();

            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connection, RequetesSql.SESSION_SELECT_PAR_LOGIN,
                    false,
                    login );

            resultSet = preparedStatement.executeQuery();
            int nombreDeLignesRequete = calculNombreLignesResultats( resultSet );

            if ( nombreDeLignesRequete == 1 ) {
                while ( resultSet.next() ) {

                    session = map( resultSet );

                }
            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }
        // TODO Auto-generated method stub
        // on vérifie que le mot de passe crypté en base et celui tapé danbs le
        // champ mot de passe :
        ConfigurablePasswordEncryptor configurablePasswordEncryptor = new ConfigurablePasswordEncryptor();
        configurablePasswordEncryptor.setAlgorithm( Cryptage.ALGO_CHIFFREMENT_SHA_256 );
        configurablePasswordEncryptor.setPlainDigest( false );
        if ( session != null && configurablePasswordEncryptor.checkPassword( motDePasse, session.getMot_de_passe() ) ) {
            System.out.println( "session connectée" );
            return session;
        } else {
            System.out.println( "echec connexion session" );
            return null;
        }
    }

    public static Session map( ResultSet resultset ) throws SQLException {
        Session session = new Session();

        session.setLogin( resultset.getString( UtilisateurTableNomsColonnes.LOGIN ) );
        session.setEmail( resultset.getString( UtilisateurTableNomsColonnes.EMAIL ) );
        session.setMot_de_passe( resultset.getString( UtilisateurTableNomsColonnes.MOT_DE_PASSE ) );
        return session;
    }

    public int calculNombreLignesResultats( ResultSet resultset ) throws SQLException {
        // on place le curseur à la dernière ligne du select
        resultset.last();
        // on récupère le numéro de la ligne
        int nombreDeLignes = resultset.getRow();
        // on replace le curseur au départ avant la première ligne
        resultset.beforeFirst();

        System.out.println( "nombre de lignes " + nombreDeLignes );
        return nombreDeLignes;

    }

}
