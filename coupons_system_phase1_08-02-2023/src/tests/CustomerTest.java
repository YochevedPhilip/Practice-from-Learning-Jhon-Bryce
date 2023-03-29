package tests;

import java.sql.Date;

import beans.Category;
import beans.Coupon;
import client.ClientType;
import client.LoginManager;
import exceptions.CouponSystemException;

import facades.CustomerFacade;

public class CustomerTest {
	
	private static CustomerFacade customerFacade; 
	
	public static void runAllCustomerTests() {
		customerLoginTest();
		parchaseCouponTest();
		getCustomerCouponsTest();
		getCustomerCouponsByCategoryTest();
		getCustomerCouponsByPriceTest(); 
		getCustomerDetailsTest();
		
	}
	
	public static void customerLoginTest() {
		System.out.println("Customer login test:");
		try {
			customerFacade = (CustomerFacade) LoginManager.getInstance().login("moshe@gmail.com", "1212", ClientType.CUSTOMER);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	} 
	
	public static void parchaseCouponTest() {
		System.out.println("Parchase coupon test");
		Coupon coupon = new Coupon(0, 1, Category.FOOD, "10% discount", "10% discount for purchases", Date.valueOf("2023-01-01"), Date.valueOf("2023-01-31"), 100, 5.5, "image1"); 
		try {
			customerFacade.parchaseCoupon(coupon);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		} 
	}
		
		public static void getCustomerCouponsTest() {
			System.out.println("Get customer coupons test:");
			try {
				for (Coupon coupon : customerFacade.getCustomerCoupons()) {
					System.out.println(coupon);
				}
			} catch (CouponSystemException e) {
				System.out.println(e);
			}
		}
		public static void getCustomerCouponsByCategoryTest() {
			System.out.println("Get customer coupons by category test:");
			try {
				for (Coupon coupon : customerFacade.getCustomerCouponsByCategory(Category.FOOD)) {
					System.out.println(coupon);
				}
			} catch (CouponSystemException e) {
				System.out.println(e);
			}
		}

		public static void getCustomerCouponsByPriceTest() {
			System.out.println("Get customer coupons by price test:");
			try {
				for (Coupon coupon : customerFacade.getCustomerCouponsByPrice(9)) {
					System.out.println(coupon);
				}
			} catch (CouponSystemException e) {
				System.out.println(e);
			}
		}
		
		public static void getCustomerDetailsTest() {
			System.out.println("Get customer details test:");
			try {
				System.out.println(customerFacade.getCustomerDetails());
			} catch (CouponSystemException e) {
				System.out.println(e);
			}
		}
	}

	

