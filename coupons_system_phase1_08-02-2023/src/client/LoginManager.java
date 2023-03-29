package client;

import exceptions.CouponSystemException;
import facades.AdminFacade;
import facades.ClientFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;

public class LoginManager {

	private static LoginManager instance = new LoginManager();

	private LoginManager() {
	}

	public static LoginManager getInstance() {
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException {
		ClientFacade client = null;

		switch (clientType) {
		case ADMINISTRATOR: {
			client = new AdminFacade();
			if (client.login(email, password)) {
				return client;

			}
			break;
		}

		case COMPANY: {
			client = new CompanyFacade();
			if (client.login(email, password)) {
				return client;
			}
			break;
		}

		case CUSTOMER: {
			client = new CustomerFacade();
			if (client.login(email, password)) {
				return client;
			}
			break;
		}
		default:
			throw new CouponSystemException("Invalid client type");
		}
		return client;
	}
}
