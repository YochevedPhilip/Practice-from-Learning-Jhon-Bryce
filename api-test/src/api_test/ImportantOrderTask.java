package api_test;

public class ImportantOrderTask implements Runnable {

	private Order order;

	public ImportantOrderTask() {

	}

	public ImportantOrderTask(Order order) {
		super();
		this.order = order;
	}

	@Override
	public void run() {

		for (int i = 0; i < 3; i++) {

			System.out.println(order.getText());
			try {
				Thread.sleep(1000 * 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
