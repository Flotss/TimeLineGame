import static libtest.Lanceur.lancer;
import static libtest.OutilTest.assertEquals;
import libtest.*;

/**
 * classe de test qui permet de verifier que la classe Paquet
 * fonctionne correctement
 */
public class TestPaquet {

	/**
	 * methode de lancement des tests
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		lancer(new TestPaquet(), args);
	}

	/**
	 * test du constructeur vide
	 */
	public void test1_constructeur() {
		Paquet paquet = new Paquet();
		assertEquals("paquet devrait avoir 0 carte", 0, paquet.getNbCartes());
	}

	/**
	 * test du constructeur parametres
	 */
	public void test2_constructeurParam() {
		Carte[] tab = new Carte[3];

		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);
		assertEquals("paquet devrait avoir 3 cartes", 3, paquet.getNbCartes());
	}

	/**
	 * test getCarte
	 */
	public void test3_1_getCarte_ok() {
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);
		assertEquals("paquet devrait avoir 3 cartes", 3, paquet.getNbCartes());

		Carte c = paquet.getCarte(1);
		assertEquals("la carte 1 a pour date -200", -200, c.getDate());
		assertEquals("la carte 1 a evenement L'invention du papier", "L'invention du papier", c.getEvenement());
		assertEquals("la carte 1 a recto est faux", false, c.getRecto());
	}

	/**
	 * test getCarte hors tableau
	 */
	public void test3_2_getCarte_horsTableauSup() {
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);
		assertEquals("paquet devrait avoir 3 cartes", 3, paquet.getNbCartes());

		Carte c = paquet.getCarte(3);
		assertEquals("la carte 3 n'existe pas", null, c);
	}

		/**
	 * test getCarte hors tableau
	 */
	public void test3_3_getCarte_horsTableauInf() {
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);
		assertEquals("paquet devrait avoir 3 cartes", 3, paquet.getNbCartes());

		Carte c = paquet.getCarte(-5);
		assertEquals("la carte 3 n'existe pas", null, c);
	}

	/**
	 * test ajoutCarteFin ok
	 */
	public void test4_ajoutCarteFin() {
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);
		paquet.ajouterCarteFin(new Carte("L'invention du morse:1838"));

		assertEquals("paquet devrait avoir 4 cartes", 4, paquet.getNbCartes());

		// chaque carte doit etre bien placee: place i => valeur i+1
		Carte c = paquet.getCarte(0);
		assertEquals("la carte 0 a pour date -9000", -9000, c.getDate());
		assertEquals("la carte 0 a evenement L'invention du papier", "L'apparition de la ceramique", c.getEvenement());
		assertEquals("la carte 0 a recto est faux", false, c.getRecto());
		c = paquet.getCarte(1);
		assertEquals("la carte 1 a pour date -200", -200, c.getDate());
		assertEquals("la carte 1 a evenement L'invention du papier", "L'invention du papier", c.getEvenement());
		assertEquals("la carte 1 a recto est faux", false, c.getRecto());
		c = paquet.getCarte(2);
		assertEquals("la carte 2 a pour date -548", -548, c.getDate());
		assertEquals("la carte 2 a evenement La fondation du theoreme de Pythagore",
				"La fondation du theoreme de Pythagore", c.getEvenement());
		assertEquals("la carte 2 a recto est faux", false, c.getRecto());
		c = paquet.getCarte(3);
		assertEquals("la carte 3 a pour date 1838", 1838, c.getDate());
		assertEquals("la carte 3 a evenement L'invention du morse", "L'invention du morse", c.getEvenement());
		assertEquals("la carte 3 a recto est faux", false, c.getRecto());

	}

	/**
	 * test retirerCarte ok
	 */
	public void test5_1_retirerCarte() {
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);
		Carte c = paquet.retirerCarte(1);

		// test paquet
		assertEquals("paquet devrait avoir 2 cartes", 2, paquet.getNbCartes());
		assertEquals("premiere carte valeur 1", "L'apparition de la ceramique", paquet.getCarte(0).getEvenement());
		assertEquals("seconde carte valeur 3", "La fondation du theoreme de Pythagore",
				paquet.getCarte(1).getEvenement());

		// test carte retournee
		assertEquals("carte retiree a pour evenment L'invention du papier", "L'invention du papier", c.getEvenement());

	}

	/**
	 * test retirerCarte hors du tableau superieur
	 */
	public void test5_2_retirerCarte_HorsTabSup() {
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);
		Carte c = paquet.retirerCarte(10);

		// test paquet
		assertEquals("paquet devrait avoir 3 cartes", 3, paquet.getNbCartes());

		// test carte retournee
		assertEquals("carte retiree a pour evenment L'invention du papier", null, c);

	}

	/**
	 * test retirerCarte hors du tableau inferieur
	 */
	public void test5_3_retirerCarte_HorsTabInf() {
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);
		Carte c = paquet.retirerCarte(-10);

		// test paquet
		assertEquals("paquet devrait avoir 3 cartes", 3, paquet.getNbCartes());

		// test carte retournee
		assertEquals("carte retiree a pour evenment L'invention du papier", null, c);

	}

	/**
	 * Test 6 piocher carte.
	 */
	public void test6_piocherHasard() {
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("La fondation du theoreme de Pythagore:-548");

		Paquet paquet = new Paquet(tab);

		Carte c = paquet.piocherHasard();

		assertEquals("paquet devrait avoir 2 cartes", 2, paquet.getNbCartes());

		if (tab[0] == c || tab[1] == c || tab[2] == c) {
			if (tab[0].getEvenement() == c.getEvenement()) {
				assertEquals("La carte retirer n'est pas la bonne", "L'apparition de la ceramique", c.getEvenement());
				assertEquals("La carte de position 0 n'est pas la bonne", "L'invention du papier",
						paquet.getCarte(0).getEvenement());
				assertEquals("La carte de position 1 n'est pas la bonne", "La fondation du theoreme de Pythagore",
						paquet.getCarte(1).getEvenement());
			} else {
				if (tab[1].getEvenement() == c.getEvenement()) {
					assertEquals("La carte retirer n'est pas la bonne", "L'invention du papier", c.getEvenement());
					assertEquals("La carte de position 0 n'est pas la bonne", "L'apparition de la ceramique",
							paquet.getCarte(0).getEvenement());
					assertEquals("La carte de position 1 n'est pas la bonne", "La fondation du theoreme de Pythagore",
							paquet.getCarte(1).getEvenement());
				} else {
					if (tab[2].getEvenement() == c.getEvenement()) {
						assertEquals("La carte retirer n'est pas la bonne", "La fondation du theoreme de Pythagore",
								c.getEvenement());
						assertEquals("La carte de position 0 n'est pas la bonne", "L'apparition de la ceramique",
								paquet.getCarte(0).getEvenement());
						assertEquals("La carte de position 1 n'est pas la bonne", "L'invention du papier",
								paquet.getCarte(1).getEvenement());
					}
				}
			}
		} else {
			throw new Error("Probleme d'aléatoire");
		}
	}

	/**
	 * Test 7 Afficher carte.
	 */
	public void testCarte7_toString() {
		Carte[] tab = new Carte[2];
		tab[0] = new Carte("L'apparition de la ceramique:-9000");
		tab[1] = new Carte("L'invention du papier:-200");

		Paquet paquet = new Paquet(tab);
		assertEquals("L'ecriture n'est pas bonne",
				"0 - ??? -> L'apparition de la ceramique\n1 - ??? -> L'invention du papier\n", paquet.toString());
	}

}
