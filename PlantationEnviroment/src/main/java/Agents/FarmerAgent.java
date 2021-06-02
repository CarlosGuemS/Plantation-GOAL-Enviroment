package Agents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import eis.eis2java.annotation.AsAction;
import eis.eis2java.annotation.AsPercept;
import eis.eis2java.translation.Filter;
import eis.exceptions.ActException;
import main.Plantation;
import utils.CropPlant;
import utils.Order;

public class FarmerAgent extends SendingAgent implements ReceivingAgent {
	
	private String name;
	
	private int numberFields;
	private CropPlant[] fields;
	
	private int seeds;
	private int fertilizer;
	private int pesticides;
	
	
	public FarmerAgent(String name, Plantation env) {
		super(env);
		this.name = name;
		numberFields = 5;
		fields = new CropPlant[numberFields];
		
		seeds = fertilizer = pesticides = 0;
	}
	
	@AsPercept(name="seeds", filter = Filter.Type.ON_CHANGE)
	public int checkSeedNumber() {
		return seeds;
	}
	
	@AsPercept(name="fertilizer", filter = Filter.Type.ON_CHANGE)
	public int checkFertilizerNumber() {
		return fertilizer;
	}
	
	@AsPercept(name="pesticides", filter = Filter.Type.ON_CHANGE)
	public int checkPesticideNumber() {
		return pesticides;
	}
	
	@AsPercept(name = "field", multiplePercepts = true, filter = Filter.Type.ONCE)
	public List<Integer> learnFieldIDs() {
		List<Integer> fields = new ArrayList<Integer>(numberFields);
		for (int i = 0; i < numberFields; i++)
			fields.add(i);
		return fields;
	}
	
	@AsPercept(name = "fieldInUse", multiplePercepts = true, filter = Filter.Type.ALWAYS)
	public List<Integer> checkFieldOnUse() {
		List<Integer> fieldsUsed = new ArrayList<Integer>(numberFields);
		for (int i = 0; i < numberFields; i++)
			if (fields[i] != null) {
				fields[i].updateState();
				fieldsUsed.add(i);
			}
		return fieldsUsed;
	}
	
	@AsPercept(name = "sickPlant", multiplePercepts = true, filter = Filter.Type.ALWAYS)
	public List<Integer> checkSickPlants() {
		List<Integer> sickPlants = new ArrayList<Integer>(numberFields);
		for (int i = 0; i < numberFields; i++)
			if (fields[i] != null && fields[i].isPlantSick())
				sickPlants.add(i);
		return sickPlants;
	}
	
	@AsPercept(name = "weakSoil", multiplePercepts = true, filter = Filter.Type.ALWAYS)
	public List<Integer> checkWeakSoil() {
		List<Integer> weakSoil = new ArrayList<Integer>(numberFields);
		for (int i = 0; i < numberFields; i++)
			if (fields[i] != null && fields[i].isFieldSick())
				weakSoil.add(i);
		return weakSoil;
	}
	
	@AsPercept(name = "readyToHarvest", multiplePercepts = true, filter = Filter.Type.ALWAYS)
	public List<Integer> readyToHarvest() {
		List<Integer> ready = new ArrayList<Integer>(numberFields);
		for (int i = 0; i < numberFields; i++)
			if (fields[i] != null && fields[i].isReady())
				ready.add(i);
		return ready;
	}
	
	@Override
	@AsPercept(name="availableTransport", filter = Filter.Type.ALWAYS)
	public String obtainAvailableTransport() {
		return super.obtainAvailableTransport();
	}
	
	@AsAction(name = "plantCrop")
	public void plant(int id) {
		fields[id] = new CropPlant();
		seeds--;
	}
	
	@AsAction(name = "healPlant")
	public void healPlant(int id) throws ActException {
		if (fields[id] == null || !fields[id].isPlantSick())
			throw new ActException("The field's plants are not sick!");
		fields[id].healPlant();
		pesticides--;
		
	}
	
	@AsAction(name = "healField")
	public void healField(int id) throws ActException {
		if (fields[id] == null || !fields[id].isFieldSick())
			throw new ActException("The field's soil isn't weak!");
		fields[id].healField();
		fertilizer--;
		
	}
	
	@AsAction(name = "harvest")
	public void harvest(int field_id, int order_id) throws ActException {
		//Harvest crop
		if (fields[field_id] == null || !fields[field_id].isReady())
			throw new ActException("The field isn't ready to harvest!");
		fields[field_id] = null;
		
	}
	
	@AsAction(name="sendCrops")
	public void sendCrops(String transporterName, int id, String receiverName, String item, int quantity) throws ActException {
		super.sendOrder(transporterName, id, receiverName, item, quantity);
	}
	
	
	/**
	 * 
	 */
	public void recieve(String object, int quantity) {
		if (object.equals("seeds"))
			seeds += quantity;
		else if (object.equals("fertilizer"))
			fertilizer += quantity;
		else if (object.equals("pesticides"))
			pesticides += quantity;
	}
	
	@Override
	public String toString() {
		return name;
	}

	

}
