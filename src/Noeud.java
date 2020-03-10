import java.util.LinkedList;

public class Noeud {
	private	int id;
	private	LinkedList<Arc>   succ;
	private boolean mark;
	

	public Noeud(int id) {
		super();
		this.id = id;
		this.succ = new LinkedList<Arc>();
	}

	
	@Override
	public String toString() {
		String descriptionNoeud = "" ;
		descriptionNoeud = "Noeud [id=" + id +",successeurs :";
		for (Arc arc : succ) 
			descriptionNoeud +="->" + arc.getCible().getId()+ "("+arc.getCout()+")";
		descriptionNoeud += "]";
		return descriptionNoeud;
	}

	@Override
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
	
	public boolean hasSuccesseur(int j) {
		
		for (Arc e : this.succ) if (e.getCible().id==j) return(true); 
		
		return(false);
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LinkedList<Arc> getSucc() {
		return succ;
	}


	public void setSucc(LinkedList<Arc> succ) {
		this.succ = succ;
	}
	
	public boolean isMark() {
		return mark;
	}


	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
}