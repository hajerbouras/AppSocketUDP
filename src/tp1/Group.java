package tp1;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private String titre;
	public List<Etudiant> getLe() {
		return le;
	}

	public void setLe(List<Etudiant> le) {
		this.le = le;
	}

	private List<Etudiant> le ; 

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Group() {
		super();
		le= new ArrayList<Etudiant>();
	}

}
