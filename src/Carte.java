/**
 * La classe Carte
 */
public class Carte {
    private int date;
    private String evenement;
    private boolean recto;

    /**
     * Constructeur carte qui construit une carte par rapport a une chaine de
     * caractere
     *
     * @param ligne Chaine de caractere entree
     */
    public Carte(String ligne) {

        if (ligne != null) {
            // L'attribut recto doit etre faux
            this.recto = false;


            int i = 0;
            String evenementligne = "";
            // Ajout de tous les caracteres jusqu'a rencontrer ':'
            while (i < ligne.length() && ligne.charAt(i) != ':') {
                evenementligne += ligne.charAt(i);
                i++;
            }
            // affectation de l'attribut evenement
            this.evenement = evenementligne;

            // Ajout de tous de la date de fin et conversion en entier
            String date = "";
            i++;
            while (i < ligne.length()) {
                date += ligne.charAt(i);
                i++;
            }
            // affectation de l'attribut date
            this.date = Integer.parseInt(String.valueOf(date));
        }
    }

    /**
     * Methode toString qui affiche la carte
     * 
     * @return carteinfo
     */
    public String toString() {
        return (!recto) ? "??? -> " + this.evenement
                        : this.date + " -> " + this.evenement;
    }

    /**
     * Methode retourner Carte qui retourne la carte
     */
    public void retournerCarte() {
        this.recto = !this.recto;
    }

    /**
     * Getter de date
     *
     * @return La date
     */
    public int getDate() {
        return date;
    }

    /**
     * Getter de evenement
     *
     * @return L'evenement
     */
    public String getEvenement() {
        return evenement;
    }

    /**
     * Getter de recto
     *
     * @return Boolean si la carte est sur le recto ou sur le verso
     */
    public boolean getRecto() {
        return recto;
    }

    /**
     * Getter d'une carte
     *
     * @return La carte
     */
    public Carte getCarte() {
        return this;
    }
}