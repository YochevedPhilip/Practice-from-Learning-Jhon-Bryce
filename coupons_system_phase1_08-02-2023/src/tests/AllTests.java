package tests;

import connection.ConnectionPool;
import job.CouponExpirationDailyJob;

public class AllTests {

	public static void testAll() {
		try {
			CouponExpirationDailyJob job = new CouponExpirationDailyJob();
			Thread t1 = new Thread(job);
			t1.start();

			AdminTest.runAllAdminTests();
			CompanyTest.runAllCompanyTests();
			CustomerTest.runAllCustomerTests();

			job.stop();
			ConnectionPool.getInstance().closeAllConnections();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
