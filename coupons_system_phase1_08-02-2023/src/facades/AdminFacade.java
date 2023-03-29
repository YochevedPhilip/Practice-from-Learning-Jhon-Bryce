package facades;

import java.util.List;

import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;

public class AdminFacade extends ClientFacade {

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (!(email.equalsIgnoreCase("admin@admin.com") && password.equals("admin"))) {
			throw new CouponSystemException("Email or password is wrong");
		}
		return true;
	}

	public void addCompany(Company company) throws CouponSystemException {
		if (companiesDbDao.isCompanyExistByName(company.getName())) {
			throw new CouponSystemException("Company name already exists");
		}
		if (companiesDbDao.isCompanyExistByEmail(company.getEmail())) {
			throw new CouponSystemException("Company email already exists");
		}
		companiesDbDao.addCompany(company);
	}

	public void updateCompany(Company company) throws CouponSystemException {
		Company companyFromDb = companiesDbDao.getOneCompanyById(company.getId());
		if (companyFromDb.getId() == 0) {
			throw new CouponSystemException("Company to update not exists");
		}
		if (!companyFromDb.getName().equalsIgnoreCase(company.getName())) {
			throw new CouponSystemException("Can not update company name");
		}
		companyFromDb.setEmail(company.getEmail());
		companyFromDb.setPassword(company.getPassword());
		companiesDbDao.updateCompany(companyFromDb);
	}

	public void deleteCompany(int companyId) throws CouponSystemException {
		if (!companiesDbDao.isCompanyExistById(companyId)) {
			throw new CouponSystemException("Company to delete not exists");
		}
		couponsDbDao.deleteCouponPurchaseByCompanyId(companyId);
		couponsDbDao.deleteCouponsByCompanyId(companyId);
		companiesDbDao.deleteCompany(companyId);
	}

	public List<Company> getAllCompanies() throws CouponSystemException {
		return companiesDbDao.getAllCompanies();
	}

	public Company getOneCompany(int companyID) throws CouponSystemException {
		if (!companiesDbDao.isCompanyExistById(companyID)) {
			throw new CouponSystemException("Company to delete not exists");
		}
		return companiesDbDao.getOneCompanyById(companyID);
	}

	public void addCustomer(Customer customer) throws CouponSystemException {
		if (customersDbDao.isCustomerExistsByEmail(customer.getEmail())) {
			throw new CouponSystemException("Customer email already exists");
		}
		customersDbDao.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws CouponSystemException {
		Customer customerFromDb = customersDbDao.getOneCustomerById(customer.getId());
		if (customerFromDb.getId() == 0) {
			throw new CouponSystemException("Customer to update not exists");
		}

		if (customersDbDao.isCustomerExistsByEmailAndNotId(customer.getEmail(), customer.getId())) {
			throw new CouponSystemException("customer email to update already exsists");
		}
		customerFromDb.setFirstName(customer.getFirstName());
		customerFromDb.setLastName(customer.getLastName());
		customerFromDb.setEmail(customer.getEmail());
		customerFromDb.setPassword(customer.getPassword());
		customersDbDao.updateCustomer(customerFromDb);
	}

	public void deleteCustomer(int customerId) throws CouponSystemException {
		if (!customersDbDao.isCustomerExistsById(customerId)) {
			throw new CouponSystemException("Customer to delete not exists");
		}
		couponsDbDao.deleteCouponPurchaseByCustomerId(customerId);
		customersDbDao.deleteCustomer(customerId);
	}

	public List<Customer> getAllCustomers() throws CouponSystemException {
		return customersDbDao.getAllCustomers();
	}

	public Customer getOneCustomer(int customerId) throws CouponSystemException {
		if (!customersDbDao.isCustomerExistsById(customerId)) {
			throw new CouponSystemException("Customer not exists");
		}
		return customersDbDao.getOneCustomerById(customerId);
	}
}
