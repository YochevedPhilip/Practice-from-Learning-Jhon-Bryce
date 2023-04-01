package api_test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class OrderSystem {

	private Set<Order> orders;
	private OrderTask task;

	public OrderSystem() {
		super();
		this.orders = new HashSet();
		this.task = new OrderTask(orders);
		Thread thread = new Thread(task);
		thread.start();
	}

	public boolean addOrder(Order order) {
		if (order.getReadyOn().getTime().before(Calendar.getInstance().getTime())) {
			return false;
		}
		return (orders.add(order));

	}

	public List<Order> getOrders() {
		List<Order> orders = new ArrayList(this.orders);
		Collections.sort(orders);
		for (Order order : orders) {
			System.out.println(order);
		}
		return orders;

	}

	public void orderMenu() {

		System.out.println("Menus:\n 1. Add order.\n 2. Get orders\n 3. Exit");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Please inser your choice");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				scanner.nextLine();
				System.out.println("Please insert text:");
				String text = scanner.nextLine();

				System.out.println("Please insert if important(true/false):");
				boolean isImportant = scanner.nextBoolean();

				System.out.println("Please insert hours");
				int hour = scanner.nextInt();

				System.out.println("Please insert minutes");
				int min = scanner.nextInt();

				Calendar readyOn = Calendar.getInstance();
				readyOn.set(Calendar.HOUR_OF_DAY, hour);
				readyOn.set(Calendar.MINUTE, min);
				readyOn.set(Calendar.SECOND, 0);
				readyOn.set(Calendar.MILLISECOND, 0);

				addOrder(new Order(readyOn, text, isImportant));
				break;

			case 2:
				getOrders();
				break;

			case 3:
				task.stop();
				scanner.close();
				return;

			}
		}

	}

}
