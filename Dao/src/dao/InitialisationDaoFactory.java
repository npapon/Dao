package dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import constante.AttributsServlet;

public class InitialisationDaoFactory implements ServletContextListener {

    private DAOFactory daoFactory;

    @Override
    public void contextDestroyed( ServletContextEvent event ) {
        // rien à réaliser à la fermeture de l'application

    }

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        // LANCER A L OUVERTURE DE L APPLICATION
        // le ServletContext est un objet récupéré au chargement de
        // l'application
        ServletContext servletContext = event.getServletContext();

        // on instancie le DAOFactory
        this.daoFactory = DAOFactory.getInstance();

        // on met le daoFactory dans un attribut (daofactory) qui a en portée
        // toute l'application
        servletContext.setAttribute( AttributsServlet.DAOFACTORY, this.daoFactory );

    }

}
