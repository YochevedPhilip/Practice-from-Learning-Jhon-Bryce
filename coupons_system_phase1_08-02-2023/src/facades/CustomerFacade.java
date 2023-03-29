package facades;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import exceptions.CouponSystemException;

public class CustomerFacade extends ClientFacade {

	private int customerId;

	Coupon coupon = new Coupon();

	public boolean login(String email, String password) throws CouponSystemException {
		if (!customersDbDao.isCustomerExistsByEmailAndPassword(email, password)) {
			throw new CouponSystemException("Email or password is wrong");
		}
		customerId = customersDbDao.getOneCustomerByEmail(email).getId();
		return true;
	}

	public void parchaseCoupon(Coupon coupon) throws CouponSystemException {
		Coupon couponFromDb = couponsDbDao.getOneCoupon(coupon.getId());
		if (couponFromDb.getId() == 0) {
			throw new CouponSystemException("Coupon not found");
		}

		if (couponFromDb.getAmount() <= 0) {
			throw new CouponSystemException("Coupon amount is 0");
		}

		if (couponFromDb.getEndDate().before(Date.valueOf(LocalDate.now()))) {
			throw new CouponSystemException("The coupon date is expired");
		}

		if (couponsDbDao.isCouponPurchasedBefore(this.customerId, couponFromDb.getId())) {
			throw new CouponSystemException("The coupon purchased before");
		}
		couponFromDb.setAmount(couponFromDb.getAmount() - 1);
		couponsDbDao.updateCoupon(couponFromDb);
		couponsDbDao.addCouponPurchase(customerId, couponFromDb.getId());
	}

	public List<Coupon> getCustomerCoupons() throws CouponSystemException {
		return couponsDbDao.getAllCouponsByCustomerId(customerId);
	}

	public List<Coupon> getCustomerCouponsByCategory(Category category) throws CouponSystemException {
		return couponsDbDao.getAllCouponsByCustomerIdAndCategory(customerId, category);
	}

	public List<Coupon> getCustomerCouponsByPrice(double price) throws CouponSystemException {
		return couponsDbDao.getAllCouponsByCustomerIdAndPrice(customerId, price);
	}

	public Customer getCustomerDetails() throws CouponSystemException {
		Customer customerFromDb = customersDbDao.getOneCustomerById(customerId);
		List<Coupon> couponsFromDb = couponsDbDao.getAllCouponsByCustomerId(customerId);
		customerFromDb.setCoupons(couponsFromDb);
		return customerFromDb;
	}

}
