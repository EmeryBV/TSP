import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * Représente la classe principale dans laquelle le programme va être lancé.
 */
public class GrapheMain {

	/**
	 * Méthode principale du programme TSP.
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		Graphe RG = new Graphe();
        for(int i=0;i<4;i++) {
            RG.addNoeud(i);
        }
        RG.addArc(0, 1, 4);
        RG.addArc(0, 2, 3);
        RG.addArc(0, 3, 2);
        RG.addArc(2, 3, 2);
        RG.addArc(2, 1, 1);
        RG.addArc(3, 1, 1);
        
        //System.out.println(G);
        RG.export();*/
        
		
        RandomGraphe RG = new RandomGraphe(9);       
        //System.out.println(RG);
        RG.export();
        
        /////////////////////// ANALYSE OPTIMALE ///////////////////////
        
        long startTime = System.currentTimeMillis();
        System.out.println("Debut analyse optimale");
        System.out.println("...");
        int noeudDepart = RG.getNoeuds().get(0).getId();
        AnalyseOptimale optimale = new AnalyseOptimale(RG,noeudDepart);
        System.out.println();
        long endTime = System.currentTimeMillis();
        System.out.println("Fin de l'analyse optimale avec un temps d'execution de "+(endTime-startTime)+" ms et "+optimale.getChemins().size()+" chemins explorés");
    	
    	///////////////////////////// FIN //////////////////////////////
        
    	System.out.println();
         
    	/////////////////////// ANALYSE GLOUTONNE ///////////////////////
    	
        startTime = System.currentTimeMillis();
    	System.out.println("Debut analyse gloutonne");
        System.out.println("...");
        ArrayList<Chemin> chemins = new ArrayList();
        for(int i=0;i<RG.getNoeuds().size();i++) {
        	AnalyseGloutonne gloutonne = new AnalyseGloutonne(RG, i);
        	chemins.add(gloutonne.getCheminResult());
        }
        afficherCheminsMoinsCouteux(chemins);
        System.out.println();
        endTime = System.currentTimeMillis();
    	System.out.println("Fin de l'analyse gloutonne avec un temps d'execution de "+(endTime-startTime)+" ms et "+RG.getNoeuds().size()+" chemins explorés");
    	
    	///////////////////////////// FIN //////////////////////////////
	}
	
	/***
	 * Permet d'afficher les chemins avec le cout total le plus bas à partir d'une liste de chemins remplis par l'analyse gloutonne.
	 * @param chemins, liste de chemins
	 */
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
