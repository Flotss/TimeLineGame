import java.util.Arrays;

/**
 * La classe Frise
 */
public class Frise {
    private Carte[] frise;

    /**
     * Constructeur de la frise.
     *
     * @param tab Un tableau de carte.
     */
    public Frise(Carte[] tab) {
        this.frise = tab;

        // tri a bulle
        for (int i = 0; i < this.frise.length; i++) {
            for (int j = 0; j < this.frise.length; j++) {
                if (j + 1 < this.frise.length) {
                    if (this.frise[j].getDate() > this.frise[j + 1].getDate()) {
                        Carte prec = this.frise[j + 1];
                        this.frise[j + 1] = this.frise[j];
                        this.frise[j] = prec;
                    }
                }
            }
        }

        // retourner toutes les cartes pour que les cartes pour voir la date
        for (int i = 0; i < this.frise.length; i++) {
            if (!this.frise[i].getRecto())
                this.frise[i].retournerCarte();
        }
    }

    /**
     * Constructeur vide de la frise sans parametre.
     */
    public Frise() {
        this.frise = new Carte[0];
    }

    /**
     * Methode pour verifier si une carte peut s'inserer apres une place.
     *
     * @param c     La carte
     * @param place La place ou l'on veut inserer
     * @return Boolean si on peut inserer oui ou non.
     */
    public boolean verifierCarteApres(Carte c, int place) {
        boolean verif = false;

        // verification que la frise ait au moins une carte
        if (this.getNbCartesFrise() > 0) {
            // verification que la place est possible
            if (place < this.getNbCartesFrise() - 1 && place > -1) {
                // verification des dates et que la place est possible
                if (this.frise[place].getDate() < c.getDate() && this.frise[place + 1].getDate() > c.getDate()) {
                    verif = true;
                }
                // verification : si on peut ajouter a la fin de la frise
            } else if (this.frise[this.getNbCartesFrise() - 1].getDate() < c.getDate()
                    && place == this.getNbCartesFrise() - 1) {
                verif = true;
                // verification : si on peut ajouter au debut (-1)
            } else if (this.frise[0].getDate() > c.getDate() && place == -1) {
                verif = true;
            }
        } else {
            // vrai puisque la frise est vide
            verif = true;
        }

        return verif;
    }

    /**
     * Methode pour inserer carte apres une place donnee.
     *
     * @param c     La carte que l'on souhaite inserer.
     * @param place La place de la carte.
     * @return Boolean Vrai ou faux Pour savoir si la carte a ete inserer.
     */
    public boolean insererCarteApres(Carte c, int place) {
        boolean inserer = false;
        // verification que la frise ait plus d'une carte
        if (this.getNbCartesFrise() > 0) {
            // verification que la place est possible et que la carte existe
            if (c != null && this.verifierCarteApres(c, place)) {
                Carte[] ntabCartes = new Carte[this.frise.length + 1];
                // ajout des valeurs de la frise dans un nouveau tableau jusqu'a la place
                for (int i = 0; i <= place; i++) {
                    ntabCartes[i] = this.frise[i];
                }
                // retourne la carte si le recto est faux
                if (!c.getRecto()) {
                    c.retournerCarte();
                }

                // ajoute la nouvelle carte
                ntabCartes[place + 1] = c;

                // ajoute les cartes la suite de la frise dans le nouveau tableau
                for (int i = place + 1; i < this.frise.length; i++) {
                    ntabCartes[i + 1] = this.frise[i];
                }
                // affectation de l'attribut paquet avec le nouveau tableau
                this.frise = ntabCartes;
                inserer = true;
            }
        } else if (c != null) {
            // ajout de la carte puisque la frise est vide
            this.frise = new Carte[1];
            this.frise[0] = c;
            inserer = true;
        }

        // retourne si la carte a ete inserer
        return inserer;
    }

    /**
     * Ajouter carte debut.
     *
     * @param c carte que l'on souhaite insere
     * @return booleen qui indique si la carte a etait inserer ou non
     */
    public boolean ajouterCarteDebut(Carte c) {
        boolean inserer = false;


        // verification que la frise ait au moins une carte
        if (this.getNbCartesFrise() > 0) {
            // verification que la carte existe et que on peut la mettre
            if (c != null && this.verifierCarteApres(c, -1)) {
                Carte[] ntabCartes = new Carte[this.frise.length + 1];
                ntabCartes[0] = c;
                for (int i = 0; i < this.frise.length; i++) {
                    ntabCartes[i + 1] = this.frise[i];
                }
                if (!c.getRecto()) {
                    c.retournerCarte();
                }
                this.frise = ntabCartes;
                inserer = true;
            }
        } else {
            // ajout de la carte puisque le tableau est vide
            this.frise = new Carte[1];
            this.frise[0] = c;
            inserer = true;
        }

        return inserer;
    }

    /**
     * Methode qui ajoute une carte trie.
     *
     * @param c Carte que l'on veut ajouter
     */
    public void ajouterCarteTrie(Carte c) {
        boolean trouve = false;
        int place = 0;
        // trouver la place
        while (place < this.getNbCartesFrise() && !trouve) {
            if (this.verifierCarteApres(c, place)) {
                trouve = true;
            } else {
                place++;
            }
        }
        // si la carte a ete trouver la placer avec insirer carte
        // sinon ajouter au debut
        if (trouve) {
            this.insererCarteApres(c, place);
        } else {
            this.ajouterCarteDebut(c);
        }
    }

    /**
     * Methode qui affiche la frise.
     *
     * @return un string
     */
    public String toString() {
        String affichage = "";
        for (int i = 0; i < this.frise.length; i++) {
            affichage += i + " - " + this.frise[i].toString() + "\n";
        }
        return affichage;
    }

    /**
     * Getter d'un carte a une place donnee
     *
     * @param place La place de la carte que l'on veut
     * @return La carte a la place p
     */
    public Carte getCarte(int place) {
        return (place < this.frise.length && place > -1)
                            ? this.frise[place]
                            : null;
    }

    /**
     * Getter Le nombre de carte de la frise.
     *
     * @return Le nombre de carte de la frise
     */
    public int getNbCartesFrise() {
        return this.frise.length;
    }

}
