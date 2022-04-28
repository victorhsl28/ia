package canibais_e_missionarios;

import java.util.ArrayList;
import java.util.List;

public class MainCanibaisEMissionarios {
	
	List<State> path = new ArrayList<>();
		
	public MainCanibaisEMissionarios() {
		State.setupMovements();
		checkSolution(new State(3, 0, 3, 0, Boat.LEFT));
		System.out.println("Size: " + path.size());
		for(State state : path) {
			state.print();
		}
	}
	
	public void checkSolution(State state) {
		if(state.isSolution()) {
			while(state.getFather() != null) {
				path.add(state);
				state = state.getFather();
			}
			
		} else {
			for(State child : state.generateChilds()) {
				checkSolution(child);
			}
		}
	}
			
	public static enum Boat {
		LEFT, RIGHT;
	}

}
