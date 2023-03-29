package dao;


import java.util.List;

import beans.Company;
import exceptions.CouponSystemException;

public interface CompaniesDao { 
	
	boolean isCompanyExistByEmailAndPassword(String email, String password) throws CouponSystemException; 
	boolean isCompanyExistByName(String name) throws CouponSystemException;
	boolean isCompanyExistByEmail(String email) throws CouponSystemException;
	boolean isCompanyExistById(int id) throws CouponSystemException;
	void addCompany(Company company) throws CouponSystemException; 
	void updateCompany(Company company) throws CouponSystemException; 
	void deleteCompany(int companyId) throws CouponSystemException; 
	List<Company> getAllCompanies() throws CouponSystemException; 
	Company getOneCompanyById(int companyId) throws CouponSystemException;
	Company getOneCompanyByEmail(String email) throws CouponSystemException;
	
	

}
