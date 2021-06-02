package utils;

import utils.InventoryItem;
import Agents.*;

public class Order {
	
	private InventoryItem content;
	private ReceivingAgent destination;
	private TransportAgent transporter;
	private int id;
	
	public Order(InventoryItem content, ReceivingAgent destination, int id) {
		this.setContent(content);
		this.setDestination(destination);
		this.setTransporter(null);
		this.setId(id);
	}

	public InventoryItem getContent() {
		return content;
	}

	public void setContent(InventoryItem content) {
		this.content = content;
	}

	public ReceivingAgent getDestination() {
		return destination;
	}

	public void setDestination(ReceivingAgent destination) {
		this.destination = destination;
	}

	public TransportAgent getTransporter() {
		return transporter;
	}

	public void setTransporter(TransportAgent transporter) {
		this.transporter = transporter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Order " + id + ":\n\tContent: (" + content.toString() + "),\n\tDestination: " + destination + ",\n\tTransporter: " + transporter + " \n";
	}

}
