

public class HightscoreDiff {

    private Highscore[] tabHighscores;

    public HightscoreDiff() {
        this.tabHighscores = new Highscore[60];
        for (int i = 1; i < 60; i++) {
            this.tabHighscores[i] = new Highscore();
        }
    }

    public HightscoreDiff(LectureFichier lecture) {
        this.tabHighscores = new Highscore[60];
        for (int i = 1; i < 60; i++) {
            this.tabHighscores[i] = new Highscore();
        }
        String[] l = lecture.lireFichier();
        int placeTab = 1;
        for (int i = 0; i < 354; i++) {
            int jj = 0;
            for (int j = i + 1; j <= i + 5; j++) {
                // System.out.println(l[j].charAt(10) + l[j] + "  Numero ligne = " + j);
                if (Character.isDigit(l[j].charAt(10)) && Character.isDigit(l[j].charAt(11))) {
                    this.tabHighscores[placeTab].NouveauScore(Integer.parseInt(l[j].substring(10, 12)));
                } else if (Character.isDigit(l[j].charAt(10))) {
                    this.tabHighscores[placeTab].NouveauScore(Integer.parseInt(l[j].substring(10, 11)));
                }
                jj = j;
            }
            placeTab++;
            i = jj;
        }

    }

    public void miseAJour(Highscore h, int difficulte) {
        int [] neew = h.getHighscore();
        int neewnbrSc = h.getNbscore();
        int [] base = this.tabHighscores[difficulte].getHighscore();
        int thisnbrSc = this.tabHighscores[difficulte].getNbscore();

        Highscore nouveau = new Highscore();

        for (int i = 0; i<neewnbrSc; i++){
            nouveau.NouveauScore(neew[i]);
        }
        for (int i = 0; i<thisnbrSc; i++){
            nouveau.NouveauScore(base[i]);
        }
        this.tabHighscores[difficulte] = nouveau;
    }

    public String toString() {
        String affichage = "";
        for (int i = 1; i < 60; i++) {
            affichage += "@" + this.toStringDiff(i);
        }

        return affichage;
    }

    public String toStringDiff(int difficulte) {
        String affichage = "Tableau des meilleurs scores " + difficulte + " \n";
        Highscore highscore = this.tabHighscores[difficulte];
        affichage += highscore.toString();
        return affichage;
    }

}
