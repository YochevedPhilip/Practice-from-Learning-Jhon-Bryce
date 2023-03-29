package dao;

import java.util.List;

import beans.Customer;
import exceptions.CouponSystemException;

public interface CustomersDao {
	
	boolean isCustomerExistsByEmailAndPassword(String email, String password) throws CouponSystemException;
	boolean isCustomerExistsByEmail(String email) throws CouponSystemException;
	boolean isCustomerExistsById(int id) throws CouponSystemException;
	boolean isCustomerExistsByEmailAndNotId(String email, int id) throws CouponSystemException; 
	void addCustomer(Customer customer) throws CouponSystemException; 
	void updateCustomer(Customer customer) throws CouponSystemException; 
	void deleteCustomer(int customerId) throws CouponSystemException; 
	List<Customer> getAllCustomers() throws CouponSystemException; 
	Customer getOneCustomerById(int customerId) throws CouponSystemException;
	Customer getOneCustomerByEmail(String email) throws CouponSystemException;
	

}
