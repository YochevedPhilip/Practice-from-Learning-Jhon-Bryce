package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Category;
import beans.Coupon;
import beans.Customer;
import connection.ConnectionPool;
import exceptions.CouponSystemException;

public class CouponsDbDao implements CouponsDao {

	ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) throws CouponSystemException {
		String sql = "insert into coupons values (0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, coupon.getCompanyId());
			pstmt.setInt(2, coupon.getCategory().ordinal() + 1);
			pstmt.setString(3, coupon.getTitle());
			pstmt.setString(4, coupon.getDescription());
			pstmt.setDate(5, coupon.getStartDate());
			pstmt.setDate(6, coupon.getEndDate());
			pstmt.setInt(7, coupon.getAmount());
			pstmt.setDouble(8, coupon.getPrice());
			pstmt.setString(9, coupon.getImage());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("Add coupon failed");

		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void addCouponPurchase(int customerId, int couponId) throws CouponSystemException {
		String sql = "insert into customers_vs_coupons values (?, ?)";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);
			pstmt.execute();

		} catch (SQLException e) {
			throw new CouponSystemException("Add coupon purchase failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		String sql = "update coupons set company_id=?, category_id=?, title=?, description=?, start_date=?, end_date=?, amount=?, price=?, image=? where id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, coupon.getCompanyId());
			pstmt.setInt(2, coupon.getCategory().ordinal() + 1);
			pstmt.setString(3, coupon.getTitle());
			pstmt.setString(4, coupon.getDescription());
			pstmt.setDate(5, coupon.getStartDate());
			pstmt.setDate(6, coupon.getEndDate());
			pstmt.setInt(7, coupon.getAmount());
			pstmt.setDouble(8, coupon.getPrice());
			pstmt.setString(9, coupon.getImage());
			pstmt.setInt(10, coupon.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Update coupon failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCouponByCouponId(int couponId) throws CouponSystemException {
		String sql = "delete from coupons where id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, couponId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("Delete coupon by coupon id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCouponsByCompanyId(int CompanyId) throws CouponSystemException {
		String sql = "delete from coupons where company_id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, CompanyId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("Delete coupon by company id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCouponPurchase(int customerId, int couponId) throws CouponSystemException {
		String sql = "delete from customers_vs_coupons where customer_id=? and coupon_id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Delete coupon purchase failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCouponPurchaseByCouponId(int couponId) throws CouponSystemException {
		String sql = "delete from customers_vs_coupons where coupon_id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, couponId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Delete coupon purchase by coupon id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCouponPurchaseByCustomerId(int customerId) throws CouponSystemException {
		String sql = "delete from customers_vs_coupons where customer_id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Delete coupon purchase by customer id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCouponPurchaseByCompanyId(int companyId) throws CouponSystemException {
		String sql = "DELETE FROM customers_vs_coupons WHERE coupon_id IN (SELECT id FROM coupons WHERE company_id = ?)";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Delete coupon purchase by commpany id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void deleteExpiredCoupons() throws CouponSystemException {
		String sql = "delete from coupons where end_date < curdate()";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Delete expired coupon failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public void deletePurchasedExpiredCoupons() throws CouponSystemException {
		String sql = "delete from customers_vs_coupons where coupon_id in (select id from coupons where end_date < CURDATE())";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Delete expired coupon failed");
		} finally {
			connectionPool.restoreConnection(con);
		}

	}

	@Override
	public List<Coupon> getAllCoupons() throws CouponSystemException {
		List<Coupon> allCoupons = new ArrayList<>();
		String sql = "select * from coupons";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Category.values()[rs.getInt(3) - 1]);
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6));
				coupon.setEndDate(rs.getDate(7));
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getInt(9));
				coupon.setImage(rs.getString(10));
				allCoupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return allCoupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCompanyId(int companyId) throws CouponSystemException {
		List<Coupon> allCoupons = new ArrayList<>();
		String sql = "select * from coupons where company_id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCategory(Category.values()[rs.getInt(3) - 1]);
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6));
				coupon.setEndDate(rs.getDate(7));
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getInt(9));
				coupon.setImage(rs.getString(10));
				allCoupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return allCoupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCompanyIdAndCategory(int companyId, Category category)
			throws CouponSystemException {
		List<Coupon> allCoupons = new ArrayList<>();
		String sql = "select * from coupons where company_id=? and category_id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			pstmt.setInt(2, category.ordinal() + 1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Category.values()[rs.getInt(3) - 1]);
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6));
				coupon.setEndDate(rs.getDate(7));
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getInt(9));
				coupon.setImage(rs.getString(10));
				allCoupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons by company id and category id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return allCoupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCompanyIdAndPrice(int companyId, double maxPrice) throws CouponSystemException {
		List<Coupon> allCoupons = new ArrayList<>();
		String sql = "select * from coupons where company_id=? and price<=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			pstmt.setDouble(2, maxPrice);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Category.values()[rs.getInt(3) - 1]);
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6));
				coupon.setEndDate(rs.getDate(7));
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getInt(9));
				coupon.setImage(rs.getString(10));
				allCoupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons by company id and max price failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return allCoupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCustomerId(int customerId) throws CouponSystemException {
		List<Coupon> allCoupons = new ArrayList<>();

		String sql = "select * from `coupons` join `customers_vs_coupons` on coupons.id = customers_vs_coupons.coupon_id where customer_id = ?;";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Category.values()[rs.getInt(3) - 1]);
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6));
				coupon.setEndDate(rs.getDate(7));
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getInt(9));
				coupon.setImage(rs.getString(10));
				allCoupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons by customer id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return allCoupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCustomerIdAndCategory(int customerId, Category category)
			throws CouponSystemException {
		List<Coupon> allCoupons = new ArrayList<>();

		String sql = "select * from `coupons` join `customers_vs_coupons` on coupons.id = customers_vs_coupons.coupon_id where customer_id = ? and category_id = ?;";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, category.ordinal() + 1);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Category.values()[rs.getInt(3) - 1]);
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6));
				coupon.setEndDate(rs.getDate(7));
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getInt(9));
				coupon.setImage(rs.getString(10));
				allCoupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons by customer id and category failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return allCoupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCustomerIdAndPrice(int customerId, double price) throws CouponSystemException {
		List<Coupon> allCoupons = new ArrayList<>();

		String sql = "select * from `coupons` join `customers_vs_coupons` on coupons.id = customers_vs_coupons.coupon_id where customer_id = ? and price <= ?;";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setDouble(2, price);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Category.values()[rs.getInt(3) - 1]);
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6));
				coupon.setEndDate(rs.getDate(7));
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getInt(9));
				coupon.setImage(rs.getString(10));
				allCoupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons by customer id and category failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return allCoupons;
	}

	@Override
	public Coupon getOneCoupon(int couponId) throws CouponSystemException {
		Coupon coupon = new Coupon();
		String sql = "select * from coupons where id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, couponId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Category.values()[rs.getInt(3) - 1]);
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6));
				coupon.setEndDate(rs.getDate(7));
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getInt(9));
				coupon.setImage(rs.getString(10));
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Get one coupon failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return coupon;
	}

	@Override
	public boolean isCouponPurchasedBefore(int customerId, int couponId) throws CouponSystemException {
		boolean isExists = false;
		String sql = "SELECT * FROM customers_vs_coupons where customer_id = ? and coupon_id = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Check if coupon purchased by customer before failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return isExists;
	}

	@Override
	public boolean isCouponExistsByTitleAndCompanyId(String title, int companyId) throws CouponSystemException {
		boolean isExists = false;
		String sql = "SELECT * FROM coupons where title=? and company_id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setInt(2, companyId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Is company exsists by title and company id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return isExists;
	}

	@Override
	public boolean isCouponExistsById(int couponId) throws CouponSystemException {
		boolean isExists = false;
		String sql = "SELECT * FROM coupons where id=?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, couponId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExists = true;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Is company exsists by title and company id failed");
		} finally {
			connectionPool.restoreConnection(con);
		}
		return isExists;
	}

}
