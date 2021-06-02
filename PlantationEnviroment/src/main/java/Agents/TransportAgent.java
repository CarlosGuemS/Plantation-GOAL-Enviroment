package Agents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import eis.eis2java.annotation.AsAction;
import eis.eis2java.annotation.AsPercept;
import eis.eis2java.translation.Filter;
import utils.Order;

public class TransportAgent {
	
	private String name;
	
	private boolean availability;
	private Order currentOrder;
	private String position;
	
	public TransportAgent(String name) {
		this.name = name;
		this.position = "garage";
		availability = true;
		currentOrder = null;
	}
	
	public boolean getAvailability() {return availability;}
	
	@AsPercept(name="available", filter = Filter.Type.ON_CHANGE)
	public int avaiabilityChange() {
		return (this.availability) ? 1 : 0;
	}
	
	@AsPercept(name="currentPosition", filter = Filter.Type.ON_CHANGE)
	public String checkPosition() {
		return position;
	}
	
	@AsPercept(name="currentInventory", multipleArguments = true, filter = Filter.Type.ON_CHANGE)
	public List<Serializable> checkInventory() {
		if (this.currentOrder != null) {
			List<Serializable> orderToSend = new ArrayList<Serializable>(2);
			orderToSend.add(currentOrder.getContent().getName());
			orderToSend.add(currentOrder.getContent().getQuantity());
			return orderToSend;
		}
		return null;
	}
	
	// Possible timeout
	@AsAction(name = "move")
	public void move(String origin, String dest) {
		this.position = dest;
	}
	
	@AsAction(name = "acceptRequest")
	public void acceptRequest() {
		this.availability = false;
	}
	
	@AsAction(name = "unload")
	public void unload(String position, String name, int quantity) {
		// TODO: Descargar
		ReceivingAgent agent = this.currentOrder.getDestination();
		agent.recieve(this.currentOrder.getContent().getName(), this.currentOrder.getContent().getQuantity());
		this.availability = true;
		this.currentOrder = null;	
	}
	
	/**
	 * Method to receive order content from others
	 */
	public void obtainOrder(Order order) {
		this.currentOrder = order;
	}
	
	
	@Override
	public String toString() {
		return name;
	}

}
