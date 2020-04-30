package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Cookie.CookieGenerateur;
import bean.Session;
import constante.Adressesinternes;
import constante.AttributsServlet;
import constante.Cookies;
import dao.DAOFactory;
import dao.SessionDao;
import formulaire.ConnexionForm;

@WebServlet( "/ConnexionServlet" )
public class ConnexionServlet extends HttpServlet {
    private SessionDao sessionDao;

    public void init() throws ServletException {

        sessionDao = ( (DAOFactory) this.getServletContext().getAttribute( AttributsServlet.DAOFACTORY ) )
                .getSessionDao();

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( Adressesinternes.CONNEXION ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        ConnexionForm connexionForm = new ConnexionForm( sessionDao );

        Session sessionactive = connexionForm.connecterUtilisateur( request );

        if ( sessionactive != null ) {

            CookieGenerateur cookieGenerateur = new CookieGenerateur();
            Cookie cookieLogin = cookieGenerateur.creerCookie( response, Cookies.COOKIE_LOGIN,
                    sessionactive.getLogin(), Cookies.COOKIE_MAX_AGE_1_AN );

            Cookie cookieMotDePasse = cookieGenerateur.creerCookie( response, Cookies.COOKIE_MOTDEPASSE,
                    sessionactive.getMot_de_passe(), Cookies.COOKIE_MAX_AGE_1_AN );

            HttpSession session = request.getSession();
            session.setAttribute( AttributsServlet.CONNEXIONFORM, connexionForm );
            session.setAttribute( AttributsServlet.COOKIE_LOGIN, cookieLogin );
            session.setAttribute( AttributsServlet.COOKIE_MOTDEPASSE, cookieMotDePasse );
            session.setAttribute( AttributsServlet.SESSIONACTIVE, sessionactive );

            response.sendRedirect( Adressesinternes.PROFIL_COURT );
        } else {
            HttpSession session = request.getSession();
            session.setAttribute( AttributsServlet.CONNEXIONFORM, connexionForm );
            response.sendRedirect( Adressesinternes.CONNEXION_COURT );
        }

    }

}
