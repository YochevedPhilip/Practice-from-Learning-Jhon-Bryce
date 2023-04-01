package api_test;

import java.util.Calendar;
import java.util.Objects;

public class Order implements Comparable<Order> {

	private Calendar readyOn;
	private String text;
	private boolean important;
	private boolean poped;

	public Order(Calendar readyOn, String text, boolean important) {
		super();
		this.readyOn = readyOn;
		this.text = text;
		this.important = important;
		this.poped = false;
	}

	public final Calendar getReadyOn() {
		return readyOn;
	}

	public final void setReadyOn(Calendar readyOn) {
		this.readyOn = readyOn;
	}

	public final String getText() {
		return text;
	}

	public final void setText(String text) {
		this.text = text;
	}

	public final boolean isImportant() {
		return important;
	}

	public final void setImportant(boolean important) {
		this.important = important;
	}

	public final boolean isPoped() {
		return poped;
	}

	public final void setPoped(boolean poped) {
		this.poped = poped;
	}

	@Override
	public int compareTo(Order o) {

		return this.readyOn.compareTo(o.readyOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(readyOn, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		return Objects.equals(readyOn, other.readyOn) && Objects.equals(text, other.text);
	}

	@Override
	public String toString() {
		return "Order [readyOn=" + readyOn.getTime() + ", text=" + text + ", important=" + important + ", poped="
				+ poped + "]";
	}

}
