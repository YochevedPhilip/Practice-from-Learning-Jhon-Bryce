package tests;

import beans.Company;
import beans.Customer;
import client.ClientType;
import client.LoginManager;
import exceptions.CouponSystemException;
import facades.AdminFacade;

public class AdminTest {

	private static AdminFacade adminFacade;

	public static void runAllAdminTests() {
		adminLoginTest();
		addCompanyTest();
		updateCompanyTest();
		deleteCompanyTest();
		getAllCompaniesTest();
		getOneCompanyTest();
		addCustomerTest();
		updateCustomerTest();
		deleteCustomerTest();
		getAllCustomersTest();
		getOneCustomerTest();
	}

	public static void adminLoginTest() {
		System.out.println("Admin login test:");
		try {
			adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
					ClientType.ADMINISTRATOR);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void addCompanyTest() {
		System.out.println("Add company test:");
		Company companyToAdd1 = new Company("Amazon", "amazon@gmail.com", "1234");
		Company companyToAdd2 = new Company("Ebay", "ebay@gmail.com", "1234");
		Company companyToAdd3 = new Company("Facebook", "facebook@gmail.com", "1234");

		try {
			adminFacade.addCompany(companyToAdd1);
			adminFacade.addCompany(companyToAdd2);
			adminFacade.addCompany(companyToAdd3);

		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void updateCompanyTest() {
		System.out.println("Update company test:");
		Company companyToUpdate = new Company(1, "Amazon", "amazon2@gmail.com", "12345");
		try {
			adminFacade.updateCompany(companyToUpdate);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void deleteCompanyTest() {
		System.out.println("Delete company test:");
		try {
			adminFacade.deleteCompany(2);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void getAllCompaniesTest() {
		System.out.println("Get all companies test:");
		try {
			for (Company company : adminFacade.getAllCompanies()) {
				System.out.println(company);
			}
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void getOneCompanyTest() {
		System.out.println("Get one company test:");
		try {
			System.out.println(adminFacade.getOneCompany(3));
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void addCustomerTest() {
		System.out.println("Add customer test:");
		Customer customerToAdd1 = new Customer("Moshe", "Koen", "moshe@gmail.com", "1212");
		Customer customerToAdd2 = new Customer("Yoav", "Levi", "yoav@gmail.com", "1111");
		Customer customerToAdd3 = new Customer("Tal", "Golan", "tal@gmail.com", "9876");
		try {
			adminFacade.addCustomer(customerToAdd1);
			adminFacade.addCustomer(customerToAdd2);
			adminFacade.addCustomer(customerToAdd3);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void updateCustomerTest() {
		System.out.println("Update customer test:");
		Customer customerToUpdate = new Customer(2, "Yoav", "Levi", "yoavlevi2@gmail.com", "2222");
		try {
			adminFacade.updateCustomer(customerToUpdate);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void deleteCustomerTest() {
		System.out.println("Delete customer test:");
		try {
			adminFacade.deleteCustomer(3);
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void getAllCustomersTest() {
		System.out.println("Get all customers test:");
		try {
			for (Customer customer : adminFacade.getAllCustomers()) {
				System.out.println(customer);
			}
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

	public static void getOneCustomerTest() {
		System.out.println("Get one customer test:");
		try {
			System.out.println(adminFacade.getOneCustomer(2));
		} catch (CouponSystemException e) {
			System.out.println(e);
		}
	}

}
