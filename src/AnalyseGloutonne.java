
import java.util.ArrayList;
import java.util.Map.Entry;

public class AnalyseGloutonne {
	
	private ArrayList<int[]> arcs = new ArrayList();
	private int totalNoeud;
	private int noeudDepart = 0;
	private Chemin cheminResult;
	private String chemin;
	
	public AnalyseGloutonne(Graphe G,int noeud) {
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
		this.chemin="";
		this.noeudDepart = noeud;
		this.algorithme(noeud,0,"",arcs,0,0);
		System.out.println(this.cheminResult);
	}
	
	
	public void algorithme(int noeud,int totalCout,String chemin, ArrayList<int[]> a, int noeudParcourus,int coutLiaison) {
		chemin+=noeud+"->";
		totalCout+=coutLiaison;
		noeudParcourus++;
		int min=-1;
		int indice=-1;
		for (int i=0; i<a.size();i++) {
			ArrayList<int[]> clone = new ArrayList();
			if (a.get(i)[0] == noeud) {
				if(min==-1) {
					min=a.get(i)[2];
					indice=a.get(i)[1];
				}
				else {
					if(a.get(i)[2]<min) {
						min=a.get(i)[2];
						indice=a.get(i)[1];
					}
				}
			}
			else if (a.get(i)[1] == noeud) {
				//ArrayList<int[]> clone = new ArrayList();
				for(int[] tab: a) {
					if (tab[0] != noeud && tab[1] != noeud) {
						clone.add(tab);
					}
				}
				if(min==-1) {
					min=a.get(i)[2];
					indice=a.get(i)[0];
				}
				else {
					if(a.get(i)[2]<min) {
						min=a.get(i)[2];
						indice=a.get(i)[0];
					}
				}
			}
		}
		ArrayList<int[]> clone = new ArrayList();
		for(int[] tab: a) {
			if (tab[0] != noeud && tab[1] != noeud) {
				clone.add(tab);
			}
		}
		if (!clone.isEmpty()) {
			algorithme(indice ,totalCout,chemin,clone,noeudParcourus,min);
		}
		else {
			noeud=indice;
			totalCout+=min;
			chemin+=indice;
			noeudParcourus++;
		}
		if (noeudParcourus == totalNoeud) {
			for (int[] arc: arcs) {
				if ((arc[1] == noeud && arc[0] == noeudDepart) || (arc[0] == noeud && arc[1] == noeudDepart)) {
					chemin+="->"+noeudDepart;
					totalCout+=arc[2];
					this.cheminResult=new Chemin(chemin,totalCout);
					break;
				}
			}
		}
	}


	public Chemin getCheminResult() {
		return cheminResult;
	}


	public void setCheminResult(Chemin cheminResult) {
		this.cheminResult = cheminResult;
	}
	
}
