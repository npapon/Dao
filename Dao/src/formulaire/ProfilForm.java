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
import bean.Session;
import constante.AttributsServlet;
import constante.MessagesErreur;
import constante.MessagesSucces;
import constante.ParametresFormulaire;
import constante.Tampon;
import dao.DAOException;
import dao.ImageDao;
import eu.medsea.mimeutil.MimeUtil;
import exception.FormValidationException;

public class ProfilForm {
    List<String>     erreurs = new ArrayList<String>();
    private ImageDao imageDao;
    private String   creationAutorisee;

    public ProfilForm( ImageDao imageDao ) {
        this.imageDao = imageDao;
    }

    public Image

            creerImageProfil( String chemin, HttpServletRequest request ) throws Exception {
        // recuperation email

        Image image = new Image();
        try {
            Part part = chargerImageProfil( request );
            affecterEmail( image, request );
            String libelleImage = affecterLibelleImage( part, image );
            String emplacement = affecterEmplacementImage( chemin, libelleImage, image );

            if ( erreurs.isEmpty() ) {
                creationAutorisee = MessagesSucces.IMAGEPROFIL_CREE;
                enregistrerImageProfil( part, chemin, libelleImage );
                if ( imageDao.rechercherImage( image.getEmail() ) == null ) {
                    imageDao.creerImage( image );
                } else {
                    imageDao.modifierImage( image );
                }
                System.out.println( creationAutorisee );
            } else {
                creationAutorisee = MessagesErreur.CREATION_IMAGEPROFIL_KO;
                System.out.println( creationAutorisee );
            }

        } catch ( DAOException e ) {
            creationAutorisee = MessagesErreur.CREATION_IMAGEPROFIL_KO_RAISON_BDD;
            System.out.println( creationAutorisee );
            e.printStackTrace();
        }
        return image;
    }

    // getParts permet de récupérer les données envoyer dans un formulaire
    // de type type enctype="multipart/form-data" (fichiers, champs
    // classiques ...
    // elle retourne une collection d'éléments de type Part
    // Collection<Part> parts = request.getParts();
    // getPart permet de récupérer un élément du formulaire en particulier
    // passé en paramètre

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
     * Comment savoir si on a un fichier dans les éléments du formulaire une
     * seule solution : vérifier que la requête envoyée dans POST contient
     * l'attribut filename
     * 
     * Ex de requête post avec un fichier :
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

    public void affecterEmail( Image image, HttpServletRequest request ) {

        HttpSession session = request.getSession();
        Session sessionActive = (Session) session.getAttribute( AttributsServlet.SESSIONACTIVE );

        String email = sessionActive.getEmail();

        if ( email.isEmpty() ) {
            try {
                throw ( new FormValidationException( MessagesErreur.EMAIL_ABSENT ) );
            } catch ( FormValidationException e ) {
                {
                    System.out.println( e.getMessage() );
                    erreurs.add(
                            e.getMessage() );
                }
            }
        } else {
            image.setEmail( email );
        }
    }

    public String affecterLibelleImage( Part part, Image image ) throws Exception {

        String libelleImage = this.recupererNomImage( part );

        validationFormatImage( part );

        image.setLibelle( libelleImage );

        return libelleImage;

    }

    public String affecterEmplacementImage( String chemin, String libelleImage, Image image ) {
        String emplacement = chemin + "/" + libelleImage;
        if ( emplacement.isEmpty() ) {
            try {
                throw new FormValidationException( MessagesErreur.EMPLACEMENT_VIDE );
            } catch ( FormValidationException e ) {
                System.out.println( e.getMessage() );
                erreurs.add(
                        e.getMessage() );
            }

        } else {
            image.setEmplacement( emplacement );
        }
        return emplacement;
    }

    private void validationFormatImage( Part part ) throws Exception {

        InputStream contenuFichier = null;
        // recupération du contenu du fichier

        contenuFichier = part.getInputStream();

        /*
         * Extraction du type MIME (charger la librarie) du fichier depuis
         * l'InputStream
         */

        MimeUtil.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
        // <?> correspond à une collection qui accepte tout type
        Collection<?> mimeTypes = MimeUtil.getMimeTypes( contenuFichier );
        System.out.println( mimeTypes );// ex : image/jpeg

        /*
         * Si le fichier est bien une image, alors son en-tête MIME commence par
         * la chaîne "image"
         */
        if ( !mimeTypes.toString().startsWith( "image" ) ) {

            erreurs.add( MessagesErreur.FICHIER_PAS_UNE_IMAGE );
            throw new Exception( MessagesErreur.FICHIER_PAS_UNE_IMAGE );

        }

    }

    private void enregistrerImageProfil( Part part, String chemin, String libelleImage ) {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream( part.getInputStream(), Tampon.TAILLE_TAMPON_10240 );

            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + "/" + libelleImage ) ),
                    Tampon.TAILLE_TAMPON_10240 );

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
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
        System.out.println( "Fichier enregistré ici : " + chemin + "/" + libelleImage );

    }
}
