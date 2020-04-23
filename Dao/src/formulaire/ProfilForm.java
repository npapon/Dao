package formulaire;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.Image;
import bean.Utilisateur;
import constante.AttributsServlet;
import constante.MessagesErreur;
import constante.ParametresFormulaire;
import constante.Tampon;
import eu.medsea.mimeutil.MimeUtil;

public class ProfilForm {
    List<String> erreurs = new ArrayList<String>();

    public Image

            enregistrerImageProfil( String chemin, HttpServletRequest request ) throws Exception {
        /* Pr�pare les flux. */

        Image image = new Image();
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( AttributsServlet.UTILISATEUR );
        String email = utilisateur.getEmail();
        image.setEmail( email );
        System.out.print( "email r�cup�r� � persisiter dans table image �& " + email );
        Part part = chargerImageProfil( request );
        String libelleImage = this.recupererNomImage( part );
        image.setLibelle( libelleImage );
        validationFormatImage( part );

        System.out.println( "Fichier enregistr� ici : " + chemin + "/" + libelleImage );
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream( part.getInputStream(), Tampon.TAILLE_TAMPON_10240 );

            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + "/" + libelleImage ) ),
                    Tampon.TAILLE_TAMPON_10240 );

            /*
             * Lit le fichier re�u et �crit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[Tampon.TAILLE_TAMPON_10240];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }

        }

        catch ( FileNotFoundException e ) {
            e.printStackTrace();

        }

        catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
        return image;
    }

    // getParts permet de r�cup�rer les donn�es envoyer dans un formulaire
    // de type type enctype="multipart/form-data" (fichiers, champs
    // classiques ...
    // elle retourne une collection d'�l�ments de type Part
    // Collection<Part> parts = request.getParts();
    // getPart permet de r�cup�rer un �l�ment du formulaire en particulier
    // pass� en param�tre

    public Part chargerImageProfil( HttpServletRequest request ) {

        Part part = null;
        try {
            part = request.getPart( ParametresFormulaire.IMAGEPROFIL );

            champContientUnFichier( part );

        } catch ( IOException | ServletException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( IllegalStateException e ) {
            e.printStackTrace();
            erreurs.add( MessagesErreur.TAILLE_FICHIER_TROP_GRANDE );

        }

        return part;
    }

    /*
     * Comment savoir si on a un fichier dans les �l�ments du formulaire une
     * seule solution : v�rifier que la requ�te envoy�e dans POST contient
     * l'attribut filename
     * 
     * Ex de requ�te post avec un fichier :
     * 
     * Content-Disposition: form-data;name="fichier";
     * filename="nom_du_fichier.ext"
     */

    private void champContientUnFichier( Part part ) {

        Boolean estUnFichier = false;
        String enteteRequetePostContentDisposition = part.getHeader( "Content-Disposition" );

        for ( String coupleAttributValeur : enteteRequetePostContentDisposition.split( ";" ) ) {
            if ( coupleAttributValeur.trim().startsWith( "filename" ) ) {
                estUnFichier = true;
            }
        }
        if ( estUnFichier == false ) {
            try {
                throw ( new Exception( MessagesErreur.AUCUN_FICHIER ) );
            } catch ( Exception e ) {
                e.printStackTrace();
                erreurs.add( e.getMessage() );
            }
        }

    }

    public String recupererNomImage( Part part ) {
        String enteteRequetePostContentDisposition = part.getHeader( "Content-Disposition" );
        System.out.println( "enteteRequetePostContentDisposition " + enteteRequetePostContentDisposition );
        String nomImage = null;
        for ( String coupleAttributValeur : enteteRequetePostContentDisposition.split( ";" ) ) {
            if ( coupleAttributValeur.trim().startsWith( "filename" ) ) {

                nomImage = coupleAttributValeur.substring( coupleAttributValeur.lastIndexOf( "\\" ) + 1,
                        coupleAttributValeur.lastIndexOf( "\"" ) );

                return nomImage;
            }
        }
        return nomImage;

    }

    private void validationFormatImage( Part part ) throws Exception {

        InputStream contenuFichier = null;
        // recup�ration du contenu du fichier

        contenuFichier = part.getInputStream();

        /*
         * Extraction du type MIME (charger la librarie) du fichier depuis
         * l'InputStream
         */

        MimeUtil.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
        // <?> correspond � une collection qui accepte tout type
        Collection<?> mimeTypes = MimeUtil.getMimeTypes( contenuFichier );
        System.out.println( mimeTypes );// ex : image/jpeg

        /*
         * Si le fichier est bien une image, alors son en-t�te MIME commence par
         * la cha�ne "image"
         */
        if ( !mimeTypes.toString().startsWith( "image" ) ) {

            erreurs.add( MessagesErreur.FICHIER_PAS_UNE_IMAGE );
            throw new Exception( MessagesErreur.FICHIER_PAS_UNE_IMAGE );

        }

    }
}