package test;

import java.sql.SQLException;
import java.sql.Statement;

import connexion.Connexion;

public class TestConnection {

	public static void main(String[] args) {
		Connexion.getConnection();
		try {
			String TVehicule = "create table vehicule (" + "id int primary key auto_increment,"
					+ "matricule varchar(30)," + "marque varchar(30)," + "type varchar(30)" + ");";

			String TTracker = "create table tracker (" + "id int primary key auto_increment,"
					+ "simNumber varchar(30));";
			String TPosition = "create table position (" + "id int primary key auto_increment," + "latitude double,"
					+ "longitude double," + "date date, " + "idTracker int);";
			String TVehiculeTracker = "create table VehiculeTracker (" + "id int primary key auto_increment,"
					+ "idTracker int , " + "idVehicule int ," + "dateDebut Date," + "dateFin Date);";
			String FkTracker_Position = "Alter table position add constraint FkTracker_Position foreign key (idTracker) "
					+ "references tracker (id);";

			String FkVehicule_VehiculeTracker = "Alter table VehiculeTracker add"
					+ " constraint FkVehicule_VehiculeTracker foreign key (idVehicule) " + "references vehicule (id);";
			String FkTracker_VehiculeTracker = "Alter table VehiculeTracker add constraint FkTracker_VehiculeTracker foreign key (idTracker) "
					+ "references tracker (id);";
			String ConstraintUnique = "alter table VehiculeTracker "
					+ "add constraint UC_VehiculeTracker UNIQUE (idTracker,idVehicule,dateDebut);";
			Statement st = Connexion.getConnection().createStatement();

//  Verify if exist tables  

			st.execute("drop table if exists position");
			st.execute("drop table if exists VehiculeTracker");
			st.execute("drop table if exists tracker");
			st.execute("drop table if exists vehicule");

//  Create Tables 

			st.executeUpdate(TTracker);
			st.executeUpdate(TVehicule);
			st.executeUpdate(TPosition);
			st.executeUpdate(TVehiculeTracker);

// Add Constraints 

			st.executeUpdate(FkTracker_Position);
			st.executeUpdate(FkVehicule_VehiculeTracker);
			st.executeUpdate(FkTracker_VehiculeTracker);
			st.executeUpdate(ConstraintUnique);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
