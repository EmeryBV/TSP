import java.util.ArrayList;
import java.util.Map.Entry;

public class AnalyseOptimale {

	private ArrayList<int[]> arcs = new ArrayList();
	private int totalNoeud;
	private int noeudDepart = 0;
	private ArrayList<Chemin> chemins = new ArrayList();
	
	public AnalyseOptimale(Graphe G, int noeud) {
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
		this.noeudDepart = noeud;
		analyserLiaisons(noeudDepart,0,"",arcs,1,0);
		//this.afficherTousLesChemins();
		this.afficherCheminsMoinsCouteux();
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
	
	public void afficherTousLesChemins() {
		for (Chemin c: chemins) {
			System.out.println(c.toString());
		}
	}
	
	private void afficherCheminsMoinsCouteux() {
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

	public ArrayList<Chemin> getChemins() {
		return chemins;
	}

	public void setChemins(ArrayList<Chemin> chemins) {
		this.chemins = chemins;
	}
}
