package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connexion.Connexion;
import dao.IDao;
import entities.Vehicule;

public class VehiculeServices implements IDao<Vehicule> {
	@Override
	public boolean create(Vehicule o) {
		try {
			String req = "Insert into vehicule values (null,?,?,?);";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getMatricule());
			pr.setString(2, o.getMarque());
			pr.setString(3, o.getType());
			if (pr.executeUpdate() != 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Vehicule o) {
		try {
			String req = "Update vehicule set matricule = ? , marque = ? , type = ? where id = ? ";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getMatricule());
			pr.setString(2, o.getMarque());
			pr.setString(3, o.getType());
			pr.setInt(4, o.getId());
			if (pr.executeUpdate() != 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Vehicule o) {
		try {
			String req = "Delete from vehicule  where id = ? ";
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
	public Vehicule findById(int id) {
		Vehicule v = null;
		try {
			String req = "Select * from vehicule  where id = ? ";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next())
				v = new Vehicule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}

	@Override
	public List<Vehicule> findAll() {
		List<Vehicule> vs = new ArrayList<Vehicule>();
		try {
			String req = "Select * from vehicule ";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next())
				vs.add(new Vehicule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vs;
	}

        public List<Vehicule> findDispo() {
		List<Vehicule> vs = new ArrayList<Vehicule>();
		try {
			String req = "SELECT * from vehicule v where ID NOT IN (SELECT DISTINCT v.id FROM vehiculetracker vt,vehicule v where v.id=vt.idVehicule AND `dateFin` is null)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next())
				vs.add(new Vehicule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vs;
	}
}
