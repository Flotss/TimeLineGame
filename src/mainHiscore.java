public class mainHiscore {
    public static void main(String[] args) {
        Highscore h = new Highscore();
        // HightscoreDiff hhh = new HightscoreDiff(new LectureFichier("score.txt"));
        HightscoreDiff hhh = new HightscoreDiff();


        String lesScore = hhh.toString();
        System.out.println(lesScore);
        String[] unTableau = lesScore.split("@");

        EcritureFichier fichier = new EcritureFichier("score.txt");
        fichier.ouvrirFichier();
        for (int i = 1; i < unTableau.length; i++) {
            String[] uneLigne = unTableau[i].split("\n");
            for (int j = 0; j < uneLigne.length; j++) {
                fichier.ecrireFichier(uneLigne[j]);
            }
        }
        fichier.fermerFichier();
    }
}
