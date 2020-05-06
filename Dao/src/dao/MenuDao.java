package dao;

import java.util.List;

import bean.Menu;

public interface MenuDao {

    List<Menu> rechercherMenu() throws DAOException;

}
