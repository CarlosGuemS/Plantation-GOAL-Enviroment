package Agents;

import eis.eis2java.annotation.AsPercept;
import eis.eis2java.translation.Filter;
import eis.exceptions.ActException;
import main.Plantation;
import eis.eis2java.annotation.AsAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.Order;


public class SupplierAgent extends SendingAgent {
	
	private String name;
	
	public SupplierAgent(String name, Plantation env) {
		super(env);
		this.name = name;
		
	}
	
	@Override
	@AsPercept(name="availableTransport", filter = Filter.Type.ALWAYS)
	public String obtainAvailableTransport() {
		return super.obtainAvailableTransport();
	}
	
	@AsAction(name="sendSupplies")
	public void sendSupplies(String transporterName, int id, String receiverName, String item, int quantity) throws ActException {
		super.sendOrder(transporterName, id, receiverName, item, quantity);
	}
	
	@Override
	public String toString() {
		return name;
	}
	

}
