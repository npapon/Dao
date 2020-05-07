package dao;

import java.util.List;

import bean.Achat;

public interface AchatDao {

    List<Achat> rechercherAchatsBloquesV2() throws DAOException;

    void modifierAchatsBloquesV2( List<Achat> achatsBloquesV2, String utilisateur ) throws DAOException;

}
