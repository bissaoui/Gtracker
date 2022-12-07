package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connexion.Connexion;
import dao.IDao;
import entities.Tracker;

public class TrackerService implements IDao<Tracker> {

	@Override
	public boolean create(Tracker o) {

		String req = "insert into tracker values (null, ?);";
		try {
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getSimNumber());
			if (pr.executeUpdate() != 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Tracker o) {
		try {
			String req = "Update tracker set simNumber = ? where id = ? ";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getSimNumber());
			pr.setInt(2, o.getId());
			if (pr.executeUpdate() != 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(Tracker o) {

		try {
			String req = "delete from tracker where id = ? ";
			PreparedStatement pr;
			pr = Connexion.getConnection().prepareStatement(req);
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
	public Tracker findById(int id) {
		Tracker tracker = null;

		try {
			String req = "select * from tracker where id = ? ";
			PreparedStatement pr;
			pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next())
				tracker = new Tracker(rs.getInt("id"), rs.getString("simNumber"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tracker;
	}

	@Override
	public List<Tracker> findAll() {
		List<Tracker> trackers = new ArrayList<Tracker>();
		try {
			String req = "select * from tracker ";
			PreparedStatement pr;
			pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next())
				trackers.add(new Tracker(rs.getInt("id"), rs.getString("simNumber")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trackers;
	}
        public List<Tracker> findDispo() {
		List<Tracker> trackers = new ArrayList<Tracker>();
		try {
			String req = "SELECT * from tracker where ID NOT IN (SELECT DISTINCT v.id FROM vehiculetracker vt,tracker v where v.id=vt.idTracker AND `dateFin` is null)";
			PreparedStatement pr;
			pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next())
				trackers.add(new Tracker(rs.getInt("id"), rs.getString("simNumber")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trackers;
	}
        
        
        	public Tracker findBySim(String s) {
		Tracker t = null;
		try {
			String req = "select * from tracker  where sumNumber = ? ";
			PreparedStatement pr;
			pr = Connexion.getConnection().prepareStatement(req);
                        pr.setString(1, s);

			ResultSet rs = pr.executeQuery();
			while (rs.next())
				t = new Tracker(rs.getInt("id"), rs.getString("simNumber"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

}
