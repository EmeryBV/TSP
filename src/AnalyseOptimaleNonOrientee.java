import java.util.ArrayList;
import java.util.Map.Entry;

public class AnalyseOptimaleNonOrientee {

	private ArrayList<int[]> arcs = new ArrayList();
	private int totalNoeud;
	private int noeudParcouru = 1;
	private ArrayList<Chemin> chemins = new ArrayList();
	
	public AnalyseOptimaleNonOrientee(Graphe G) {
		int n1;
		int n2;
		int cout;
		totalNoeud =  G.getHmap().size();
		for (Entry<Integer, Noeud> entrees : G.getHmap().entrySet()) {
            n1 = entrees.getValue().getId();
            for (Arc succ: entrees.getValue().getSucc()) {
            	n2 = succ.getCible().getId();
            	cout = succ.getCout();
            	int[] arc = new int[3];
            	arc[0] = n1;
            	arc[1] = n2;
            	arc[2] = cout;
            	arcs.add(arc);
            	System.out.println("N1: "+arc[0]+", N2: "+arc[1]+", cout: "+arc[2]);
            }
        }
	}
	
	public void analyserLiaisons(int noeud,int totalCout,String chemin, ArrayList<int[]> a, int noeudParcourus) {
		System.out.println("Noeud courent: "+noeud);
		for (int[] arc: a) {
			System.out.println("N1: "+arc[0]+", N2: "+arc[1]+", cout: "+arc[2]);
		}
		noeudParcourus++;
		for (int i=0; i<a.size();i++) {
			if (a.get(i)[0] == noeud) {
				System.out.println(noeud+" -> "+a.get(i)[1]);
				System.out.println("Noeuds parcourus: "+noeudParcourus);
				totalCout+=a.get(i)[2];
				chemin+= "->"+a.get(i)[1];
				ArrayList<int[]> clone = new ArrayList();
				for(int[] tab: a) {
					if (tab[0] != noeud && tab[1] != noeud) {
						clone.add(tab);
					}
				}
				if (!clone.isEmpty()) {
					analyserLiaisons(a.get(i)[1] ,totalCout,chemin,clone,noeudParcourus);					
				}
			}
			else if (a.get(i)[1] == noeud) {
				System.out.println(noeud+" -> "+a.get(i)[0]);
				System.out.println("Noeuds parcourus: "+noeudParcourus);
				totalCout += a.get(i)[2];
				chemin += "->"+a.get(i)[0];
				ArrayList<int[]> clone = new ArrayList();
				for(int[] tab: a) {
					if (tab[0] != noeud && tab[1] != noeud) {
						clone.add(tab);
					}
				}
				if (!clone.isEmpty()) {
					analyserLiaisons(a.get(i)[0] ,totalCout,chemin,clone,noeudParcourus);					
				}
			}
			System.out.println("Aucun noeud associé");
		}
		System.out.println("Sors de la boucle FOR, noeud parcourus : "+noeudParcourus);
		if (noeudParcourus == totalNoeud) {
			Chemin c = new Chemin(chemin,totalCout);
			chemins.add(c);
			System.out.println("FINAL: "+chemin);
		}
		noeudParcourus--;
	}
	
	public ArrayList<int[]> getArcs() {
		return arcs;
	}

	public void setArcs(ArrayList<int[]> arcs) {
		this.arcs = arcs;
	}
	
	public void Afficher() {
		for (Chemin c: chemins) {
			System.out.println("Chemin: "+c.getChemin()+", cout total: "+c.getCout());
		}
	}
}
