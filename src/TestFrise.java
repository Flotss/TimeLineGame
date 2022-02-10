import static libtest.Lanceur.lancer;
import static libtest.OutilTest.assertEquals;
import libtest.*;

/**
 * classe de test qui permet de verifier que la classe Frise
 * fonctionne correctement
 */

public class TestFrise {

	/**
	 * methode de lancement des tests
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		lancer(new TestFrise(), args);
	}

	// #######################################
	// ## tests a ecrire
	// #######################################

	/**
	 * Test 1 constructeur.
	 */
	public void test1_constructeur() {
		Frise frise = new Frise();
		assertEquals("paquet devrait avoir 0 carte", 0, frise.getNbCartesFrise());
	}

	/**
	 * Test 2 constructeur param.
	 */
	public void test2_constructeurParam() {
		Carte[] tab = new Carte[3];

		tab[0] = new Carte("L'invention du papier:-200");
		tab[1] = new Carte("La fondation du theoreme de Pythagore:-548");
		tab[2] = new Carte("L'apparition de la ceramique:-9000");

		Frise frise = new Frise(tab);
		assertEquals("paquet devrait avoir 3 cartes", 3, frise.getNbCartesFrise());
		for (int i = 0; i < frise.getNbCartesFrise(); i++) {
			assertEquals("paquet devrait être retouner true pour toute les cartes", true, frise.getCarte(i).getRecto());
		}
		assertEquals("Le résultat doit être -9000", -9000, frise.getCarte(0).getDate());
		assertEquals("Le résultat doit être -548", -548, frise.getCarte(1).getDate());
		assertEquals("Le résultat doit être -200", -200, frise.getCarte(2).getDate());
	}

	// .......................................

	/**
	 * Test 3 verifier carte apres frise param.
	 */
	public void test3_1_verifierCarteApres_friseParam() {
		Carte[] tab = new Carte[3];

		tab[0] = new Carte("L'invention du papier:-200");
		tab[1] = new Carte("La fondation du theoreme de Pythagore:-548");
		tab[2] = new Carte("L'apparition de la ceramique:-9000");
		Frise frise = new Frise(tab);

		Carte nCarte = new Carte("L'invention du Zeppelin:1900");

		boolean verif = frise.verifierCarteApres(nCarte, 0);
		assertEquals("Le resultat devrait etre faux", false, verif);

		verif = frise.verifierCarteApres(nCarte, 2);
		assertEquals("Le resultat devrait etre vrai", true, verif);
	}

	/**
	 * Test 3.2 verifier carte apres frise sans param.
	 */
	public void test3_2_verifierCarteApres_friseSansParam() {
		Frise frise = new Frise();

		Carte nCarte = new Carte("L'invention du Zeppelin:1900");

		boolean verif = frise.verifierCarteApres(nCarte, 0);
		assertEquals("Le resultat devrait etre vrai", true, verif);
	}

	/**
	 * Test 3.3 verifier carte apres au dessus du tableau.
	 */
	public void test3_3_verifierCarteApres_hors_frise_sup() {
		Frise frise = new Frise();

		Carte nCarte = new Carte("L'invention du Zeppelin:1900");

		boolean verif = frise.verifierCarteApres(nCarte, 10);
		assertEquals("Le resultat devrait etre vrai", true, verif);

		frise.ajouterCarteDebut(nCarte);
		Carte n2Carte = new Carte("L'invention de la vis:-250");

		verif = frise.verifierCarteApres(n2Carte, 0);
		assertEquals("Le resultat devrait etre faux", false, verif);
	}

	/**
	 * Test 3.3 verifier carte apres en dessous du tableau.
	 */
	public void test3_4_verifierCarteApres_hors_frise_inf() {
		Frise frise = new Frise();

		Carte nCarte = new Carte("L'invention du Zeppelin:1900");

		boolean verif = frise.verifierCarteApres(nCarte, -10);
		assertEquals("Le resultat devrait etre vrai", true, verif);

	}

	/**
	 * Test 4.1 inserer carte apres frise param.
	 */
	public void test4_1_insererCarteApres_friseParam() {
		Carte[] tab = new Carte[3];

		tab[0] = new Carte("L'invention du papier:-200");
		tab[1] = new Carte("La fondation du theoreme de Pythagore:-548");
		tab[2] = new Carte("L'apparition de la ceramique:-9000");
		Frise frise = new Frise(tab);

		Carte nCarte = new Carte("L'invention du Zeppelin:1900");
		Carte n2Carte = new Carte("L'invention de la vis:-250");

		frise.insererCarteApres(nCarte, 0);
		frise.insererCarteApres(n2Carte, 1);
		assertEquals("paquet devrait avoir 4 cartes", 4, frise.getNbCartesFrise());

		assertEquals("Le résultat doit être -9000", -9000, frise.getCarte(0).getDate());
		assertEquals("Le résultat doit être -548", -548, frise.getCarte(1).getDate());
		assertEquals("Le résultat doit être -250", -250, frise.getCarte(2).getDate());
		assertEquals("Le résultat doit être -200", -200, frise.getCarte(3).getDate());

	}

