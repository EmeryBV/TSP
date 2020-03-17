
public class testGraphe {

	private Graphe G ;
	
	public testGraphe(Graphe G) {
		this.G=G;
	}
	
	
	
	public void testConnexe(){
		System.out.println(G.getNbrArc() +" " + G.getNbrNoeud());
		if(G.getNbrArc()<G.getNbrNoeud()-1) {
			
			System.err.println("Il faut au moins " + (G.getNbrNoeud()) + " arcs pour que TSP soit valable ");
		}
		
	}
}
