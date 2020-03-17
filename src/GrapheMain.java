import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

public class GrapheMain {

    public static void main(String[] args) {
        /*
        RandomGraphe rg = new RandomGraphe(7,14);
        System.out.println(rg);
        rg.export();/

        /
        Graphe G = new Graphe();
        for(int i=1;i<7;i++) {
            G.addNoeud(i);
        }
        G.addArc(1, 5, 1);
        G.addArc(1, 6, 2);
        G.addArc(6, 5, 3);
        G.addArc(2, 5, 3);
        G.addArc(5, 4, 2);
        G.addArc(2, 3, 4);
        G.addArc(4, 3, 3);
        G.addArc(6, 4, 3);
        G.addArc(5, 3, 1);
        G.addArc(1, 2, 2);*/

    	
//		Graphe qui ne marche : bug liason
//        Graphe G = new Graphe();
//        for(int i=1;i<7;i++) {
//            G.addNoeud(i);
//        }
//        G.addArc(1, 2, 1);
//        G.addArc(1, 3, 3);
//        G.addArc(1, 6, 1);
//        G.addArc(2, 3, 1);
//        G.addArc(2, 5, 2);
//        G.addArc(2, 1, 4);
//       
       
       //Graphe qui marche 
//    	Graphe G = new Graphe();
//        for(int i=1;i<5;i++) {
//            G.addNoeud(i);
//        }
//        G.addArc(1, 2, 4);
//        G.addArc(1, 3, 3);
//        G.addArc(1, 4, 2);
//        G.addArc(3, 4, 2);
//        G.addArc(3, 2, 1);
//        G.addArc(4, 2, 1);
    	
    	
    	Graphe G = new Graphe();
        for(int i=1;i<7;i++) {
            G.addNoeud(i);
        }
        G.addArc(1, 5, 1);
        G.addArc(1, 6, 2);
        G.addArc(6, 5, 3);
        G.addArc(2, 5, 3);
        G.addArc(5, 4, 2);
        G.addArc(2, 3, 4);
        G.addArc(4, 3, 3);
        G.addArc(6, 4, 3);
        G.addArc(5, 3, 1);
        G.addArc(1, 2, 2);
    	
        //System.out.println(G);
        //G.export();
       testGraphe GT = new testGraphe(G);
        GT.testConnexe();
        G.export();
        

        
//        analyse.analyserLiaisons(1,0,"1",analyse.getArcs(),1);
//        analyse.Afficher();
    }

}