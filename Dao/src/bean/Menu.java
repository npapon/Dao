package bean;

public class Menu {

    int    id;

    String item;

    String description;

    int    ordre;

    String actif;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem( String item ) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre( int ordre ) {
        this.ordre = ordre;
    }

    public String getActif() {
        return actif;
    }

    public void setActif( String actif ) {
        this.actif = actif;
    }

}
