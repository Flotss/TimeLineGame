import static libtest.Lanceur.lancer;
import static libtest.OutilTest.assertEquals;
import libtest.*;

/**
 * classe de test qui permet de verifier que la classe Carte
 * fonctionne correctement
 */
public class TestCarte {

	/**
	 * methode de lancement des tests
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		lancer(new TestCarte(), args);
	}

	/**
	 * Test carte methode ok.
	 */
// #######################################
	// ## tests a ecrire
	// #######################################
	public void testCarte_methodeOK() {
		Carte carte = new Carte("L'apparition de la ceramique:-9000");
		carte.toString();
		carte.retournerCarte();
		carte.getDate();
		carte.getEvenement();
		carte.getRecto();
	}

	/**
	 * Test carte constructeur ok.
	 */
	public void testCarte_ConstructeurOK() {
		Carte carte = new Carte("L'apparition de la ceramique:-9000");
		assertEquals("La carte n'a pas le bon evenement", "L'apparition de la ceramique", carte.getEvenement());
		assertEquals("La carte n'a pas la bonne date", -9000, carte.getDate());
		assertEquals("La carte doit être initialise a faux", false, carte.getRecto());
	}

	/**
	 * Test carte 01 retourner carte.
	 */
	public void testCarte01_retournerCarte() {
		Carte carte = new Carte("L'apparition de la ceramique:-9000");
		carte.retournerCarte();
		assertEquals("La carte doit être a vrai", true, carte.getRecto());
	}

	/**
	 * Test carte 02 retourner carte 2 fois.
	 */
	public void testCarte02_retournerCarte2fois() {
		Carte carte = new Carte("L'apparition de la ceramique:-9000");
		carte.retournerCarte();
		assertEquals("La carte doit être a vrai", true, carte.getRecto());
		carte.retournerCarte();
		assertEquals("La carte doit être a vrai", false, carte.getRecto());
	}

	/**
	 * Test carte 03 tostring ok.
	 */
	public void testCarte03_toStringOK() {
		Carte carte = new Carte("L'apparition de la ceramique:-9000");
		assertEquals("La carte doit être a vrai", "??? -> L'apparition de la ceramique", carte.toString());
		carte.retournerCarte();
		assertEquals("La carte doit être a vrai", "-9000 -> L'apparition de la ceramique", carte.toString());
	}

	/**
	 * Test carte 04 getdate.
	 */
	public void testCarte04_getDate() {
		Carte carte = new Carte("L'apparition de la ceramique:-9000");
		assertEquals("La carte doit retourner la bonne date", -9000, carte.getDate());
	}

	/**
	 * Test carte 05 getevenement.
	 */
	public void testCarte05_getEvenement() {
		Carte carte = new Carte("L'apparition de la ceramique:-9000");
		assertEquals("La carte doit retourner l'évènement", "L'apparition de la ceramique", carte.getEvenement());
	}

	/**
	 * Test carte 06 getrecto.
	 */
	public void testCarte06_getRecto() {
		Carte carte = new Carte("L'apparition de la ceramique:-9000");
		assertEquals("La carte doit retourner si la carte est au recto", false, carte.getRecto());

	}


	// .......................................
	// .......................................
	// .......................................

}
