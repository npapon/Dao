package dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import constante.AttributsServlet;

@WebListener
public class InitialisationDaoFactory implements ServletContextListener {

    private DAOFactory daoFactory;

    @Override
    public void contextDestroyed( ServletContextEvent event ) {
        // rien � r�aliser � la fermeture de l'application

    }

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        // LANCER A L OUVERTURE DE L APPLICATION
        // le ServletContext est un objet r�cup�r� au chargement de
        // l'application
        ServletContext servletContext = event.getServletContext();

        // on instancie le DAOFactory
        this.daoFactory = DAOFactory.getInstance();

        // on met le daoFactory dans un attribut (daofactory) qui a en port�e
        // toute l'application
        servletContext.setAttribute( AttributsServlet.DAOFACTORY, this.daoFactory );

    }

}
