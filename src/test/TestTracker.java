package test;

import entities.Tracker;
import service.TrackerService;

public class TestTracker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrackerService ts = new TrackerService();

		ts.create(new Tracker("45sd8s666"));
		ts.create(new Tracker("452666"));
		ts.create(new Tracker("547474"));
		ts.create(new Tracker("11d8s666"));

		for (Tracker f : ts.findAll() )
			System.out.println(f.toString());

		
		
	}

}
