package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Achat;
import bean.Session;
import constante.Adressesinternes;
import constante.AttributsServlet;
import dao.AchatDao;
import dao.DAOFactory;
import formulaire.AchatForm;

@WebServlet( "/LibererAchatV2Servlet" )
public class LibererAchatV2Servlet extends HttpServlet {

    private AchatDao achatDao;

    public void init() throws ServletException {

        achatDao = ( (DAOFactory) this.getServletContext().getAttribute( AttributsServlet.DAOFACTORY ) )
                .getAchatDao();

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( Adressesinternes.LIBERERACHATV2 ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        AchatForm achatForm = new AchatForm( achatDao );
        HttpSession session = request.getSession();
        Session sessionActive = (Session) session.getAttribute( AttributsServlet.SESSIONACTIVE );
        List<Achat> achatsBloquesV2 = achatDao.rechercherAchatsBloquesV2();
        achatDao.modifierAchatsBloquesV2( achatsBloquesV2, sessionActive.getLogin() );

    }

}
