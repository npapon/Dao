import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main2 {

    public static void main( String[] args ) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String login = "npapon";
        String mdp = "Patapoun123!";
        String bddurl = "jdbc:mysql://localhost:3306/bdd_mediahub";

        // Chargement driver

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection( bddurl, login, mdp );

            preparedStatement = connection.prepareStatement( "select * from utilisateur" );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {

                System.out.print( resultSet.getString( "login" ) );
            }

            System.out.println( "" );
            preparedStatement = connection.prepareStatement( "update utilisateur set date_creation=now()" );
            int statut = preparedStatement.executeUpdate();
            System.out.println( statut );

            preparedStatement = connection
                    .prepareStatement(
                            "insert into utilisateur  (login,email,mot_de_passe,nom,date_creation) values(?,?,?,?,now())",
                            preparedStatement.RETURN_GENERATED_KEYS );

            preparedStatement.setString( 1, "lchenu2" );
            preparedStatement.setString( 2, "lchenu2@gay.com" );
            preparedStatement.setString( 3, "gay" );
            preparedStatement.setString( 4, "chenu" );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            while ( resultSet.next() ) {
                System.out.println( resultSet.getInt( 1 ) );
            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if ( preparedStatement != null ) {
            try {
                preparedStatement.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
