package dao;

import java.util.List;

import bean.Achat;

public interface AchatDao {

    List<Achat> rechercherAchatsBloquesV2() throws DAOException;

    void modifierAchatBloqueV2( Achat achat, String utilisateur ) throws DAOException;

}
