package dao;

import bean.Image;

public interface ImageDao {

    Image rechercherImage( String email ) throws DAOException;

    void creerImage( Image image ) throws DAOException;

    void modifierImage( Image image ) throws DAOException;

    void supprimerImage( Image image ) throws DAOException;
}
