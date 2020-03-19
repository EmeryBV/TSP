
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AnalyseTest {

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
	
	@Test
	void testAnalyseGloutonne() {
		
	}

}
