import bean.Utilisateur;
import dao.DAOFactory;
import dao.UtilisateurDao;

public class Main {

    public static void main( String[] args ) {
        // on a un daoFactory qui a l'url,login mot de passe en param
        // il a chargé le driver
        DAOFactory daoFactory = DAOFactory.getInstance();
        Utilisateur utilisateur = new Utilisateur();

        // on instancie utilisateurDaoImpl avec le daoFactory en paramètre qui a
        // les méthodes CRUD
        UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();
        // cette méthode initie la connexion avec les paramètres du dao et
        // execute une requete type select et retourne un utilisateur
        utilisateur = utilisateurDao.rechercherUtilisateur( "npapon@live.fr" );

        System.out.println( "mail connard " + utilisateur.getEmail() );

    }
}
