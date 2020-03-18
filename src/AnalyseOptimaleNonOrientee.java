import java.util.ArrayList;
import java.util.Map.Entry;

public class AnalyseOptimaleNonOrientee {

	private ArrayList<int[]> arcs = new ArrayList();
	private int totalNoeud;
	private int noeudDepart = 0;
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
            }
        }
	}
	
	public void analyse(int noeudDepart) {
		this.noeudDepart = noeudDepart;
		analyserLiaisons(noeudDepart,0,"",arcs,1,0);
		//afficher();
		int coutMin = chemins.get(0).getCout();
		for (Chemin c: chemins) {
			if (c.getCout() < coutMin) coutMin=c.getCout();
		}
		System.out.println("Les chemins les moins couteux, avec un cout total = "+coutMin+", sont:");
		for (Chemin c: chemins) {
			if (coutMin==c.getCout()) {
				System.out.println(c.getChemin());
			}
		}
		
	}
	
	public void analyserLiaisons(int noeud,int totalCout,String chemin, ArrayList<int[]> a, int noeudParcourus,int coutLiaison) {
		chemin+=noeud+"->";
		totalCout+=coutLiaison;
		noeudParcourus++;
		for (int i=0; i<a.size();i++) {
			if (a.get(i)[0] == noeud) {
				ArrayList<int[]> clone = new ArrayList();
				for(int[] tab: a) {
					if (tab[0] != noeud && tab[1] != noeud) {
						clone.add(tab);
					}
				}
				if (!clone.isEmpty()) { 
					analyserLiaisons(a.get(i)[1] ,totalCout,chemin,clone,noeudParcourus,a.get(i)[2]);
				}
				else {
					totalCout+=a.get(i)[2];
					chemin+=a.get(i)[1];
					noeud=a.get(i)[1];
				}
			}
			else if (a.get(i)[1] == noeud) {
				ArrayList<int[]> clone = new ArrayList();
				for(int[] tab: a) {
					if (tab[0] != noeud && tab[1] != noeud) {
						clone.add(tab);
					}
				}
				if (!clone.isEmpty()) {
					analyserLiaisons(a.get(i)[0] ,totalCout,chemin,clone,noeudParcourus,a.get(i)[2]);
				}
				else {
					noeud=a.get(i)[0];
					totalCout+=a.get(i)[2];
					chemin+=a.get(i)[0];
				}
			}
		}
		if (noeudParcourus == totalNoeud) {
			for (int[] arc: arcs) {
				if ((arc[1] == noeud && arc[0] == noeudDepart) || (arc[0] == noeud && arc[1] == noeudDepart)) {
					chemin+="->"+noeudDepart;
					totalCout+=arc[2];
					Chemin c = new Chemin(chemin,totalCout);
					chemins.add(c);
					break;
				}
			}
		}
		noeudParcourus--;
	}
	
	public void afficher() {
		for (Chemin c: chemins) {
			System.out.println("Chemin: "+c.getChemin()+", cout total: "+c.getCout());
		}
	}
}
