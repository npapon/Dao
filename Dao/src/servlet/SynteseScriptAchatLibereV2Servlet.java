package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constante.Adressesinternes;

/**
 * Servlet implementation class SynteseScriptAchatLibereV2Servlet
 */
@WebServlet( "/syntesescriptachatliberev2" )
public class SynteseScriptAchatLibereV2Servlet extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( Adressesinternes.SYNTHESE_SCRIPT_LIBERERACHATV2 ).forward( request,
                response );
    }

}
