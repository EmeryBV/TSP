import java.util.ArrayList;
import java.util.LinkedList;

/**
 * La classe RendomGraphe nous permet de pouvoir générer des graphes.
 */
public class RandomGraphe extends Graphe {

	/**
	 * Constructeur de la classe RandomGraphe. Permet de générer des graphes aléatoires.
	 * @param n, le nombre de noeud
	 * @param p, probabilité minimale d'acceptation de création d'un arc
	 */
	public RandomGraphe(int n, double p) {
		for(int i=0;i<n;i++) {
			this.addNoeud(i);
		}
		int v=1;
		int w=-1;
		while(v<n) {
			double r= Math.random();
			w=(int) (w+1+Math.abs(Math.log(1-r)/Math.log(1-p)));
			while(w>=v && v<n) {
				w=w-v;
				v=v+1;
			}
			if(v<n) {
				double x = Math.random()*20;
				this.addArc(v,w,(int) x);
			}
		}
	}

	/**
	 * Constructeur de la classe RandomGraphe. Permet de générer des graphes aléatoires en fonction du nombre de noeud et d'arc.
	 * @param n, le nombre de noeud
	 * @param m, le nombre d'arc
	 */
	public RandomGraphe(int n, int m) {
		for(int i=0;i<n;i++) {
			this.addNoeud(i);
		}
		ArrayList<Integer> listeR = new ArrayList();
		int[][] data = bijection(n);
		afficherTableau2D(data);
		int cond = 0;
		while (cond < m) {
			int r = (int)(Math.random() * (((n*(n-1)/2) - 1) + 1));
			if (!listeR.contains(r)) {
				double x = Math.random()*20;
				this.addArc(data[r][1],data[r][2],(int) x);
				listeR.add(r);
				cond++;
			}
		}
	}
	
	/**
	 * Constructeur de la classe RandomGraphe. Permet de générer des graphes complets et pondérés.
	 * @param n, le nombre de noeud
	 */
	public RandomGraphe(int n) {
		for(int i=0;i<n;i++) {
			this.addNoeud(i);
		}
		LinkedList<Noeud> listNoeud =this.getNoeuds() ;
		for ( int i = 0 ; i<listNoeud.size();i++ ) {
			for(int y =0; y<listNoeud.size();y++) {
				if(i!=y ){
					int x = 1 + (int)(Math.random() * ((20 - 1) + 1));
					this.addArc(i,y,(int) x);
				}
			}
		}
	}

	/**
	 * Méthode représentant tous les arcs que l'on peut créer à partir d'un graphe de i noeuds.
	 * @param i, le nombre de noeud du graphe
	 * @return le tableau contenant les arcs et leur indice
	 */
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
		}
		//afficherTableau2D(data);
		return data;
	}

	/**
	 * Méthode permettant d'afficher un tableau à deux dimensions.
	 * @param data, tableau à deux dimensions
	 */
	public void afficherTableau2D(int [][] data) {
		int i = 0, j = 0;
		for(i=0;i<data.length;i++){
			for(j=0;j<data[i].length;j++){
				System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
	}
}
