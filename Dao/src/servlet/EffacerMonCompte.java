package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constante.Adressesinternes;
import constante.AttributsServlet;
import dao.DAOFactory;
import dao.ImageDao;
import dao.SessionDao;

@WebServlet( "/EffacerMonCompte" )
public class EffacerMonCompte extends HttpServlet {

    private SessionDao sessionDao;
    private ImageDao   imageDao;

    public void init() throws ServletException {

        sessionDao = ( (DAOFactory) this.getServletContext().getAttribute( AttributsServlet.DAOFACTORY ) )
                .getSessionDao();
        imageDao = ( (DAOFactory) this.getServletContext().getAttribute( AttributsServlet.DAOFACTORY ) )
                .getImageDao();

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect( Adressesinternes.CONNEXION_COURT );
    }

}
