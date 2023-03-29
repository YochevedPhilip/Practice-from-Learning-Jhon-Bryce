package facades;




import dao.CompaniesDbDao;
import dao.CouponsDbDao;
import dao.CustomersDbDao;
import exceptions.CouponSystemException;

public abstract class ClientFacade { 
	
	protected CompaniesDbDao companiesDbDao = new CompaniesDbDao(); 
	protected CouponsDbDao couponsDbDao = new CouponsDbDao(); 
	protected CustomersDbDao customersDbDao = new CustomersDbDao(); 
	
	public abstract boolean login(String email, String password) throws CouponSystemException;

	

	
	
	
	
	 

}
