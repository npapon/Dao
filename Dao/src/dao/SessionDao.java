package dao;

import bean.Session;

public interface SessionDao {

    Session rechercherSession( String login, String motDePasse ) throws DAOException;

}
