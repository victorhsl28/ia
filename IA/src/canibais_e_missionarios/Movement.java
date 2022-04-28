package canibais_e_missionarios;

import java.util.LinkedList;
import java.util.List;

public class Movement {
	
	private int missionary, cannibal;
	
	public static List<Movement> movements = new LinkedList<>();
	
	public Movement(int missionary, int cannibal) {
		this.missionary = missionary;
		this.cannibal = cannibal;
	}

	public int getMissionary() {
		return missionary;
	}

	public void setMissionary(int missionary) {
		this.missionary = missionary;
	}

	public int getCannibal() {
		return cannibal;
	}

	public void setCannibal(int cannibal) {
		this.cannibal = cannibal;
	}
	
	public static void setup() {
		movements.add(new Movement(1, 0));
		movements.add(new Movement(0, 1));
		movements.add(new Movement(2, 0));
		movements.add(new Movement(0, 2));
		movements.add(new Movement(1, 1));
	}
	
}
