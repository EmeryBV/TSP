/**
 * Représente la classe de l'objet Arc.
 */
public class Arc {

	private Noeud	source;
	private	Noeud	cible;
	private int cout;

	/**
	 * Constructeur de Arc.
	 * @param source, Noeud source
	 * @param cible, Noeud cible
	 * @param cout, cout de l'arc
	 */
	public Arc(Noeud source, Noeud cible, int cout) {
		super();
		this.source = source;
		this.cible = cible;
		this.cout = cout;
		this.source.getSucc().add(this);

	}
	
	/**
	 * Permet d'afficher le noeud source et cible avec le cout de l'arc.
	 */
	@Override
	public String toString() {
		return "Arc [source=" + source.getId() + ", cible=" + cible.getId() + "cout : " + cout + "]";
	}
	
	/**
	 * Retourne le noeud source.
	 * @return un objet noeud 
	 */
	public Noeud getSource() {
		return source;
	}
	
	/**
	 * Modifie le noeud source.
	 * @param source, Noeud source à modifier
	 */
	public void setSource(Noeud source) {
		this.source = source;
	}
	
	/**
	 * Retourne le noeud cible.
	 * @return un objet noeud
	 */
	public Noeud getCible() {
		return cible;
	}
	
	/**
	 * Permet de modifier le noeud cible.
	 * @param cible, Noeud cible à modifier
	 */
	public void setCible(Noeud cible) {
		this.cible = cible;
	}
	
	/**
	 * Retourne le cout de l'arc.
	 * @return un entier 
	 */
	public int getCout() {
		return cout;
	}
	
	/**
	 * Modifie le cout d'un arc.
	 * @param cout, cout de l'arc à modifier
	 */
	public void setCout(int cout) {
		this.cout = cout;
	}

}