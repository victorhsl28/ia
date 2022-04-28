package canibais_e_missionarios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainCanibaisEMissionarios {
	
	static List<State> path = new ArrayList<>();

	public MainCanibaisEMissionarios() {
		Movement.setup();
		run();
		System.out.println("Passos: " + path.size());
		for(State s : path) {
			s.print();
		}
	}
	
	void run() {
		List<State> list = new LinkedList<>();
		list.add(new State(3, 0, 3, 0, Boat.LEFT));
		for(int i = 0; i < 10000; i++) {
			State state = list.get(i);
			if(state.isSolution()) {
				while(state.getFather() != null) {
					path.add(state);
					state = state.getFather();
				}
				return;
			} else {
				for(State child : state.generateChilds()) {
					list.add(list.size() - 1, child);
				}
			}
		}
	}
				
	public static enum Boat {
		LEFT, RIGHT;
	}

}