	/**
	 * Test 4.2 inserer carte apres frise sans param.
	 */
	public void test4_2_insererCarteApres_friseSansParam() {
		Frise frise = new Frise();

		Carte nCarte = new Carte("L'invention du Zeppelin:1900");
		Carte n2Carte = new Carte("L'invention de la vis:-250");
		Carte n3Carte = new Carte("L'invention du disque compact:1979");

		frise.insererCarteApres(nCarte, 0);
		frise.insererCarteApres(n2Carte, 0);
		frise.insererCarteApres(n3Carte, 0);

		assertEquals("paquet devrait avoir 2 cartes", 2, frise.getNbCartesFrise());

		assertEquals(1900, frise.getCarte(0).getDate());
		assertEquals(1979, frise.getCarte(1).getDate());

	}

	/**
	 * Test 5.1 ajouter carte trie frise param.
	 */
	// .......................................
	public void test5_1_ajouterCarteTrie_friseParam() {
		Carte[] tab = new Carte[3];

		tab[0] = new Carte("L'invention du papier:-200");
		tab[1] = new Carte("La fondation du theoreme de Pythagore:-548");
		tab[2] = new Carte("L'apparition de la ceramique:-9000");
		Frise frise = new Frise(tab);

		Carte nCarte = new Carte("L'invention du Zeppelin:1900");
		Carte n2Carte = new Carte("L'invention de la vis:-250");

		frise.ajouterCarteTrie(nCarte);
		frise.ajouterCarteTrie(n2Carte);
		assertEquals("paquet devrait avoir 5 cartes", 5, frise.getNbCartesFrise());
		assertEquals(-9000, frise.getCarte(0).getDate());
		assertEquals(-548, frise.getCarte(1).getDate());
		assertEquals(-250, frise.getCarte(2).getDate());
		assertEquals(-200, frise.getCarte(3).getDate());
		assertEquals(1900, frise.getCarte(4).getDate());
	}

	/**
	 * Test 5.2 ajouter carte trie frise sans param.
	 */
	public void test5_2_ajouterCarteTrie_friseSansParam() {
		Frise frise = new Frise();

		Carte nCarte = new Carte("L'invention du Zeppelin:1900");
		Carte n2Carte = new Carte("L'invention de la vis:-250");

		frise.ajouterCarteTrie(nCarte);
		frise.ajouterCarteTrie(n2Carte);

		assertEquals("paquet devrait avoir 2 cartes", 2, frise.getNbCartesFrise());
		assertEquals(-250, frise.getCarte(0).getDate());
		assertEquals(1900, frise.getCarte(1).getDate());
	}

	/**
	 * Test 6.1 ajouter carte debut.
	 */
	public void test6_1_ajouterCarteDebut_OK() {
		Frise frise = new Frise();
		Carte nCarte = new Carte("L'invention du Zeppelin:1900");
		Carte n2Carte = new Carte("L'invention de la vis:-250");

		frise.ajouterCarteDebut(nCarte);

		assertEquals("AjouterCarteDebut devrait retourner vrai et le faire", frise.ajouterCarteDebut(n2Carte), true);
		assertEquals("La frise devrait etre de taille 2", 2, frise.getNbCartesFrise());
		assertEquals("la premiere carte devrait avoir pour date -250", -250, frise.getCarte(0).getDate());
		assertEquals("la deuxieme carte devrait avoir pour date 1900", 1900, frise.getCarte(1).getDate());
	}

	/**
	 * Test 6.1 ajouter carte debut dans le mauvais ordre.
	 */
	public void test6_2_ajouterCarteDebut_MauvaisOrdre() {
		Frise frise = new Frise();
		Carte nCarte = new Carte("L'invention du Zeppelin:1900");
		Carte n2Carte = new Carte("L'invention de la vis:-250");

		frise.ajouterCarteDebut(n2Carte);

		assertEquals("AjouterCarteDebut devrait retourner faux et ne rien faire", frise.ajouterCarteDebut(nCarte),
				false);
		assertEquals("La frise devrait etre de taille 1", 1, frise.getNbCartesFrise());
	}

}
