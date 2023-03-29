package job;

import dao.CouponsDbDao;
import exceptions.CouponSystemException;

public class CouponExpirationDailyJob implements Runnable {

	private CouponsDbDao couponsDbDao;
	private boolean quit;

	public CouponExpirationDailyJob() {
		this.couponsDbDao = new CouponsDbDao();
		this.quit = false;
	}

	@Override
	public void run() {
		while (!quit) {
			try {
				couponsDbDao.deletePurchasedExpiredCoupons();
				couponsDbDao.deleteExpiredCoupons();
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		this.quit = true;
	}

}
