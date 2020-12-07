package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerDAO {

	private String dbURL = "jdbc:mysql://localhost:3306/aeroparker";
	private String dbusername = "root";
	private String password = "";
	private String dbdriver = "com.mysql.jdbc.Driver";

	public void loadDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL, dbusername, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public boolean insert(Customer customer, String url) {
		loadDriver(dbdriver);
		Connection con = getConnnection();
		String sql = "INSERT INTO `customers`(`email`, `title`, `firstName`, `lastName`, `addressLine1`, `addressLine2`, `city`, `postCode`, `telephone`) VALUES (?,?,?,?,?,?,?,?,?)";
		String sql2 = "INSERT INTO `customer_sites`(`customer_id`, `site_id`) VALUES (?,?)";
		boolean inserted = true;
		int site_id, customer_id;
		if (url.equals("Avalon City")) {
			site_id = 1;
		} else {
			site_id = 2;
		}
		try {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getEmail());
			ps.setString(2, customer.getTitle());
			ps.setString(3, customer.getFirstName());
			ps.setString(4, customer.getLastName());
			ps.setString(5, customer.getAddressLine1());
			ps.setString(6, customer.getAddressLine2());
			ps.setString(7, customer.getCity());
			ps.setString(8, customer.getPostCode());
			ps.setString(9, customer.getTelephone());
			int key = ps.executeUpdate();

			if (key != 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						customer_id = generatedKeys.getInt(1);
					} else {
						throw new SQLException("Creating customer failed, no ID obtained.");
					}
				}

				ps.clearParameters();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, customer_id);
				ps.setInt(2, site_id);
				ps.executeUpdate();
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			inserted = false;
		}

		return inserted;
	}

	public ArrayList<String> readAllEmails() {
		ArrayList<String> emails = new ArrayList<String>();
		String sql = "select email from customers";
		loadDriver(dbdriver);
		Connection con = getConnnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				emails.add(rs.getString("email"));
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emails;
	}
}
