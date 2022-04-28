package canibais_e_missionarios;

public class Movement {
	
	private int missionary, cannibal;
	
	public Movement(int missionary, int cannibal) {
		this.setMissionary(missionary);
		this.setCannibal(cannibal);
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
	
}
