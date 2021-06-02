package Agents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import eis.exceptions.ActException;
import main.Plantation;
import utils.InventoryItem;
import utils.Order;

public abstract class SendingAgent {
	
	protected Plantation env;
	
	public SendingAgent(Plantation env) {
		this.env = env;
	}
	
	protected String obtainAvailableTransport() {
		TransportAgent t = env.obtainAvailableTransporter();
		return (t == null) ? null : t.toString();
	}
	
	protected void sendOrder(String transporterName, int id, String receiverName, String item, int quantity) throws ActException {
		Order order = null;
		try {
			ReceivingAgent reciever = (ReceivingAgent) env.getEntity(receiverName);
			order = new Order(new InventoryItem(item, quantity), reciever, id);
		} catch (Exception e) {
			System.err.println("The recieving agent cannot recieve packages from a transporter!");
			System.err.println(e.toString());
			throw new ActException("The recieving agent cannot recieve packages from a transporter!");
		}
		try {
			TransportAgent transporter = (TransportAgent) env.getEntity(transporterName);
			order.setTransporter(transporter);
			transporter.obtainOrder(order);
		} catch (Exception e) {
			System.err.println("Unkown transporter!");
			System.err.println(e.toString());
			throw new ActException("Unkown transporter!");
		}
	}

}
