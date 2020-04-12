
public class Vehicule {

    String nom;
    String marque;

    public Vehicule( String nom, String marque ) {
        this.nom = nom;
        this.marque = marque;

    }

    public Vehicule getInstance()

    {

        try {
            String nom = "volvo";
        } catch ( Exception e ) {
        }
        String marque = "Wolkswagen";
        Vehicule instance = new Vehicule( nom, marque );

        return instance;
    }

}
