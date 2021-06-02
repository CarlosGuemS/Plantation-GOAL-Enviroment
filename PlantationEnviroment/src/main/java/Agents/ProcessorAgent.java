package Agents;

import eis.eis2java.annotation.AsAction;
import eis.eis2java.annotation.AsPercept;
import eis.eis2java.translation.Filter;
import eis.exceptions.ManagementException;
import main.Plantation;

public class ProcessorAgent implements ReceivingAgent {
	
	public String name;
	private Plantation env;
	
	private int unprocessed;

	public ProcessorAgent(String name, Plantation env) {
		this.name = name;
		this.env = env;
		this.unprocessed = 0;
	}
	
	@AsPercept(name="checkUnprocessedCrops", filter = Filter.Type.ON_CHANGE)
	public int checkUnprocessedCrops() {
		return this.unprocessed;
	}
	
	@AsAction(name="processCrop")
	public void processCrop() {
		this.unprocessed--;
		//TODO Logger
	}
	
	@AsAction(name="end")
	public void end() throws ManagementException {
		env.kill();
	}
	
	@Override
	public String toString() {
		return name;
	}

	public void recieve(String object, int quantity) {
		// TODO Auto-generated method stub
		this.unprocessed += quantity;
	}
}
