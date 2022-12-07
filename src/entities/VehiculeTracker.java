package entities;

import java.util.Date;

public class VehiculeTracker {

	private int id;
	private Date dateDebut;
	private Date dateFin;
	private Vehicule vehicule;
	private Tracker tracker;
	public static int cmp = 0;

	public VehiculeTracker(Date dateDebut, Date dateFin, Vehicule vehicule, Tracker gpsTracker) {
		id = ++cmp;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.vehicule = vehicule;
		this.tracker = gpsTracker;
	}

	public VehiculeTracker(int id, Date dateDebut, Date dateFin, Vehicule vehicule, Tracker gpsTracker) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.vehicule = vehicule;
		this.tracker = gpsTracker;
	}

	public int getId() {
		return id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker gpsTracker) {
		this.tracker = gpsTracker;
	}

	@Override
	public String toString() {
		return "VehiculeTracker [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", vehicule="
				+ vehicule + ", tracker=" + tracker + "]";
	}

}
