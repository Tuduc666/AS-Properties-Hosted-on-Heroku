package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Showing;
import utils.OracleQueries;

public class ShowingDAO {

	public Showing getShowing(Integer  user_id, Integer  property_id) throws IOException, SQLException {
		Showing showing = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		conn = GetConnection.Connect();
		stmt = conn.prepareStatement(OracleQueries.GETSHOWING);
		stmt.setInt(1, user_id);
		stmt.setInt(2, property_id);
		result = stmt.executeQuery();
		if (result.next()) {
			showing = new Showing();
			showing.setUser_id(result.getInt(1));
			showing.setProperty_id(result.getInt(2));
			showing.setUser_message(result.getString(3));
		}

		if (result != null) {
			result.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return showing;
	}

	public Boolean addShowing(Integer  user_id, Integer  property_id, String user_message)
			throws IOException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer result = null;

		conn = GetConnection.Connect();
		stmt = conn.prepareStatement(OracleQueries.ADDSHOWING);
		stmt.setInt(1, user_id);
		stmt.setInt(2, property_id);
		stmt.setString(3, user_message);
		result = stmt.executeUpdate();

		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return result > 0;
	}

	public Boolean updateShowing(Integer  user_id, Integer  property_id, String user_message)
			throws IOException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer result = null;

		conn = GetConnection.Connect();
		stmt = conn.prepareStatement(OracleQueries.UPDATESHOWING);
		stmt.setInt(1, user_id);
		stmt.setInt(2, property_id);
		stmt.setString(3, user_message);
		stmt.setInt(4, user_id);
		stmt.setInt(5, property_id);
		result = stmt.executeUpdate();

		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return result > 0;
	}

	public boolean deleteShowing(Integer user_id, Integer property_id) throws IOException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer result = null;

		conn = GetConnection.Connect();
		stmt = conn.prepareStatement(OracleQueries.DELETESHOWING);
		stmt.setInt(1, user_id);
		stmt.setInt(2, property_id);
		result = stmt.executeUpdate();

		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return result > 0;
	}
}