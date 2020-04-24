package constante;

public class RequetesSql {

    public final static String UTILISATEUR_SELECT_PAR_MAIL = "SELECT * FROM Utilisateur WHERE email = ?";
    public final static String IMAGEPROFIL_SELECT_PAR_MAIL = "SELECT * FROM ImageProfil WHERE email = ?";
    public final static String UTILISATEUR_INSERT          = "insert into utilisateur (login, email, mot_de_passe, nom, date_creation) values (?,?,?,?,now())";
    public final static String IMAGEPROFIL_INSERT          = "insert into ImageProfil(libelle,email,date_creation) values(?,?,now())";

}
