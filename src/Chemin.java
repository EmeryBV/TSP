import java.util.ArrayList;

/**
 * Représente la classe de l'objet Chemin.
 */
public class Chemin {

	private String chemin;
	private int cout;
	
	/**
	 * Constructeur de l'objet Chemin.
	 * @param chemin, chaine de caractere contenant le chemin 
	 * @param cout, cout du chemin
	 */
	public Chemin(String chemin, int cout) {
		this.chemin = chemin;
		this.cout = cout;
	}
	
	/**
	 * Récupere le chemin.
	 * @return retourne une chaine de caractère qui contient le chemin
	 */
	public String getChemin() {
		return chemin;
	}
	
	/**
	 * Permet de modifier le chemin.
	 * @param chemin, Chemin à modifier 
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	
	/**
	 * Retourne le cout du chemin.
	 * @return un entier qui contient le cout 
	 */
	public int getCout() {
		return cout;
	}
	
	/**
	 * Permet de modifier le cout.
	 * @param cout, cout à modifier
	 */
	public void setCout(int cout) {
		this.cout = cout;
	}
	
	/**
	 * Permet d'afficher le chemin + chaine de caractère.
	 */
	public String toString() {
		return "Chemin: "+this.getChemin()+", cout total: "+this.getCout();
	}
}
