package facades;

import java.util.List;

import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;

public class CompanyFacade extends ClientFacade {

	private int companyId;

	public boolean login(String email, String password) throws CouponSystemException {
		if (!companiesDbDao.isCompanyExistByEmailAndPassword(email, password)) {
			throw new CouponSystemException("Email or password is wrong");
		}
		companyId = companiesDbDao.getOneCompanyByEmail(email).getId();
		return true;
	}

	public void addCoupon(Coupon coupon) throws CouponSystemException {
		if (couponsDbDao.isCouponExistsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompanyId())) {
			throw new CouponSystemException("Coupon Title already exists");
		}
		couponsDbDao.addCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		Coupon couponFromDb = couponsDbDao.getOneCoupon(coupon.getId());
		if (couponFromDb.getId() == 0) {
			throw new CouponSystemException("Coupon to update not exists");
		}
		if (couponFromDb.getCompanyId() != coupon.getCompanyId()) {
			throw new CouponSystemException("Can not update company id");
		}
		couponFromDb.setAmount(coupon.getAmount());
		couponFromDb.setCategory(coupon.getCategory());
		couponFromDb.setDescription(coupon.getDescription());
		couponFromDb.setEndDate(coupon.getEndDate());
		couponFromDb.setImage(coupon.getImage());
		couponFromDb.setPrice(coupon.getPrice());
		couponFromDb.setStartDate(coupon.getStartDate());
		couponFromDb.setTitle(coupon.getTitle());
		couponsDbDao.updateCoupon(couponFromDb);
	}

	public void deleteCoupon(int couponId) throws CouponSystemException {
		if (!couponsDbDao.isCouponExistsById(couponId)) {
			throw new CouponSystemException("Coupon to delete not exists");
		}
		couponsDbDao.deleteCouponPurchaseByCouponId(couponId);
		couponsDbDao.deleteCouponByCouponId(couponId);

	}

	public List<Coupon> getCompanyCoupons() throws CouponSystemException {
		return couponsDbDao.getAllCouponsByCompanyId(this.companyId);
	}

	public List<Coupon> getCompanyCouponsByCategory(Category categoty) throws CouponSystemException {
		return couponsDbDao.getAllCouponsByCompanyIdAndCategory(companyId, categoty);
	}

	public List<Coupon> getCompanyCouponsByPrice(double maxPrice) throws CouponSystemException {
		return couponsDbDao.getAllCouponsByCompanyIdAndPrice(companyId, maxPrice);
	}

	public Company getCompanyDetails() throws CouponSystemException {
		Company companyFromDb = companiesDbDao.getOneCompanyById(companyId);
		List<Coupon> couponsFromDb = couponsDbDao.getAllCouponsByCompanyId(companyId);
		companyFromDb.setCoupons(couponsFromDb);
		return companyFromDb;
	}

}
