import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main( String[] args ) {

        List<Integer> achatsBloquesV2 = new ArrayList<Integer>();

        achatsBloquesV2.add( 1 );
        achatsBloquesV2.add( 2 );
        achatsBloquesV2.add( 3 );
        String listeDesIdentifiantsAchats = "";

        for ( int i = 0; i < achatsBloquesV2.size(); i++ ) {

            int identifiantAchat = achatsBloquesV2.get( i );
            if ( i != achatsBloquesV2.size() - 1 ) {
                listeDesIdentifiantsAchats += identifiantAchat + ", ";
            } else {
                listeDesIdentifiantsAchats += identifiantAchat;
            }
        }

        System.out.println( listeDesIdentifiantsAchats );
    }

}
