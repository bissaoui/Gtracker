package entities;

public class Tracker {

	private int id;
	private String simNumber;
	private static int cmp=0;

	public Tracker(String simNumber) {
		
		this.id = ++cmp;
		this.simNumber = simNumber;
	}
	public Tracker(int id, String simNumber) {
		this.id = id;
		this.simNumber = simNumber;
	}

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return  simNumber ;
	}
	
	
}
