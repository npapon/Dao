package formulaire;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.Session;
import constante.MessagesErreur;
import constante.MessagesSucces;
import constante.ParametresFormulaire;
import dao.DAOException;
import dao.SessionDao;
import exception.FormValidationException;

public class ConnexionForm {

    private SessionDao   sessionDao;
    private List<String> erreurs = new ArrayList<String>();
    private String       connexionAutorisee;

    public ConnexionForm( SessionDao sessionDao ) {
        this.sessionDao = sessionDao;
    }

    public Session connecterUtilisateur( HttpServletRequest request ) {
        String login = request.getParameter( ParametresFormulaire.LOGIN );
        String motdepasse = request.getParameter( ParametresFormulaire.MOTDEPASSE );

        Session sessionActive = new Session();
        try {

            affecterLogin( login );
            verifierMotDePasse( login, motdepasse, sessionActive );
            recupererEmail( login, motdepasse, sessionActive );

            if ( erreurs.isEmpty() ) {
                connexionAutorisee = MessagesSucces.SESSIONACTIVE_CREE;
                // utilisateurDao.creerUtilisateur( utilisateur );
                System.out.println( connexionAutorisee );
            } else {
                connexionAutorisee = MessagesErreur.CREATION_SESSIONACTIVE_KO;
                sessionActive = null;
                System.out.println( connexionAutorisee );

            }

        } catch ( DAOException e ) {
            connexionAutorisee = MessagesErreur.CREATION_SESSIONACTIVE_KO_RAISON_BDD;
            System.out.println( connexionAutorisee );
            e.printStackTrace();

        }

        return sessionActive;
    }

    public void affecterLogin( String login ) {
        if ( login.length() <= 3 ) {
            try {
                throw new FormValidationException( MessagesErreur.LOGIN_TROP_COURT );
            } catch ( FormValidationException e ) {
                System.out.println( e.getMessage() );

                erreurs.add(
                        e.getMessage() );
            }
        }
    }

    public void verifierMotDePasse( String login, String motdepasse, Session session ) {
        if ( sessionDao.rechercherSession( login, motdepasse ) == null ) {
            try {
                throw new FormValidationException( MessagesErreur.MOTDEPASSE_INCORRECT );
            } catch ( FormValidationException e ) {
                System.out.println( e.getMessage() );

                erreurs.add(
                        e.getMessage() );
            }
        } else {
            session.setLogin( login );
            session.setMot_de_passe( motdepasse );
        }

    }

    public void recupererEmail( String login, String motdepasse, Session session ) {
        if ( sessionDao.rechercherSession( login, motdepasse ) == null ) {
            try {
                throw new FormValidationException( MessagesErreur.MOTDEPASSE_INCORRECT );
            } catch ( FormValidationException e ) {
                System.out.println( e.getMessage() );

                erreurs.add(
                        e.getMessage() );
            }
        } else {
            String email = sessionDao.rechercherSession( login, motdepasse ).getEmail();
            session.setEmail( email );
        }

    }

    public String getConnexionAutorisee() {
        return connexionAutorisee;
    }

}
