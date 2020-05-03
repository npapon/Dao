package dao;

import bean.Utilisateur;

public interface UtilisateurDao {

    Utilisateur rechercherUtilisateur( String email ) throws DAOException;

    void creerUtilisateur( Utilisateur utilisateur ) throws DAOException;

    void supprimerUtilisateur( Utilisateur utilisateur ) throws DAOException;

}
