package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import connexion.Connexion;
import dao.IDao;
import entities.Tracker;
import entities.Position;

public class PositionService implements IDao<Position> {

	private TrackerService ts = new TrackerService();

	@Override
	public boolean create(Position o) {
		try {
			String req = "Insert into position values (null,?,?,?,?);";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setDouble(1, o.getLatitude());
			pr.setDouble(2, o.getLongitude());
			pr.setDate(3, new Date(o.getDate().getTime()));
			pr.setInt(4, o.getGpsTracker().getId());
			if (pr.executeUpdate() != 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Position o) {
		try {
			String req = "Update position set latitude = ? , longitude = ? , date = ? , idTracker = ? where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setDouble(1, o.getLatitude());
			pr.setDouble(2, o.getLongitude());
			pr.setDate(3, new Date(o.getDate().getTime()));
			pr.setInt(4, o.getGpsTracker().getId());
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
	public boolean delete(Position o) {
		try {
			String req = "Delete from position  where id = ?";
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
	public Position findById(int id) {
		Position p = null;
		try {
			String req = "Select * from position  where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next())
				p = new Position(rs.getInt("id"), rs.getDouble("latitude"), rs.getDouble("longitude"),
						rs.getDate("date"), ts.findById(rs.getInt("idTracker")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Position> findAll() {
		List<Position> ps = new ArrayList<Position>();
		try {
			String req = "Select * from position ";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next())
				ps.add(new Position(rs.getInt("id"), rs.getDouble("latitude"), rs.getDouble("longitude"),
						rs.getDate("date"), ts.findById(rs.getInt("idTracker"))));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}

	public List<Position> findPositionsBetweenDates(Tracker tracker, java.util.Date dateDebut, java.util.Date dateFin) {
		List<Position> ps = new ArrayList<Position>();

		for (Position v : this.findAll()) {
			if (v.getDate().after(dateDebut) && v.getDate().before(dateFin)
					&& v.getGpsTracker().getId() == tracker.getId()) {
				ps.add(v);
			}
		}

		return ps;

	}

}
