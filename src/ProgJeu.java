import java.util.Scanner;
import java.io.File;

/**
 * La classe ProgJeu
 */
public class ProgJeu {
    /**
     * Main de ProgJeu permettant de lancer le jeu timeline
     *
     * @param args Emplacement du fichier
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Highscore highscore = new Highscore();
        HightscoreDiff toutlHightscorediff = new HightscoreDiff(new LectureFichier("score.txt"));
        String[] fichier;

        int nbrFicher = 0;
        while (nbrFicher <= 0) {
            System.out.println("Combien de fichier voulez-vous mettre : ");
            nbrFicher = sc.nextInt();
        }
        fichier = new String[nbrFicher];

        for (int i = 0; i < nbrFicher; i++) {
            System.out.println("Donner le bon emplacement du fichier numero " + (i + 1) + " : ");
            fichier[i] = sc.next();
            // verification que le fichier existe
            File f = new File(fichier[i]);
            while (!f.exists() && !f.isDirectory()) {
                System.out.println("Donner le bon emplacement du fichier numero " + (i + 1) + " : ");
                fichier[i] = sc.next();
                f = new File(fichier[i]);
            }
        }

        System.out.print("Ecrivez le nombre de carte de la main : ");
        int difficulte = sc.nextInt();
        while (difficulte < 1 && difficulte > 60) {
            System.out.println("Ecrivez le bon nombre de carte de la main (entre 1 et 59");
            difficulte = sc.nextInt();
        }

        boolean bjeu = true;
        while (bjeu) {
            Jeu jeu = new Jeu(fichier, difficulte);
            int s = jeu.demarrerUnePartie();
            // highscore ne marche pas.
            highscore.NouveauScore(s);
            System.out.println("\nVoulez-vous commencer une nouvelle partie ? (oui ou non)");
            String npartie = sc.nextLine();
            while (!npartie.equals("oui") && !npartie.equals("non")) {
                System.out.println("Oui ou non : ");
                npartie = sc.nextLine();
            }
            if (npartie.equals("non")) {
                bjeu = false;
                toutlHightscorediff.miseAJour(highscore, difficulte);
            } else if (npartie.equals("oui")) {
                toutlHightscorediff.miseAJour(highscore, difficulte);
                highscore = new Highscore();
                System.out.println("Quel nombre de carte dans la main");
                difficulte = sc.nextInt();
            }
        }

        String lesScore = toutlHightscorediff.toString();
        String[] unTableau = lesScore.split("@");

        EcritureFichier fichierE = new EcritureFichier("score.txt");
        fichierE.ouvrirFichier();
        for (int i = 1; i < unTableau.length; i++) {
            String[] uneLigne = unTableau[i].split("\n");
            for (int j = 0; j < uneLigne.length; j++) {
                fichierE.ecrireFichier(uneLigne[j]);
            }
        }
        fichierE.fermerFichier();

        sc.close();

    }
}
