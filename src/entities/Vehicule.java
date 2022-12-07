package entities;

public class Vehicule {

	private int id;
	private String matricule;
	private String marque ;
	private String type;
	private static int cmp=0;
	
	public Vehicule( String matricule,String marque, String type) {
		this.id=++cmp;
		this.marque=marque;
		this.matricule=matricule;
		this.type=type;
		
	}
	public Vehicule(int id ,String matricule,String marque,  String type) {
		this.id=id;
		this.marque=marque;
		this.matricule=matricule;
		this.type=type;
		
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "matricule=" + matricule ;
	}
	
	
}
