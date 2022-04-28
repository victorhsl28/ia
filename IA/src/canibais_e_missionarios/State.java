package canibais_e_missionarios;

import java.util.LinkedList;
import java.util.List;

import canibais_e_missionarios.MainCanibaisEMissionarios.Boat;

public class State {
	
	private int left_missionaries, right_missionaries, left_cannibals, right_cannibals;
	private Boat boat;
	private State father;
	private Movement step;
		
	public State(int left_missionaries, int right_missionaries, int left_cannibals, int right_cannibals, Boat boat) {
		this.left_missionaries = left_missionaries;
		this.right_missionaries = right_missionaries;
		this.left_cannibals = left_cannibals;
		this.right_cannibals = right_cannibals;
		this.boat = boat;
	}
	
	public boolean isSolution() {
		if(right_missionaries == 3 && left_missionaries == 0 && right_cannibals == 3 && left_cannibals == 0)	return true;
		
		return false;
	}
	
	public boolean onBound() {
		if((left_missionaries <= 3 && left_missionaries >= 0) &&
		(right_missionaries <= 3 && right_missionaries >= 0) &&
		(left_cannibals <= 3 && left_cannibals >= 0) &&
		(right_cannibals <= 3 && right_cannibals >= 0) &&
		(left_missionaries == 0 || left_missionaries >= left_cannibals) &&
		(right_missionaries == 0 || right_missionaries >= right_cannibals))		return true;
		
		
		return false;
	}
	
	public List<State> generateChilds() {
		
		List<State> childs = new LinkedList<>();
		
		for(Movement movement : Movement.movements) {
			
			int l_m = 0, r_m = 0, l_c = 0, r_c = 0;
			Boat tempBoat;
			
			if(boat == Boat.LEFT) {
				tempBoat = Boat.RIGHT;
			} else {
				tempBoat = Boat.LEFT;
			}
			
			if(tempBoat == Boat.RIGHT) {
				
				l_m = left_missionaries - movement.getMissionary();
				r_m = right_missionaries + movement.getMissionary();
				
				l_c = left_cannibals - movement.getCannibal();
				r_c = right_cannibals + movement.getCannibal();
				
			} else {
				
				l_m = left_missionaries + movement.getMissionary();
				r_m = right_missionaries - movement.getMissionary();
				
				l_c = left_cannibals + movement.getCannibal();
				r_c = right_cannibals - movement.getCannibal();
			}
			
			State childState = new State(l_m, r_m, l_c, r_c, tempBoat);
			childState.setFather(this);
			childState.setStep(movement);
			if(childState.onBound())	childs.add(childState);
		}
		
		return childs;
	}
	
	public void print() {
		System.out.println("\nMissionarios a esqueda: " + left_missionaries);
		System.out.println("Missionarios a direita: " + right_missionaries);
		System.out.println("Canibais a esqueda: " + left_cannibals);
		System.out.println("Canibais a direita: " + right_cannibals);
		System.out.println("Barco: " + boat.toString());
		System.out.println("Passo: [" + step.getMissionary() + ", " + step.getCannibal() + "]");
		
	}
		
	public int getleft_missionaries() {
		return left_missionaries;
	}

	public void setleft_missionaries(int left_missionaries) {
		this.left_missionaries = left_missionaries;
	}

	public int getright_missionaries() {
		return right_missionaries;
	}

	public void setright_missionaries(int right_missionaries) {
		this.right_missionaries = right_missionaries;
	}

	public int getright_cannibals() {
		return right_cannibals;
	}

	public void setright_cannibals(int right_cannibals) {
		this.right_cannibals = right_cannibals;
	}

	public int getleft_cannibals() {
		return left_cannibals;
	}

	public void setleft_cannibals(int left_cannibals) {
		this.left_cannibals = left_cannibals;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}
	
	public State getFather() {
		return father;
	}

	public void setFather(State father) {
		this.father = father;
	}
		
	public Movement getStep() {
		return step;
	}

	public void setStep(Movement step) {
		this.step = step;
	}
	
}
