package test;

import entities.Vehicule;
import service.VehiculeServices;

public class TestVehicule {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VehiculeServices VS = new VehiculeServices();
		VS.create(new Vehicule("3541dq","ford","voiture"));
		VS.create(new Vehicule("7754s","bmw","voiture"));
		VS.create(new Vehicule("1h2547","megane","voiture"));
		VS.create(new Vehicule("6d97784","ford","voiture"));
		for (Vehicule v : VS.findAll()) {
			System.out.println(v.toString());
		}
	}

}
