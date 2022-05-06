package metro_de_paris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Station {
	
	private int id, heuristic, cost;
	private List<String> lines;
	
	public Station(int id, int heuristic, int cost) {
		this.id = id;
		this.heuristic = heuristic;
		this.lines = MainMetroDeParis.t.getLines(id);
		MainMetroDeParis.stations.put(id, this);
	}
	
	List<Station> generateChilds(int arrive) {
		List<Station> childs = new LinkedList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("metroCost.txt"));
			int counter = 1;
			for(String line; (line = br.readLine()) != null; ) {
				if(id == counter) {
					int columCounter = 1;
					for(String colum : line.split(" ")) {
						
						if(Integer.parseInt(colum) != 0) {
							childs.add(new Station(columCounter, MainMetroDeParis.t.getHeuristic(columCounter, arrive), MainMetroDeParis.t.getCost(columCounter, arrive)));
						}
						columCounter++;
					}
					break;
				}
		        counter++;
		    }
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return childs;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	
}
