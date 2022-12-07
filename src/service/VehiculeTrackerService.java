package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import connexion.Connexion;
import dao.IDao;
import entities.Tracker;
import entities.Vehicule;
import entities.VehiculeTracker;

public class VehiculeTrackerService implements IDao<VehiculeTracker> {

	

	@Override
	public boolean create(VehiculeTracker o) {
		// TODO Auto-generated method stub
		try {
			String req = " Insert into VehiculeTracker values (null,?,?,?,null)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);

			pr.setInt(1, o.getTracker().getId());
			pr.setInt(2, o.getVehicule().getId());
			pr.setDate(3, new java.sql.Date(o.getDateDebut().getTime()));

			if (pr.executeUpdate() != 0)
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return false;
	}

	@Override
	public boolean update(VehiculeTracker o) {
		try {
			String req = "Update VehiculeTracker set idTracker = ? , idvehicule = ? , dateDebut = ? , dateFin = ? where id = ? ";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);

			pr.setInt(1, o.getTracker().getId());
			pr.setInt(2, o.getVehicule().getId());
			pr.setDate(3, new java.sql.Date(o.getDateDebut().getTime()));
			pr.setDate(4, new java.sql.Date(o.getDateFin().getTime()));
			pr.setInt(5, o.getId());

			if (pr.executeUpdate() != 0)
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean delete(VehiculeTracker o) {
		try {
			String req = " Delete from VehiculeTracker where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, o.getId());

			if (pr.executeUpdate() != 0)
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public VehiculeTracker findById(int id) {
                
            VehiculeServices vss = new VehiculeServices();
                TrackerService tss = new TrackerService();
	
                VehiculeTracker vt = null;

		try {
			String req = " select * from VehiculeTracker  where id = ? ";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);

			pr.setInt(1, id);

			ResultSet rs = pr.executeQuery();
			if (rs.next())
				vt = new VehiculeTracker(rs.getInt("id"),rs.getDate("dateDebut"), rs.getDate("dateFin"), vss.findById(rs.getInt("idVehicule")),
						tss.findById(rs.getInt("idTracker")));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vt;
	}

	@Override
	public List<VehiculeTracker> findAll() {
		List<VehiculeTracker> ls = new ArrayList<VehiculeTracker>();
		 VehiculeServices vss = new VehiculeServices();
	 TrackerService tss = new TrackerService();
		try {
			String req = " select * from VehiculeTracker  ";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);


			ResultSet rs = pr.executeQuery();
			while (rs.next())
				ls.add( new VehiculeTracker(rs.getInt("id"),rs.getDate("dateDebut"), rs.getDate("dateFin"), vss.findById(rs.getInt("idVehicule")),
						tss.findById(rs.getInt("idTracker"))));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}

	public List<Tracker> findGPSTrackersBetweenDates(Vehicule vehicule, Date dateDebut, Date dateFin) {

		List<Tracker> a = new ArrayList<Tracker>();

		for (VehiculeTracker v : this.findAll()) {
			if (v.getDateDebut().after(dateDebut) && v.getDateFin().before(dateFin)
					&& vehicule.getId() == v.getVehicule().getId()) {
				a.add(v.getTracker());
			}
		}

		return a;
	}

	public boolean testDates(Date firstDate, Date SecondDate) {
            
		long diffInMillies = SecondDate.getTime() - firstDate.getTime();

		if (diffInMillies < 0)
			return true;

		return false;
	}
        
       
}

        
