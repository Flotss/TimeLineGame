/**
 * The type Main carte.
 */
public class MainCarte {

    /**
     * Main qui permet d'afficher toutes les cartes d'un fichier
     *
     * @param args Argument pour le main
     */
    public static void main(String[] args) {
        LectureFichier lF = new LectureFichier("../cartes/timeline.txt");
        String [] fich = lF.lireFichier();
        
        for (int i = 0; i < fich.length; i++){
            Carte carte = new Carte(fich[i]);
            System.out.println(carte);
            carte.retournerCarte();
            System.out.println(carte);
        }
    }
}