package collaborateur.web.service.model;

public class Collaborateur {
	
	/* ATTRIBUTS */
	private int Id;
	private String nom;

	/* CONSTRUCTOR */
	
	public Collaborateur(int id, String string) {
		super();
		Id = id;
		this.nom = string;
	}
   /* METHODS */

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
}
