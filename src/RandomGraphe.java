import java.util.ArrayList;

public class RandomGraphe extends Graphe {

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
				this.addArc(v,w,1);
			}
		}
	}

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
				this.addArc(data[r][1],data[r][2],1);
				listeR.add(r);
				cond++;
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
		}
		//afficherTableau2D(data);
		return data;
	}

	public static int fact (int n) {
		int f = 1;
		for (int i=1; i<=n; i++)
			f=f*i;
		return(f);
	}

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
