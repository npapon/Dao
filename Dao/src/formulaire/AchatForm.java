package formulaire;

import java.util.List;

import bean.Achat;
import dao.AchatDao;

public class AchatForm {

    private AchatDao achatDao;

    public AchatForm( AchatDao achatDao ) {
        this.achatDao = achatDao;
    }

    public List<Achat> libererAchatV2( String utilisateur ) {

        List<Achat> achatsBloquesV2 = achatDao.rechercherAchatsBloquesV2();

        for ( Achat achat : achatsBloquesV2 ) {
            achatDao.modifierAchatBloqueV2( achat, utilisateur );
        }

        return achatsBloquesV2;

    }

}
