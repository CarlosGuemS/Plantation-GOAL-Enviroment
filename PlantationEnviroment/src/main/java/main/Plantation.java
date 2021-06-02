package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eis.eis2java.environment.AbstractEnvironment;
import eis.eis2java.handlers.DefaultActionHandler;
import eis.eis2java.handlers.DefaultPerceptHandler;
import eis.iilang.Action;
import eis.iilang.EnvironmentState;
import eis.iilang.Parameter;

import eis.exceptions.*;
import Agents.FarmerAgent;
import Agents.ProcessorAgent;
import Agents.SupplierAgent;
import Agents.TransportAgent;



public class Plantation extends AbstractEnvironment {
	
	private List<TransportAgent> transporterList; 
	
	/**
	 * Method to initialize environment
	 * Parameters are ignored for this demo
	 */
	
	
	
	public void init(Map<String, Parameter> parameters) throws ManagementException {
		
		//Reset the environment
		reset(parameters);
		
		//Initialize the transporterList
		transporterList = new ArrayList<TransportAgent>(1);
		
		super.setState(eis.iilang.EnvironmentState.PAUSED);
		
		//Create entities
		try {
			SupplierAgent s = new SupplierAgent("supplier", this);
			registerEntity("supplier", s);
			
			TransportAgent t = new TransportAgent("transporter");
			registerEntity("transporter", t);
			//Add transporter the transporter list
			transporterList.add(t);
			registerEntity("processor", new ProcessorAgent("processor", this));
			registerEntity("farmer", new FarmerAgent("farmer", this));
			
		} catch (EntityException e) {
			throw new ManagementException("Could not create an entity", e);
		}
		
		super.setState(eis.iilang.EnvironmentState.RUNNING);
		
	}

	public TransportAgent obtainAvailableTransporter() {
		for (TransportAgent transporter : this.transporterList) {
			if (transporter.getAvailability()){
				return transporter;
			}
		}
		return null;
	}
	
	
	/**
	 * Kill 
	 */
	@Override
	public void kill() throws ManagementException {
	    setState(EnvironmentState.KILLED);
	}

	@Override
	protected boolean isSupportedByEnvironment(Action action) {
		// Supplier
		if (action.getName().equals("tramitOrder") && action.getParameters().size()==4)
			return true;
		else if (action.getName().equals("sendSupplies") && action.getParameters().size()==5)
			return true;
		
		// Farmer agent
		else if (action.getName().equals("plantCrop") && action.getParameters().size()==1)
			return true;
		else if (action.getName().equals("healPlant") && action.getParameters().size()==1)
			return true;
		else if (action.getName().equals("healField") && action.getParameters().size()==1)
			return true;
		else if (action.getName().equals("harvest") && action.getParameters().size()==2)
			return true;
		else if (action.getName().equals("sendCrops") && action.getParameters().size()==5)
			return true;
		
		// Transport agent
		else if (action.getName().equals("move") && action.getParameters().size()==2)
			return true;
		else if (action.getName().equals("acceptRequest") && action.getParameters().size()==0)
			return true;
		else if (action.getName().equals("unload") && action.getParameters().size()==3)
			return true;
		
		// Processor agent
		else if (action.getName().equals("processCrop") && action.getParameters().size()==0)
			return true;
		else if (action.getName().equals("end") && action.getParameters().size()==0)
			return true;
	
			
		return false;
	}

	@Override
	protected boolean isSupportedByType(Action action, String type) {
		
		// Supplier
		if (type.equals("supplier")) {
			if (action.getName().equals("sendSupplies") && action.getParameters().size()==5)
				return true;
		}
		
		// Farmer agent
		else if (type.equals("farmer")) {
			if (action.getName().equals("plantCrop") && action.getParameters().size()==1)
				return true;
			if (action.getName().equals("healPlant") && action.getParameters().size()==1)
				return true;
			if (action.getName().equals("healField") && action.getParameters().size()==1)
				return true;
			if (action.getName().equals("harvest") && action.getParameters().size()==2)
				return true;
			if (action.getName().equals("sendCrops") && action.getParameters().size()==5)
				return true;
		}
		
		// Transport agent
		else if (type.equals("transporter")) {
			if (action.getName().equals("move") && action.getParameters().size()==2)
				return true;
			else if (action.getName().equals("acceptRequest") && action.getParameters().size()==0)
				return true;
			else if (action.getName().equals("unload") && action.getParameters().size()==3)
				return true;
		}
		
		// Processor agent
		else if (type.equals("processor")) {
			if (action.getName().equals("processCrop") && action.getParameters().size()==0)
				return true;
			else if (action.getName().equals("end") && action.getParameters().size()==0)
				return true;
		}	
		return false;
	}
	
	

}
