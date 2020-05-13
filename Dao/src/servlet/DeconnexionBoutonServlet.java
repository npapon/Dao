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
import constante.RepertoiresIcones;

@WebServlet( "/deconnexionbouton" )
public class DeconnexionBoutonServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute( AttributsServlet.ICONE_DECONNEXION, RepertoiresIcones.DECONNEXION );
        session.setAttribute( AttributsServlet.DECONNEXION_PAGE, Adressesinternes.DECONNEXION_COURT );
        this.getServletContext().getRequestDispatcher( Adressesinternes.DECONNEXIONBOUTON ).forward( request,
                response );
    }

}
