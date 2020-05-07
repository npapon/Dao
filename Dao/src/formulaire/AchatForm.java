package formulaire;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.Achat;
import dao.AchatDao;

public class AchatForm {

    private AchatDao achatDao;

    public AchatForm( AchatDao achatDao ) {
        this.achatDao = achatDao;
    }

    public List<Achat> libererAchatV2( HttpServletRequest request, String utilisateur ) {

        List<Achat> achatsBloquesV2 = achatDao.rechercherAchatsBloquesV2();

        achatDao.modifierAchatsBloquesV2( achatsBloquesV2, utilisateur );

        return achatsBloquesV2;

    }

}
