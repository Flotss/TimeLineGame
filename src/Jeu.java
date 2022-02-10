import java.util.Scanner;

/**
 * La classe Jeu.
 */
public class Jeu {

    private Paquet joueur, pioche;
    private Frise frise;
    private int score;

    /**
     * Constructeur qui initialise la classe jeu .
     *
     * @param fichier    Le tableau de fichier
     * @param tailleMain taille de la main
     */
    public Jeu(String[] fichier, int tailleMain) {
        this.iniPiochefichier(fichier);
        this.friseInitialisee();
        this.tireCarteMainJoueur(tailleMain);
        this.score = 0;
    }
    
    /**
     * Methode qui initialise la pioche avec un ou plusieurs fichiers
     * 
     * @param fichier Le tableau de nom de fichier
     */
    public void iniPiochefichier(String[] fichier) {
        this.pioche = new Paquet();
        for (int i = 0; i < fichier.length; i++) {
            LectureFichier l = new LectureFichier(fichier[i]);
            String[] fich = l.lireFichier();
            for (int j = 0; j < fich.length; j++) {
                Carte carte = new Carte(fich[j]);
                this.pioche.ajouterCarteFin(carte);
            }
        }
    }

    /**
     * Tire carte main joueur.
     *
     * @param taillemain taille de la main
     */
    public void tireCarteMainJoueur(int taillemain) {
        this.joueur = new Paquet();
        for (int i = 0; i < taillemain; i++) {
            Carte ctiree = this.pioche.piocherHasard();
            this.joueur.ajouterCarteFin(ctiree);
        }
    }

    /**
     * Frise initialisee.
     */
    public void friseInitialisee() {
        this.frise = new Frise();
    }

    /**
     * Methode qui verifie si le coup est possible
     *
     * @param placeCarteJoueur place carte a joue dans la main du joueur
     * @param placeCarteFrise  place dans la frise
     * @return booleen si possible il change l'attribut sinon il ne le fait pas
     */
    public boolean possiblecoup(int placeCarteJoueur, int placeCarteFrise) {
        boolean possible = false;

        if (placeCarteJoueur != -1 && placeCarteJoueur < this.joueur.getNbCartes()) {
            possible = this.frise.insererCarteApres(this.joueur.getCarte(placeCarteJoueur), placeCarteFrise);
        } else {
            possible = this.frise.ajouterCarteDebut(this.joueur.getCarte(placeCarteJoueur));
        }
        if (!possible) {
            this.joueur.retirerCarte(placeCarteJoueur);
            this.joueur.ajouterCarteFin(this.pioche.piocherHasard());
        } else {
            this.joueur.retirerCarte(placeCarteJoueur);
        }
        this.score++;
        return possible;
    }

    /**
     * Affiche toute la frise et toute la main du joueur
     * 
     * @return les deux string
     */
    public String toString() {
        String info = "";
        info += "--------------\n" + "La frise \n--------------\n" + this.frise.toString();
        info += "--------------\n" + "Main du joueur \n--------------\n" + this.joueur.toString() + "\n";
        return info;
    }

    /**
     * Afficher l emplacement entre lesquels la carte devrait se placer
     *
     * @param placeMain  place choisi dans la main du joueur
     * @param placefrise place dans la frise
     * @return string
     */
    public String afficherEmplacement(int placeMain, int placefrise) {
        String info = "";
        this.joueur.getCarte(placeMain).retournerCarte();
        if (placefrise == -1 && this.frise.getNbCartesFrise() > 0) {
            // carte en premiere position donc pas besoin d'afficher la carte d apres
            info += "Derriere ....\n";
            info += "    - " + this.frise.getCarte(0).toString() + "\n";
            info += "- carte jouee :" + this.joueur.getCarte(placeMain);
        } else if (placefrise == -1 && this.frise.getNbCartesFrise() == 0) {
            // frise vide donc pas besoin d'afficher d'autres cartes
            info += "La frise est vide donc le jeu peut commencer \n";
            info += "- carte jouee : " + this.joueur.getCarte(placeMain);
        } else if (placefrise == this.frise.getNbCartesFrise() - 1) {
            // carte en derniere position donc pas de carte derriere
            info += "Apres ....\n";
            info += "    - " + this.frise.getCarte(this.frise.getNbCartesFrise() - 1).toString() + "\n";
            info += "- carte jouee : " + this.joueur.getCarte(placeMain);
        } else if (placefrise > -1 && placefrise < this.frise.getNbCartesFrise() - 1) {
            // carte entre deux cartes
            info += "Entre ....\n";
            info += "    - " + this.frise.getCarte(placefrise).toString() + "\n";
            info += "    - " + this.frise.getCarte(placefrise + 1).toString() + "\n";
            info += "- carte jouee : " + this.joueur.getCarte(placeMain);
        }
        return info;
    }

    /**
     * Methode pour demarrer une partie
     */
    public int demarrerUnePartie() {
        // initialisation du jeu
        Scanner sc = new Scanner(System.in);
        boolean bjeu = true;
        System.out.println(this);

        // Le jeu en
        while (bjeu) {
            System.out.print("Quelle carte de votre main ? ");
            int placeCarteJoueur = sc.nextInt();
            // verification de la bonne place de la carte du joueur
            while (placeCarteJoueur >= this.joueur.getNbCartes() || placeCarteJoueur < 0) {
                System.out.println(
                        "Ecrire un emplacement de carte de votre main valide entre 0 et "
                                + (this.joueur.getNbCartes() - 1));
                placeCarteJoueur = sc.nextInt();
            }
            System.out.println(this.joueur.getCarte(placeCarteJoueur) + "\n");
            System.out.print("Derriere quelle carte de la frise ? ");
            int placeCarteFrise = sc.nextInt();
            // verification de la bonne place de la carte de la frise
            while (placeCarteFrise < -1 || placeCarteFrise > this.frise.getNbCartesFrise() - 1) {
                if (this.frise.getNbCartesFrise() > 0) {
                    System.out.println("Ecrire un emplacement de carte de la frise valide entre 0 et "
                            + (this.frise.getNbCartesFrise() - 1) + " ou -1 pour ajouter au debut de la frise");
                } else {
                    System.out.println("La frise est vide alors il faut mettre -1");
                }
                placeCarteFrise = sc.nextInt();
            }
            // affiche les elements qui entoure la place choisi
            System.out.println(this.afficherEmplacement(placeCarteJoueur, placeCarteFrise));

            Boolean possible = this.possiblecoup(placeCarteJoueur, placeCarteFrise);

            // affiche si le joueur a reussi son coup
            if (possible) {
                System.out.println("!!!! Une carte de placee !!!!\n");
            } else {
                System.out.println("La carte defosse et vous piocher une carte\n");
            }

            // affiche le jeu (main du joueur + frise)
            System.out.println(this);

            // affiche la fin du jeu et arrete la boucle
            if (this.joueur.getNbCartes() == 0) {
                System.out.println("!!!!!!  VICTOIRE  !!!!!!");
                bjeu = false;
            } else if (this.pioche.getNbCartes() == 0) {
                System.out.println("C'est une defaite");
                bjeu = false;
            }

        }
        System.out.println("Votre score est de : " + this.score);
        return this.score;
    }

}
