package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Customer;
import connection.ConnectionPool;
import exceptions.CouponSystemException;

public class CustomersDbDao implements CustomersDao {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExistsByEmailAndPassword(String email, String password) throws CouponSystemException {
		boolean isExists = false;
		String sql = "select * from customers where email = ? and password = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("is customer exists by email and password failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return isExists;
	}

	@Override
	public boolean isCustomerExistsByEmail(String email) throws CouponSystemException {
		boolean isExists = false;
		String sql = "select * from customers where email = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("is customer exists by email failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

		return isExists;
	}

	@Override
	public boolean isCustomerExistsByEmailAndNotId(String email, int id) throws CouponSystemException {
		boolean isExists = false;
		String sql = "select * from customers where email = ? and id != ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("is customer exists by email and not id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

		return isExists;
	}

	@Override
	public boolean isCustomerExistsById(int id) throws CouponSystemException {
		boolean isExists = false;
		String sql = "select * from customers where id = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Is customer existsby id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

		return isExists;
	}

	@Override
	public void addCustomer(Customer customer) throws CouponSystemException {
		String sql = "insert into customers values (0, ?, ?, ?, ?)";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Add customer failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException {
		String sql = "update customers set first_name=?, last_name=?, email=?, password=? where id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.setInt(5, customer.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("Update customer failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCustomer(int customerId) throws CouponSystemException {
		String sql = "delete from customers where id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Delete customer failed");

		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public List<Customer> getAllCustomers() throws CouponSystemException {
		List<Customer> allCustomers = new ArrayList<>();
		String sql = "select * from customers";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setEmail(rs.getString(4));
				customer.setPassword(rs.getString(5));
				allCustomers.add(customer);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get all customers failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

		return allCustomers;
	}

	@Override
	public Customer getOneCustomerById(int customerId) throws CouponSystemException {
		Customer customer = new Customer();
		String sql = "select * from customers where id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				customer.setId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setEmail(rs.getString(4));
				customer.setPassword(rs.getString(5));
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get one customer by id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

		return customer;
	}

	@Override
	public Customer getOneCustomerByEmail(String email) throws CouponSystemException {
		Customer customer = new Customer();
		String sql = "select * from customers where email=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				customer.setId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setEmail(rs.getString(4));
				customer.setPassword(rs.getString(5));
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get one customer by email failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

		return customer;
	}

}
