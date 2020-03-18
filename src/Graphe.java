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

public class Graphe {
	private LinkedList<Noeud> noeuds;
	/* This is how to declare HashMap */
	private HashMap<Integer, Noeud> hmap;

	public void parcoursprofR() {
		// Initialisation de mark à False
		for(Noeud node : this.noeuds) node.setMark(false);
		//Pout tout noeud non marké, 
		for(Noeud node : this.noeuds) {
			if ( ! node.isMark()) {
				//lancer le parcours en profondeur prof (
				this.profR(node,"");
			}
		}

	}

	public void profR(Noeud n, String buffer) {
		// marker n
		n.setMark(true);
		//afficher n
		//System.out.println("Mark " +n.getId());
		//this.buffer += n.getId()+"\n"+this.buffer;
		System.out.println(buffer +n.getId());

		//Pour tout successeur non marké, appeler profR(successeur)
		for(Arc arc : n.getSucc()) {
			//System.out.println(" on va de "+n.getId()+"->"+arc.getCible().getId());
			if ( ! arc.getCible().isMark()) {
				this.profR(arc.getCible(),buffer + "--");
			}
		}
	}


	public void parcoursprofI() {
		// Initialisation de mark à False
		for(Noeud node : this.noeuds) node.setMark(false);
		//Pout tout noeud non marké, 
		for(Noeud node : this.noeuds) {
			if ( ! node.isMark()) {
				//lancer le parcours en profondeur prof (
				this.profI(node);
			}
		}

	}
	public void profI(Noeud n) {
		Stack<Noeud> st;
		st = new Stack<Noeud>();
		n.setMark(true);
		st.push(n);
		System.out.println(n.getId());
		Noeud node;
		boolean trouve = true;
		while (! st.isEmpty()) {
			node = st.peek();
			trouve = true;
			for (Arc arc : node.getSucc()) trouve = trouve && arc.getCible().isMark();
			if (trouve) {
				node = st.pop();

			}
			else {
				for (Arc arc : node.getSucc()) {
					if (! arc.getCible().isMark()) {
						arc.getCible().setMark(true);
						st.push(arc.getCible());
						System.out.println(arc.getCible().getId());
					}
				}
			}
		}
	}

	public void parcourslargeur() {
		// Initialisation de mark à False
		for(Noeud node : this.noeuds) node.setMark(false);
		//Pout tout noeud non marké, 
		for(Noeud node : this.noeuds) {
			if ( ! node.isMark()) {
				//lancer le parcours en profondeur prof (
				this.largeur(node);
			}
		}

	}

	public void largeur(Noeud n) {
		//création file
		LinkedList<Noeud> file;
		file = new LinkedList<Noeud>();
		n.setMark(true);
		file.addFirst(n);
		System.out.println(n.getId());
		Noeud node;
		while (! file.isEmpty()) {
			node = file.getLast();
			file.removeLast();
			for (Arc arc : node.getSucc()) {
				if (! arc.getCible().isMark()) {
					arc.getCible().setMark(true);
					file.addFirst(arc.getCible());
					System.out.println(arc.getCible().getId());
				}
			}
		}
	}

	public void addNoeud(Noeud n) {
		// recherche si l'id apparait dans un Noeud de la liste
		// Ajoute le Noeud à la liste sinon
		/*		if (! (this.getNoeuds().contains(n))) {
			this.getNoeuds().add(n); 
			this.getHmap().put(n.getId(),n);
		}*/
		if ( this.getHmap().get(n.getId())==null) {
			this.getNoeuds().add(n); 
			this.getHmap().put(n.getId(),n);
		}
	}

	public void addNoeud(int n) {
		/*		boolean trouve = false;

		// recherche si un Noeud n apparait dans la liste
		for(Noeud e : this.getNoeuds()) if (e.getId()==n) trouve = true;

		// l'ajoute Sinon
		if (! trouve) {
			Noeud node= new Noeud(n);
			this.getNoeuds().add(node);
			this.getHmap().put(n, node);
		}*/

		if (this.getHmap().get(n)== null) {
			Noeud node= new Noeud(n);
			this.getNoeuds().add(node);
			this.getHmap().put(n, node);
		}

	}

	public void addArc(int x, int y, int c) {
		//Verifie que x est dans le graphe
		// Verifie que y est dans le graphe
		// test si y est déja successeur de x 
		// l'ajoute Sinon
		/*		Noeud noeudx=this.getNoeud(x);
		Noeud noeudy=this.getNoeud(y);
		if ( (noeudx != null) && (noeudy != null)) 
			if ( !(noeudx.hasSuccesseur(y) ) ) 
				new Arc(noeudx,noeudy);*/
		if (this.getHmap().get(x) !=null && this.getHmap().get(y) != null) {
			if ( !(this.getHmap().get(x).hasSuccesseur(y) ) ) 
				new Arc(this.getHmap().get(x),this.getHmap().get(y), c);
		}

	}

	public Noeud getNoeud(int n) {
		//recherche l'élément dans la liste 
		/*				for (int i=0; i < this.getNoeuds().size(); i++) {
					if (this.getNoeuds().get(i).getId()==n)
						return (this.getNoeuds().get(i));
				}
				return null; */
		return(this.getHmap().get(n));
	}

	@Override
	public String toString() {
		/*		Noeud n;
		for (int i=0;i<10;i++) {
			n = this.hmap.get(i+1);
			System.out.println("HMAP" + n);
		}*/

		return "Graphe [noeuds=" + getNoeuds() + "]";
	}

	public Graphe() {
		super();
		setNoeuds(new LinkedList<Noeud>());
		setHmap(new HashMap<Integer, Noeud>());
	}

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

	// Export d�un graphe sous format CSV selon la liste de ses arcs
	// Format Source : Target
	public void export() {
		String buff = "Source,Target\n";
		String sep = ",";
		for (Noeud n : this.noeuds) {
			for (Arc a : n.getSucc()) {
				buff += a.getCible().getId() + sep +
						a.getSource().getId() + "\n";
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

	public LinkedList<Noeud> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(LinkedList<Noeud> noeuds) {
		this.noeuds = noeuds;
	}

	public HashMap<Integer, Noeud> getHmap() {
		return hmap;
	}

	public void setHmap(HashMap<Integer, Noeud> hmap) {
		this.hmap = hmap;
	}

}