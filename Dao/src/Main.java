import bean.Utilisateur;
import dao.DAOFactory;
import dao.UtilisateurDao;

public class Main {

    public static void main( String[] args ) {
        // on a un daoFactory qui a l'url,login mot de passe en param
        // il a chargé le driver
        DAOFactory daoFactory = DAOFactory.getInstance();
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setEmail( "nigos111@live.fr" );
        utilisateur.setLogin( "npapoune" );
        utilisateur.setMot_de_passe( "caca" );
        utilisateur.setNom( "papounet" );

        // on instancie utilisateurDaoImpl avec le daoFactory en paramètre qui a
        // les méthodes CRUD
        UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();
        // cette méthode initie la connexion avec les paramètres du dao et
        // execute une requete type select et retourne un utilisateur
        utilisateur = utilisateurDao.rechercherUtilisateur( "npapon@live.fr" );
        System.out.println( "mail recherché  " + utilisateur.getEmail() );
        utilisateur = new Utilisateur();

        utilisateur.setEmail( "help3@live.fr" );
        utilisateur.setLogin( "help3" );
        utilisateur.setMot_de_passe( "caca" );
        utilisateur.setNom( "help3" );

        utilisateurDao.creerUtilisateur( utilisateur );

        System.out.print( "utilisateur crééé " + utilisateur.toString() );

    }
}
