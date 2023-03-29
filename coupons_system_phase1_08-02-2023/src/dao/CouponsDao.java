package dao;

import java.util.List;

import beans.Category;
import beans.Coupon;
import exceptions.CouponSystemException;

public interface CouponsDao { 
	
	void addCoupon(Coupon coupon) throws CouponSystemException; 
	void addCouponPurchase(int customerId, int couponId) throws CouponSystemException;
	void updateCoupon(Coupon coupon) throws CouponSystemException; 
	void deleteCouponByCouponId(int couponId) throws CouponSystemException;
	void deleteCouponsByCompanyId(int CompanyId) throws CouponSystemException;
	void deleteCouponPurchase(int customerId, int couponId) throws CouponSystemException;
	void deleteCouponPurchaseByCouponId(int couponId) throws CouponSystemException;
	void deleteCouponPurchaseByCustomerId(int customerId) throws CouponSystemException; 
	void deleteCouponPurchaseByCompanyId(int companyId) throws CouponSystemException;
	void deleteExpiredCoupons() throws CouponSystemException;
	void deletePurchasedExpiredCoupons() throws CouponSystemException;
	List<Coupon> getAllCoupons() throws CouponSystemException; 
	List<Coupon> getAllCouponsByCompanyId(int companyId) throws CouponSystemException;
	List<Coupon> getAllCouponsByCompanyIdAndCategory(int companyId, Category category) throws CouponSystemException;
	List<Coupon> getAllCouponsByCompanyIdAndPrice(int companyId, double maxPrice) throws CouponSystemException;
	List<Coupon> getAllCouponsByCustomerId(int customerId) throws CouponSystemException;
	List<Coupon> getAllCouponsByCustomerIdAndCategory(int customerId, Category category) throws CouponSystemException;
	List<Coupon> getAllCouponsByCustomerIdAndPrice(int customerId, double price) throws CouponSystemException; 
	Coupon getOneCoupon(int CouponId) throws CouponSystemException; 
	boolean isCouponExistsByTitleAndCompanyId(String title, int companyId) throws CouponSystemException;
	boolean isCouponExistsById(int couponId) throws CouponSystemException;
	boolean isCouponPurchasedBefore(int customerId, int couponId) throws CouponSystemException;
	
	
	
	

}
