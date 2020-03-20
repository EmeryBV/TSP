
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Classe de test JUnit.
 * Tests des constructeurs des classes RandomGraphe, AnalyseOptimale et AnalyseGloutonne.
 */
class AnalyseTest {

	/**
	 * Test du constructeur RandomGraphe(int n).
	 * Vérifie que les nombres de noeuds et d'arc sont égaux au nombres attendus pour un n positif (4 et 12 avec n=4).
	 * Vérifie que les nombres de noeuds et d'arc pour un n négatif ou nul sont 0 et que le graphe n'est pas null.
	 */
	@Test
	void testRandomGraphe() {

		RandomGraphe G1 = new RandomGraphe(4);
		RandomGraphe G2 = new RandomGraphe(-6);
		RandomGraphe G3 = new RandomGraphe(0);

		assertSame("4 expected", 4, G1.getNbrNoeud());
		assertSame("12 expected", 12, G1.getNbrArc());

		assertSame("0 expected", 0, G2.getNbrNoeud());
		assertSame("0 expected", 0, G2.getNbrArc());
		assertNotNull(G2);

		assertSame("0 expected", 0, G3.getNbrNoeud());
		assertSame("0 expected", 0, G3.getNbrArc());
		assertNotNull(G3);		
	}

	/**
	 * Test du constructeur public AnalyseOptimale(Graphe G, int noeud).
	 * Création d'un graphe à 6 noeuds avec int noeudDepart=1.
	 * Vérifie que les chemins obtenus sont équivalents à ceux attendus.
	 */
	@Test
	void testAnalyseOptimale() {

		Graphe G = new Graphe();
		for(int i=1;i<7;i++) {
			G.addNoeud(i);
		}
		G.addArc(1, 5, 5);
		G.addArc(1, 6, 2);
		G.addArc(6, 5, 3);
		G.addArc(2, 5, 3);
		G.addArc(5, 4, 2);
		G.addArc(2, 3, 4);
		G.addArc(4, 3, 3);   
		G.addArc(6, 4, 3);
		G.addArc(5, 3, 1);
		G.addArc(1, 2, 2);

		int noeudDepart = G.getNoeuds().get(0).getId();
		AnalyseOptimale optimale = new AnalyseOptimale(G, noeudDepart);


		ArrayList<String> cheminsPrevus = new ArrayList<String>();
		cheminsPrevus.add("1->5->2->3->4->6->1");
		cheminsPrevus.add("1->5->6->4->3->2->1");
		cheminsPrevus.add("1->6->5->4->3->2->1");
		cheminsPrevus.add("1->6->4->3->2->5->1");
		cheminsPrevus.add("1->6->4->3->5->2->1");
		cheminsPrevus.add("1->6->4->5->3->2->1");
		cheminsPrevus.add("1->2->5->3->4->6->1");		 
		cheminsPrevus.add("1->2->3->4->5->6->1");		  
		cheminsPrevus.add("1->2->3->4->6->5->1");
		cheminsPrevus.add("1->2->3->5->4->6->1");

		ArrayList<String> cheminsObtenus = new ArrayList<String>();

		for(int j =0;j<optimale.getChemins().size();j++)
			cheminsObtenus.add(optimale.getChemins().get(j).getChemin());

		for(int k =0;k<cheminsPrevus.size();k++)
			assertTrue(cheminsObtenus.contains(cheminsPrevus.get(k)));
	}

	/**
	 * Test du constructeur public AnalyseGloutonne(Graphe G,int noeud).
	 * Création d'un graphe complet à 4 noeuds.
	 * Vérifie que les chemins et leurs couts obtenus sont égaux aux chemins et couts prévus, 
	 * avec pour points de départ les noeuds 1 et 2.
	 */
	@Test
	void testAnalyseGloutonne() {

		Graphe G = new Graphe();
		for(int i=1;i<5;i++) {
			G.addNoeud(i);
		}
		G.addArc(1, 2, 4);        
		G.addArc(1, 3, 3);
		G.addArc(1, 4, 2);        
		G.addArc(3, 4, 2);       
		G.addArc(3, 2, 1);      
		G.addArc(4, 2, 1);        

		System.out.println("Point de départ : "+1);
		AnalyseGloutonne gloutonne1 = new AnalyseGloutonne(G, 1); 
		System.out.println(gloutonne1.getCheminResult().getChemin());
		assertEquals("1->4->2->3->1", gloutonne1.getCheminResult().getChemin());
		assertSame(7, gloutonne1.getCheminResult().getCout());

		System.out.println("Point de départ : "+2);
		AnalyseGloutonne gloutonne2 = new AnalyseGloutonne(G, 2); 
		assertEquals("2->3->4->1->2", gloutonne2.getCheminResult().getChemin());
		assertSame(9, gloutonne2.getCheminResult().getCout());
	}

}