public class Highscore {

    private int[] highscore;
    private int nbscore;

    public Highscore() {
        this.highscore = new int[5];
        this.nbscore = 0;
    }

    public void NouveauScore(int s) {
        if (this.nbscore > 0) {
            if (this.nbscore != 5) {
                this.highscore[this.nbscore] = s;
                this.nbscore++;
                for (int i = 0; i < this.nbscore; i++) {
                    for (int j = 0; j < this.nbscore-1; j++) {
                        if (this.highscore[j] > this.highscore[j + 1]) {
                            int prec = this.highscore[j];
                            this.highscore[j] = this.highscore[j+1];
                            this.highscore[j+1] = prec;
                        }
                    }
                }
            } else {
                boolean trouve = false;
                int place = 0;
                while (!trouve && place < 5) {
                    if (this.highscore[place] >= s) {
                        trouve = true;
                        for (int i = 4; i > place; i--) {
                            this.highscore[i] = this.highscore[i-1];
                        }
                        this.highscore[place] = s;
                    }else{
                        place++;
                    }
                }
            }
        } else {
            this.highscore[0] = s;
            this.nbscore++;
        }
    }





    public String toString() {
        String affichage = "";
        for (int i = 1; i <= 5; i++) {
            if (i <= this.nbscore) {
                affichage += "    - " + (i) + " - " + this.highscore[i-1] + " \n";
            } else {
                affichage += "    - Aucun resultat\n";
            }
        }
        return affichage;
    }


    public int[] getHighscore() {
        return highscore;
    }

    public void setHighscore(int[] highscore) {
        this.highscore = highscore;
    }

    public int getNbscore() {
        return nbscore;
    }

    public void setNbscore(int nbscore) {
        this.nbscore = nbscore;
    }
}
