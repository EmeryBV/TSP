import java.util.LinkedList;

/**
 * Représente la classe de l'objet Noeud.
 */
public class Noeud {
	private	int id;
	private	LinkedList<Arc>   succ;
	private boolean mark;

	/**
	 * Constructeur de l'objet Noeud.
	 * @param id id du noeud
	 */
	public Noeud(int id) {
		super();
		this.id = id;
		this.succ = new LinkedList<Arc>();
	}

	/**
	 * Permet d'afficher le noeud.
	 * @return une chaine de caractères
	 */
	@Override
	public String toString() {
		String descriptionNoeud = "" ;
		descriptionNoeud = "Noeud [id=" + id +",successeurs :";
		for (Arc arc : succ) 
			descriptionNoeud +="->" + arc.getCible().getId()+ "("+arc.getCout()+")";
		descriptionNoeud += "]";
		return descriptionNoeud;
	}

	/**
	 * Permet de comparer deux objets.
	 * @param objet
	 * return vrai, si les deux objets sont égaux, faux sinon
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noeud other = (Noeud) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * Permet de savoir si un noeud à pour successeur un noeud avec l'id "j".
	 * @param j, id du noeud cible	
	 * @return vrai, si j est un successeur, faux sinon
	 */
	public boolean hasSuccesseur(int j) {

		for (Arc e : this.succ) if (e.getCible().id==j) return(true); 

		return(false);
	}
	
	/**
	 * Retourn l'id du noeud.
	 * @return un id pour l'id du noeud
	 */
	public int getId() {
		return id;
	}

	/**
	 * Permet de modifier l'id d'un noeud.
	 * @param id id du noeud à modifier
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retourne la liste des successeurs d'un noeud.
	 * @return une liste d'arc
	 */
	public LinkedList<Arc> getSucc() {
		return succ;
	}

	/**
	 * Permet de modifier la liste des successeurs.
	 * @param succ, liste des successeurs à modifier
	 */
	public void setSucc(LinkedList<Arc> succ) {
		this.succ = succ;
	}

}