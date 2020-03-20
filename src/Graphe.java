import java.util.LinkedList;
import java.util.HashMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
//import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represente la classe de l'objet Graphe.
 */
public class Graphe {

	private LinkedList<Noeud> noeuds;
	private HashMap<Integer, Noeud> hmap;
	private int nbrArc = 0 ; 
	private int nbrNoeud = 0 ;

	/**
	 * Retourne le nombre d'arc.
	 * @return un entier qui permet d'avoir le nombre d'arc
	 */
	public int getNbrArc() {
		return nbrArc;
	}

	/**
	 * Retourne le nombre de noeud.
	 * @return un entier qui permet d'avoir le nombre de noeud
	 */
	public int getNbrNoeud() {
		return nbrNoeud;
	}

	/**
	 * Permet d'ajouter un noeud à un graphe. 
	 * @param n Noeud que l'on souhaite ajouter 
	 */
	public void addNoeud(Noeud n) {
		if ( this.getHmap().get(n.getId())==null) {
			this.getNoeuds().add(n); 
			this.getHmap().put(n.getId(),n);
			nbrNoeud++;
		}
	}

	/**
	 * Permet d'ajouter un noeud à un graphe à l'aide de son id.
	 * @param n, id du noeud
	 */
	public void addNoeud(int n) {
		if (this.getHmap().get(n)== null) {
			Noeud node= new Noeud(n);
			this.getNoeuds().add(node);
			this.getHmap().put(n, node);
			nbrNoeud++;
		}

	}

	/**
	 * Permet d'ajouter un arc.
	 * @param x id du noeud de départ
	 * @param y id du noeud d'arrivé
	 * @param c valeur de l'arc
	 */
	public void addArc(int x, int y, int c) {

		if (this.getHmap().get(x) !=null && this.getHmap().get(y) != null) {
			if ( !(this.getHmap().get(x).hasSuccesseur(y) )&& !(this.getHmap().get(y).hasSuccesseur(x)) )
				new Arc(this.getHmap().get(x),this.getHmap().get(y), c);
			nbrArc++;
		}

	}

	/**
	 * Retourne un noeud à partir de son id.
	 * @param n, id du noeud
	 * @return le noeud
	 */
	public Noeud getNoeud(int n) {

		return(this.getHmap().get(n));
	}


	/**
	 * Permet d'afficher le graphe.
	 * @return une chaine de caractère
	 */
	public String toString() {
		return "Graphe [noeuds=" + getNoeuds() + "]";
	}

	/**
	 * Construteur de graphe.
	 */
	public Graphe() {
		super();
		setNoeuds(new LinkedList<Noeud>());
		setHmap(new HashMap<Integer, Noeud>());
	}

	/**
	 * Constructeur du graphe avec l'insertion de k noeuds.
	 * @param k, nombre de noeud voulu
	 */
	public Graphe(int k) {
		super();
		setNoeuds(new LinkedList<Noeud>());
		setHmap(new HashMap<Integer, Noeud>());

		for (int i=0; i<k; i++) {
			getNoeuds().add(new Noeud(i+1)) ;
			/*Adding elements to HashMap*/
			hmap.put(i+1, getNoeuds().getLast());
		}
	}

	/**
	 * Contructeur du graphe provenant d'un ficher.
	 * @param file, fichier pour construire le graphe
	 * @throws IOException
	 */
	public Graphe(String file) throws IOException {
		super();
		setNoeuds(new LinkedList<Noeud>());
		setHmap(new HashMap<Integer, Noeud>());
		try (
				Reader reader = Files.newBufferedReader(Paths.get(file));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
						.withFirstRecordAsHeader()
						.withIgnoreHeaderCase()
						.withTrim());
				) {
			for (CSVRecord csvRecord : csvParser) {
				// Accessing values by Header names
				String Source = csvRecord.get("noeudSource");
				String Cible = csvRecord.get("NoeudCible");
				String Distance = csvRecord.get("Distance");
				String Comment = csvRecord.get("Comment");
				this.addNoeud(Integer.parseInt(Source));
				this.addNoeud(Integer.parseInt(Cible));
				this.addArc(Integer.parseInt(Source), Integer.parseInt(Cible), Integer.parseInt(Distance));
				/*	                System.out.println("Record No - " + csvRecord.getRecordNumber());
	                System.out.println("---------------");
	                System.out.println("Source : " + Source);
	                System.out.println("Cible : " + Cible);
	                System.out.println("Distance : " + Distance);
	                System.out.println("Comment : " + Comment);
	                System.out.println("---------------\n\n");	*/
			}
		}
	}

	/**
	 * Permet d'exporter le graphe en CSV.
	 */
	public void export() {
		String buff = "Source,Target,valeur\n";
		String sep = ",";
		for (Noeud n : this.noeuds) {
			for (Arc a : n.getSucc()) {
				buff += a.getCible().getId() + sep +
						a.getSource().getId() + sep + a.getCout() + "\n";
			}
		}
		File outputFile = new File(this.getClass() + ".csv");
		FileWriter out;
		try {
			out = new FileWriter(outputFile);
			out.write(buff);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retourne une liste de noeuds.
	 * @return liste de noeuds
	 */
	public LinkedList<Noeud> getNoeuds() {
		return noeuds;
	}

	/**
	 * Permet de modifier la liste de noeuds.
	 * @param noeuds, liste de noeuds
	 */
	public void setNoeuds(LinkedList<Noeud> noeuds) {
		this.noeuds = noeuds;
	}

	/**
	 * Permet de récuperer un hashmap de noeuds.
	 * @return un hashmap de noeud avec une assiociation identifiant/noeud
	 */
	public HashMap<Integer, Noeud> getHmap() {
		return hmap;
	}

	/**
	 * Permet de modifier le hashMap de noeuds.
	 * @param hmap hashMap de noeud a modifier
	 */
	public void setHmap(HashMap<Integer, Noeud> hmap) {
		this.hmap = hmap;
	}

}