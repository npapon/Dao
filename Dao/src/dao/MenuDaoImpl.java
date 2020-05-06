package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Menu;
import constante.MenuTableNomsColonnes;
import constante.RequetesSql;

public class MenuDaoImpl implements MenuDao {

    private DAOFactory daoFactory;

    public MenuDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Menu> rechercherMenu() throws DAOException {
        Menu menuItem = null;
        List<Menu> menuComplet = new ArrayList<Menu>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();

            preparedStatement = DAOUtilitaire.initialisaterRequetePreparee( connection, RequetesSql.MENU_SELECT,
                    false );

            resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ) {

                menuItem = map( resultSet );
                menuComplet.add( menuItem );

            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }
        return menuComplet;
    }

    public static Menu map( ResultSet resultset ) throws SQLException {
        Menu menuItem = new Menu();

        menuItem.setItem( resultset.getString( MenuTableNomsColonnes.ITEM ) );
        menuItem.setDescription( resultset.getString( MenuTableNomsColonnes.DESCRIPTION ) );
        menuItem.setOrdre( resultset.getInt( MenuTableNomsColonnes.ORDRE ) );
        menuItem.setActif( resultset.getString( MenuTableNomsColonnes.ACTIF ) );
        return menuItem;
    }

}
