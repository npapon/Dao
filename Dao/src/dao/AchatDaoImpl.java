package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Achat;
import constante.AchatTableNomsColonnes;
import constante.MessagesErreur;
import constante.RequetesSql;

public class AchatDaoImpl implements AchatDao {

    private DAOFactory daoFactory;

    public AchatDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Achat> rechercherAchatsBloquesV2() throws DAOException {
        Achat achat = null;
        List<Achat> achatsBloquesV2 = new ArrayList<Achat>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();

            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connection, RequetesSql.ACHATBLOQUESV2_SELECT,
                    false );

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {

                achat = map( resultSet );
                achatsBloquesV2.add( achat );

            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }
        return achatsBloquesV2;
    }

    @Override
    public void modifierAchatBloqueV2( Achat achat, String utilisateur ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        Object[] modifierAchatsAttributs = { utilisateur, achat.getId() };
        try

        {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connexion, RequetesSql.ACHATBLOQUEV2_UPDATE, false,
                    modifierAchatsAttributs );

            int nombreDeLignesUpdate = preparedStatement.executeUpdate();

            if ( nombreDeLignesUpdate == 0 ) {
                throw new DAOException( MessagesErreur.ECHEC_UPDATE_ACHATSBLOQUESV2 );
            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally

        {
            DAOUtilitaire.fermeturesSilencieuses( preparedStatement, connexion );
        }

    }

    public static Achat map( ResultSet resultset ) throws SQLException {
        Achat achat = new Achat();

        achat.setId( resultset.getInt( AchatTableNomsColonnes.ID ) );
        achat.setNumeroAffaire( resultset.getInt( AchatTableNomsColonnes.NUMERO_AFFAIRE ) );
        achat.setNumeroAchat( resultset.getInt( AchatTableNomsColonnes.NUMERO_ACHAT ) );
        achat.setVerrouilleV2( resultset.getString( AchatTableNomsColonnes.VERROUILLE_DANS_V2 ).charAt( 0 ) );
        achat.setStatutAchat( resultset.getString( AchatTableNomsColonnes.STATUT_ACHAT ).charAt( 0 ) );
        achat.setUtilisateur( resultset.getString( AchatTableNomsColonnes.UTILISATEUR ) );
        achat.setDate_creation( resultset.getTimestamp( AchatTableNomsColonnes.DATE_CREATION ) );
        achat.setDate_modification( resultset.getTimestamp( AchatTableNomsColonnes.DATE_MODIFICATION ) );
        return achat;
    }

}
