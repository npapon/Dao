package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtilitaire {

    // on met pas Resulset car pas utilisé pour les méthodes de type update
    public static PreparedStatement initialisaterRequetePreparee( Connection connection, String sql,
            boolean returnGeneratedKeys,
            Object... objects )

    {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement( sql,
                    returnGeneratedKeys ? preparedStatement.RETURN_GENERATED_KEYS : preparedStatement.NO_GENERATED_KEYS );

            for ( int i = 0; i < objects.length; i++ ) {
                preparedStatement.setObject( i + 1, objects[i] );

            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return preparedStatement;
    }

    public static void fermetureSilencieuse( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void fermetureSilencieuse( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void fermetureSilencieuse( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connection ) {
        fermetureSilencieuse( resultSet );
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connection );

    }

    public static void fermeturesSilencieuses( Statement statement, Connection connection ) {

        fermetureSilencieuse( statement );
        fermetureSilencieuse( connection );

    }

}
