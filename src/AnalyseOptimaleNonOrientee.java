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
		afficher();
	}
	
	public void analyserLiaisons(int noeud,int totalCout,String chemin, ArrayList<int[]> a, int noeudParcourus,int coutLiaison) {
		chemin+=noeud+"->";
		totalCout+=coutLiaison;
		System.out.println("On cherche les liaisons avec le noeud: "+noeud+", parmis les liaisons suivantes:");
		for (int[] arc: a) {
			System.out.println(arc[0]+" -> "+arc[1]+", cout de la liaison: "+arc[2]);
		}
		System.out.println("Le chemin parcouru est "+chemin);
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
					System.out.println("Trouvé OK");
					analyserLiaisons(a.get(i)[1] ,totalCout,chemin,clone,noeudParcourus,a.get(i)[2]);
				}
				else { 
					System.out.println("Pourquoi !?!?");
					totalCout+=a.get(i)[2];
					chemin+=a.get(i)[1];
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
					System.out.println("Trouvé OK");
					analyserLiaisons(a.get(i)[0] ,totalCout,chemin,clone,noeudParcourus,a.get(i)[2]);
				}
				else { 
					System.out.println("Pourquoi !?!?");
					totalCout+=a.get(i)[2];
					chemin+=a.get(i)[0];
				}
			}
			System.out.println("Liaison "+a.get(i)[0]+" -> "+a.get(i)[1]+" non retenue");
		}
		if (noeudParcourus == totalNoeud) {
			
			Chemin c = new Chemin(chemin,totalCout);
			chemins.add(c);
			System.out.println("Nous sommes sortis de la boucle FOR et tous les noeuds ont été parcourus, chemin final: "+chemin);
		}
		else {
			System.out.println("Nous sommes sortis de la boucle FOR et tous les noeuds n'ont pas été parcourus, seulement "+noeudParcourus+" noeud(s) ont été parcourus");
		}
		noeudParcourus--;
	}
	
	public ArrayList<int[]> getArcs() {
		return arcs;
	}

	public void setArcs(ArrayList<int[]> arcs) {
		this.arcs = arcs;
	}
	
	public void afficher() {
		for (Chemin c: chemins) {
			System.out.println("Chemin: "+c.getChemin()+", cout total: "+c.getCout());
		}
	}
}
