
public class Arc {
	
	private Noeud	source;
	private	Noeud	cible;
	private int cout;
	
	
	public Arc(Noeud source, Noeud cible, int cout) {
		super();
		this.source = source;
		this.cible = cible;
		this.cout = cout;
		this.source.getSucc().add(this);
		
	}
	
	@Override
	public String toString() {
		return "Arc [source= " + source.getId() + ", cible= " + cible.getId() + ", cout : " + cout + " ]";
	}

	public Noeud getSource() {
		return source;
	}

	public void setSource(Noeud source) {
		this.source = source;
	}

	public Noeud getCible() {
		return cible;
	}

	public void setCible(Noeud cible) {
		this.cible = cible;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}
	
}
