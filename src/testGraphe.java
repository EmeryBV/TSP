
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
		return liason();
	}

	public boolean liason() {
		for(Noeud noeud : G.getNoeuds()) {
			boolean test = false ; 
			if(noeud.getSucc().size()>=2) {
				for(int i = 0 ;  i<noeud.getSucc().size();i++) {
					//					System.out.println("Noeud cible = " + noeud.getSucc().get(i).getCible().getSucc());
					if(String.valueOf(noeud.getSucc().get(i).getCible().getSucc())=="[]") {

						for(Noeud noeud2 : G.getNoeuds()) {
							if(noeud2==noeud);
							else if(noeud2.hasSuccesseur(noeud.getSucc().get(i).getCible().getId())) {
								test=true ;
								break;
							}
						}

						if(test==false ) {
							System.out.println("Erreur de liason, TSP ne peut pas être appliquer sans passer deux fois par le même noeud");
							return false;
						}
					}
				}

			}
		}
		return true;
	}
}
