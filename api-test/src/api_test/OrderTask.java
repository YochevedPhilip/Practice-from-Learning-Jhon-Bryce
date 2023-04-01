package api_test;

import java.util.Calendar;
import java.util.Set;

public class OrderTask implements Runnable {

	private Set<Order> orders;
	private boolean isRunning = true;

	public OrderTask(Set<Order> orders) {
		super();
		this.orders = orders;
	}

	@Override
	public void run() {
		while (isRunning) {
			for (Order order : orders) {
				if (!order.isPoped() && order.getReadyOn().getTime().before(Calendar.getInstance().getTime())) {
					if (order.isImportant()) {
						Thread thread = new Thread(new ImportantOrderTask(order));
						thread.start();

					} else {
						System.out.println(order.getText());

					}
					order.setPoped(true);
				}

			}
			try {
				Thread.sleep(1000 * 30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void stop() {
		isRunning = false;
	}

}
