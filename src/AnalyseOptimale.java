import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * Cette classe fait reference à l'analyse optimale.
 */
public class AnalyseOptimale {

	private ArrayList<int[]> arcs = new ArrayList();
	private int totalNoeud;
	private int noeudDepart = 0;
	private ArrayList<Chemin> chemins = new ArrayList();
	
	/**
	 * Constructeur de la classe AnalyseOptimale.
	 * @param G, représente le graphe à analyser
	 * @param noeud, représente le noeud d'où l'analyse va débuter
	 */
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
		algorithme(noeudDepart,0,"",arcs,1,0);
		//this.afficherTousLesChemins();
		this.afficherCheminsMoinsCouteux();
	}
	
	/**
	 * Cette méthode itérative représente l'algorithme de l'analyse optimale. Elle determine tous les chemins 
	 * pouvant être empruntés à partir d'un noeud de depart, qu'elle sauvegarde dans une liste de chemins avec 
	 * leur cout total respectif.
	 * @param noeud
	 * @param totalCout
	 * @param chemin
	 * @param a, la liste d'arcs restants
	 * @param noeudParcourus
	 * @param coutLiaison
	 */
	public void algorithme(int noeud,int totalCout,String chemin, ArrayList<int[]> a, int noeudParcourus,int coutLiaison) {
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
					algorithme(a.get(i)[1] ,totalCout,chemin,clone,noeudParcourus,a.get(i)[2]);
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
					algorithme(a.get(i)[0] ,totalCout,chemin,clone,noeudParcourus,a.get(i)[2]);
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
	
	/**
	 * Méthode qui affiche tous les chemins identifiés.
	 */
	public void afficherTousLesChemins() {
		for (Chemin c: chemins) {
			System.out.println(c.toString());
		}
	}
	
	/**
	 * Méthode qui affiche les chemins avec le cout total le plus bas.
	 */
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

	/**
	 * Représente le getter de l'attribut chemins.
	 * @return l'attribut chemins
	 */
	public ArrayList<Chemin> getChemins() {
		return chemins;
	}

	/**
	 * Représente le setter de l'attribut chemins.
	 * @param chemins
	 */
	public void setChemins(ArrayList<Chemin> chemins) {
		this.chemins = chemins;
	}
}
