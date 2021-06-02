package utils;

import java.util.Random;

public class CropPlant {
	
	private int counterReady;
	private boolean problemPlant;
	private boolean problemField;
	private Random rng;
	private long next_update;
	
	private final double PROBLEM_PROB = 0.1;
	private final long PERIOD_TIME = 100;
	
	public CropPlant() {
		rng = new Random();
		
		problemPlant = problemField = false;
		counterReady = rng.nextInt(2) + 4;
		next_update = System.currentTimeMillis() + PERIOD_TIME;
	}
	
	public void updateState() {
		// Check if the counter has reached 0
		if (counterReady > 0)
			// only continue the counter if the plant is healthy
			if (!(problemPlant || problemField)) {
				//Check the time period has ellapsed
				if (next_update < System.currentTimeMillis()) {
					counterReady--;
					// randomly generate problem
					if (rng.nextFloat() < PROBLEM_PROB) problemPlant = true;
					if (rng.nextFloat() < PROBLEM_PROB) problemField = true;
					// Update timer
					next_update = System.currentTimeMillis() + PERIOD_TIME;
				}
				
			}
	}
	
	public boolean isPlantSick() {return problemPlant;}
	public boolean isFieldSick() {return problemField;}
	
	public void healPlant() {problemPlant = false;}
	public void healField() {problemField = false;}
	
	public boolean isReady() {return (counterReady == 0) && !problemPlant && !problemField;}

}
