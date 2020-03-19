import java.util.ArrayList;

public class Chemin {

	private String chemin;
	private int cout;
	
	public Chemin(String chemin, int cout) {
		this.chemin = chemin;
		this.cout = cout;
	}
	
	public String getChemin() {
		return chemin;
	}
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	public String toString() {
		return "Chemin: "+this.getChemin()+", cout total: "+this.getCout();
	}
}
