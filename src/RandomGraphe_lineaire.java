
import java.util.ArrayList;
import java.util.LinkedList;

public class RandomGraphe_lineaire extends Graphe {

	/**
	 * 
	 * @param n nombre de noeuds
	 * @param m	 nombre d'arcs
	 */
	public RandomGraphe_lineaire(int n, int m) {
		int replace[] = new int[(n*(n-1))/2];
		for(int i=0;i<(n*(n-1))/2;i++) {
			replace[i]=i;
		}
		for(int i=0;i<n;i++) {
			this.addNoeud(new Noeud(i));
			System.out.println("i="+i);
		}
		int[][]data=bijection(n);
		for(int i=0;i<m;i++) {
			int r = i + (int)(Math.random() * (((n*(n-1))/2-1 - i) + 1));
			//int[] tab2 = tab.get(r);
			boolean RisAlreadyCreated=false;
			//if(this.getNoeud(tab2[0]).getSucc()!=null) {
				LinkedList<Arc> arc =this.getNoeud(data[r][1]).getSucc();
				RisAlreadyCreated=false;
				for(Arc a : arc) {
					if(a.getCible().getId()==data[r][2]) {
						RisAlreadyCreated=true;
					}
				}
			
			if(!RisAlreadyCreated) {
				System.out.println("r = perlimpinpin "+r+"  ["+data[r][1]+"],["+data[r][2]+"]");
				this.addArc(data[r][1], data[r][2], 1);
			}
			else {
				int indice=replace[r];
				System.out.println("r = "+r+"  ["+data[indice][1]+"],["+data[indice][2]+"]");
				this.addArc(data[indice][1], data[indice][2], 1);
			}
			
			///////////////////////////////////////////////////////////////////
			//tab2 = tab.get(replace[i]);
			 arc =this.getNoeud(data[replace[i]][1]).getSucc();
			boolean IisAlreadyCreated=false;
			for(Arc a : arc) {
				if(a.getCible().getId()==data[replace[i]][2]) {
					IisAlreadyCreated=true;
				}
			}
			if(IisAlreadyCreated) {
				replace[r]=i;
			}
			else {
				replace[r]=replace[i];
			}
		}
	}
	
	private int [][] bijection(int i) {
        int totalArcs = i*(i-1)/2;
        int[][] data = new int[totalArcs][3];
        for (int k=0; k<totalArcs; k++) {
            int racine = (int) (Math.sqrt((2*k)+0.25) - 0.5);
            int v = 1 + racine;
            int w = k - (v*(v-1))/2;
            data[k][0] = k;
            data[k][1] = v;
            data[k][2] = w;
            System.out.println("["+k+"],["+v+"],["+w+"]");
        }
        //afficherTableau2D(data);
        return data;
    }
}
