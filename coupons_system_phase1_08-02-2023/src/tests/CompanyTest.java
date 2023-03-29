package tests;

import java.sql.Date;
import beans.Category;
import beans.Coupon;
import client.ClientType;
import client.LoginManager;
import exceptions.CouponSystemException;
import facades.CompanyFacade;

public class CompanyTest {

	private static CompanyFacade companyFacade;

	public static void runAllCompanyTests() {
		companyLoginTest();
		addCouponTest();
		updateCouponTest();
		deleteCouponTest();
		getCompanyCouponsTest();
		getCompanyCouponsByCategoryTest();
		getCompanyCouponsByPriceTest();
		getCompanyDetailsTest();
	}

	public static void companyLoginTest() {
		System.out.println("Company login test:");
		try {
			companyFacade = (CompanyFacade) LoginManager.getInstance().login("amazon2@gmail.com", "12345",
					ClientType.COMPANY);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void addCouponTest() {
		System.out.println("Add coupon test:");
		Coupon couponToAdd1 = new Coupon(1, Category.FOOD, "10% discount", "10% discount for purchases",
				Date.valueOf("2023-01-01"), Date.valueOf("2023-01-31"), 100, 5.5, "image1");
		Coupon couponToAdd2 = new Coupon(1, Category.FOOD, "20% discount", "20% discount for purchases",
				Date.valueOf("2023-01-01"), Date.valueOf("2023-02-28"), 50, 8, "image2");
		Coupon couponToAdd3 = new Coupon(1, Category.ELECTRICITY, "1+1", "The second product is free",
				Date.valueOf("2023-02-01"), Date.valueOf("2023-03-31"), 10, 25.9, "image3");

		try {
			companyFacade.addCoupon(couponToAdd1);
			companyFacade.addCoupon(couponToAdd2);
			companyFacade.addCoupon(couponToAdd3);

		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void updateCouponTest() {
		System.out.println("Update coupon test:");
		Coupon couponToAdd = new Coupon(2, 1, Category.FOOD, "20% discount", "20% discount for purchases",
				Date.valueOf("2023-01-01"), Date.valueOf("2023-02-28"), 50, 9.9, "image2");
		try {
			companyFacade.updateCoupon(couponToAdd);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void deleteCouponTest() {
		System.out.println("Delete coupon test:");
		try {
			companyFacade.deleteCoupon(3);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void getCompanyCouponsTest() {
		System.out.println("Get company coupons test:");
		try {
			for (Coupon coupon : companyFacade.getCompanyCoupons()) {
				System.out.println(coupon);
			}
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void getCompanyCouponsByCategoryTest() {
		System.out.println("Get company coupons by category test:");
		try {
			for (Coupon coupon : companyFacade.getCompanyCouponsByCategory(Category.FOOD)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void getCompanyCouponsByPriceTest() {
		System.out.println("Get company coupons by price test:");
		try {
			for (Coupon coupon : companyFacade.getCompanyCouponsByPrice(9)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void getCompanyDetailsTest() {
		System.out.println("Get company details test:");
		try {
			System.out.println(companyFacade.getCompanyDetails());
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

}
