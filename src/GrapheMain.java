import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

public class GrapheMain {

	public static void main(String[] args) {
		
		/*
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
        
        //System.out.println(G);
        G.export();*/
        
        RandomGraphe RG = new RandomGraphe(9);       
        //System.out.println(RG);
        RG.export();
        
        // ANALYSE OPTIMALE //
        
        System.out.println("Debut analyse optimale");
        System.out.println("...");
        int noeudDepart = RG.getNoeuds().get(0).getId();
        AnalyseOptimale optimale = new AnalyseOptimale(RG,noeudDepart);
        System.out.println();
        System.out.println("Fin de l'analyse optimale avec "+optimale.getChemins().size()+" chemins explorés");
    	System.out.println();
    	
    	// ANALYSE GLOUTONNE //
    	
    	System.out.println("Debut analyse gloutonne");
        System.out.println("...");
        ArrayList<Chemin> chemins = new ArrayList();
        for(int i=0;i<RG.getNoeuds().size();i++) {
        	AnalyseGloutonne gloutonne = new AnalyseGloutonne(RG, i);
        	chemins.add(gloutonne.getCheminResult());
        }
        afficherCheminsMoinsCouteux(chemins);
        System.out.println();
    	System.out.println("Fin de l'analyse gloutonne avec "+RG.getNoeuds().size()+" chemins explorés");
    	
	}
	
	private static void afficherCheminsMoinsCouteux(ArrayList<Chemin> chemins) {
		int coutMin = chemins.get(0).getCout();
		for (Chemin c: chemins) {
			if (c.getCout() < coutMin) coutMin=c.getCout();
		}
		System.out.println("Les chemins les moins couteux, avec un cout = "+coutMin+", sont :");
		for (Chemin c: chemins) {
			if (coutMin==c.getCout()) {
				System.out.println(c.toString());
			}
		}
	}

}
