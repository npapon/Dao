package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constante.Adressesinternes;

@WebServlet( "/deconnexion" )
public class DeconnexionServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect( Adressesinternes.CONNEXION_COURT );

    }

}
