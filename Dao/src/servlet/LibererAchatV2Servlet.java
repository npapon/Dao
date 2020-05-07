package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constante.Adressesinternes;
import constante.AttributsServlet;
import dao.AchatDao;
import dao.DAOFactory;

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
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}
