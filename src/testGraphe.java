import java.util.LinkedList;

public class testGraphe {

	private Graphe G ;

	public testGraphe(Graphe G) {
		this.G=G;
	}

	public boolean testConnexe(){

		if(G.getNbrArc()<G.getNbrNoeud()-1) {
			System.err.println("Il faut au moins " + (G.getNbrNoeud()-1) + " arcs pour que TSP soit valable ");
			return false;
		}
		return liaison();
	}

	public boolean liaison() {
		for(Noeud noeud : G.getNoeuds()) {
			LinkedList<Arc> listSucc = noeud.getSucc();
			boolean test = false ; 
			if(noeud.getSucc().size()>=2) {
				int nbrSucc = listSucc.size();
				for(int i = 0 ;  i<listSucc.size();i++) {
					if(listSucc.get(i).getCible().getSucc().isEmpty()) {

						for(Noeud noeud2 : G.getNoeuds()) {
							if(noeud2==noeud);
							else if(noeud2.hasSuccesseur(listSucc.get(i).getCible().getId())) {
								
								nbrSucc--;
								if(nbrSucc==1) {
									test=true ;
								break;
								}
							}
						}

						if(test==false ) {
							System.err.println("Erreur de liason, TSP ne peut pas être appliquer sans passer deux fois par le même noeud");
							return false;
						}
					}
				}

			}
		}
		return true;
	}
}
