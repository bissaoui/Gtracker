package entities;

import java.util.Date;

public class Position {

	private int id;
	public double longitude;
	public double latitude;
	private Date date;
	private Tracker tracker;
	private static int cmp = 0;

	public Position(double longitude, double latitude, Date date, Tracker gps) {
		this.id = ++cmp;
		this.longitude = longitude;
		this.latitude = latitude;
		this.date = date;
		this.tracker = gps;
	}

	public Position(int id, double longitude, double latitude, Date date, Tracker gps) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.date = date;
		this.tracker = gps;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public Tracker getGpsTracker() {
		return tracker;
	}

	public void setTracker(Tracker gpsTracker) {
		this.tracker = gpsTracker;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", date=" + date
				+ ", gpsTracker=" + tracker.toString() + "]";
	}

}
