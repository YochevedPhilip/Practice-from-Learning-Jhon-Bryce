package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Company;
import connection.ConnectionPool;
import exceptions.CouponSystemException;

public class CompaniesDbDao implements CompaniesDao {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private int companyId;

	public final int getCompanyId() {
		return companyId;
	}

	public final void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public boolean isCompanyExistByEmailAndPassword(String email, String password) throws CouponSystemException {
		boolean isExists = false;
		String sql = "SELECT * FROM companies where email=? and password=?";
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
			throw new CouponSystemException("Is company exsists by email and password failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return isExists;

	}

	@Override
	public boolean isCompanyExistByName(String name) throws CouponSystemException {
		boolean isExists = false;
		String sql = "SELECT * FROM companies where name=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Is company exsists by name failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return isExists;

	}

	@Override
	public boolean isCompanyExistByEmail(String email) throws CouponSystemException {
		boolean isExists = false;
		String sql = "SELECT * FROM companies where email=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Is company exsists by email failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return isExists;

	}

	@Override
	public boolean isCompanyExistById(int id) throws CouponSystemException {
		boolean isExists = false;
		String sql = "SELECT * FROM companies where id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Is company exsists by email failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return isExists;

	}

	@Override
	public void addCompany(Company company) throws CouponSystemException {
		String sql = "insert into companies values(0, ?, ?, ?)";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("Add company failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void updateCompany(Company company) throws CouponSystemException {
		String sql = "update companies set name=?, email=?, password=? where id = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.setInt(4, company.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("update company failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCompany(int companyId) throws CouponSystemException {
		String sql = "delete from companies where id = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("delete company failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public List<Company> getAllCompanies() throws CouponSystemException {
		List<Company> companies = new ArrayList<>();
		String sql = "select * from companies";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				companies.add(company);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get all companies failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

		return companies;
	}

	@Override
	public Company getOneCompanyById(int companyId) throws CouponSystemException {
		String sql = "select * from companies where id=?";
		Connection con = connectionPool.getConnection();
		Company company = new Company();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get one company by id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return company;
	}

	@Override
	public Company getOneCompanyByEmail(String email) throws CouponSystemException {
		String sql = "select * from companies where email=?";
		Connection con = connectionPool.getConnection();
		Company company = new Company();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get one company by email failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return company;
	}

}
