package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Image;
import constante.Adressesinternes;
import constante.Dossiers;
import formulaire.ProfilForm;

@WebServlet( "/ProfilServlet" )
public class ProfilServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( Adressesinternes.PROFIL ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        ProfilForm profilForm = new ProfilForm();

        try {
            Image image = profilForm.enregistrerImageProfil( Dossiers.REPERTOIRE_ABSOLU_IMAGESPROFIL, request );
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.sendRedirect( Adressesinternes.PROFIL_COURT );
    }

}
