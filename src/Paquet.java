import java.util.Random;

/**
 * La classe Paquet.
 */
public class Paquet {

    private Carte[] paquet;

    /**
     * Constructeur sans parametre
     */
    public Paquet() {
        this.paquet = new Carte[0];
    }

    /**
     * Constructeur avec parametres
     *
     * @param nCartes Un tableau de carte
     */
    public Paquet(Carte[] nCartes) {
        if (nCartes != null) {
            this.paquet = nCartes;
        } else {
            this.paquet = new Carte[0];
        }
    }

    /**
     * Methode qui crée un paquet en fonction d'un fichier
     *
     * @param nomFichier Le nom du fichier
     */
    public Paquet(String nomFichier) {
        // Construction d'un tableau de chaine de caractere qui a pour valeur des cartes
        // pas encore construite
        LectureFichier fichier = new LectureFichier(nomFichier);
        String[] fich = fichier.lireFichier();

        // Construction du paquet à partir du tableau fich
        this.paquet = new Carte[fich.length];
        for (int i = 0; i < fich.length; i++) {
            Carte carte = new Carte(fich[i]);
            this.paquet[i] = carte;
        }
    }

    /**
     * Methode pour ajouter une carte a la fin.
     *
     * @param c La carte rentree
     */
    public void ajouterCarteFin(Carte c) {
        if (c != null) {
            Carte[] npaquet = new Carte[this.paquet.length + 1];
            if (this.paquet.length != 0) {
                // ajout de toutes les valeurs de l'attribut paquet dans le nouveau tableau
                for (int i = 0; i < this.getNbCartes(); i++) {
                    npaquet[i] = this.paquet[i];
                }
                // ajout de la nouvelle carte a la fin
                npaquet[npaquet.length - 1] = c;
            } else {
                // ajout de la carte puisque le paquet est vide
                npaquet[0] = c;
            }
            // affectation de l'attribut paquet avec le nouveau tableau
            this.paquet = npaquet;
        }
    }

    /**
     * Retirer une carte a une place
     *
     * @param place place voulu 
     * @return la carte retire si elle l'est sinon retourne null 
     */
    public Carte retirerCarte(int place) {
        Carte[] npaquet = new Carte[this.paquet.length - 1];
        Carte carte = null;
        // verification de la place
        if (place > -1 && place < this.paquet.length) {
            // ajout des valeurs dans le nouveau paquet sans prendre la carte
            for (int i = 0, j = 0; i < this.paquet.length; i++) {
                if (i != place) {
                    npaquet[j] = this.paquet[i];
                    j++;
                } else {
                    carte = this.paquet[i];
                }
            }
            // affectation de l'attribut paquet avec le nouveau tableau
            this.paquet = npaquet;
        }
        // retourne la carte retirer
        return carte;
    }

    /**
     * Methode pour piocher une carte au hasard
     *
     * @return La carte piocher
     */
    public Carte piocherHasard() {
        Carte cartepiocher = null;

        Random r = new Random();
        int placePiocher = -1;
        // verification que le tableau n'est pas vide
        if (this.paquet.length > 0) {
            placePiocher = r.nextInt(this.paquet.length);
            cartepiocher = this.paquet[placePiocher];
        }
        // verification que la place est possible
        if (placePiocher > -1) {
            this.retirerCarte(placePiocher);
        }

        // retourne la carte piocher
        return cartepiocher;
    }

    /**
     * Methode pour afficher le paquet
     */

    public String toString() {
        String affichage = "";
        for (int i = 0; i < this.getNbCartes(); i++) {
            affichage += i + " - " + this.paquet[i].toString() + "\n";
        }
        return affichage;
    }

    /**
     * Getter de la longueur du paquet
     *
     * @return length du paquet
     */
    public int getNbCartes() {
        return this.paquet.length;
    }

    /**
     * Getter de la carte a une place donnee
     *
     * @param place La place donnee
     * @return la carte retire
     */
    public Carte getCarte(int place) {
        Carte Carte;
        if (place < this.paquet.length && place > -1) {
            Carte = this.paquet[place];
        } else {
            Carte = null;
        }
        return Carte;
    }

}
