import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class Main3 {

    public static void main( String[] args ) {

        String ALGO_CHIFFREMENT = "SHA-256";
        String motDePasse = "Patapoun123";
        /*
         * Utilisation de la biblioth�que Jasypt pour chiffrer le mot de passe
         * efficacement.
         * 
         * L'algorithme SHA-256 est ici utilis�, avec par d�faut un salage
         * al�atoire et un grand nombre d'it�rations de la fonction de hashage.
         * 
         * La String retourn�e est de longueur 56 et contient le hash en Base64.
         */
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );
        System.out.println( motDePasseChiffre );

        System.out.println( passwordEncryptor.checkPassword( motDePasse, motDePasseChiffre ) );

    }

}
