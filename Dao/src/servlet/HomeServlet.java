package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constante.Adressesinternes;
import constante.AttributsServlet;

@WebServlet( "/home" )
public class HomeServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        ServletContext context = getServletContext();
        context.setAttribute( AttributsServlet.CONNEXION_PAGE, Adressesinternes.CONNEXION_COURT );
        context.setAttribute( AttributsServlet.INSCRIPTION_PAGE, Adressesinternes.INSCRIPTION_COURT );
        this.getServletContext().getRequestDispatcher( Adressesinternes.HOME ).forward( request,
                response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}
