package metro_de_paris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Table {
		
	public int getHeuristic(int departure, int arrive) {
		int x = Math.max(departure, arrive);
		int y = Math.min(departure, arrive);

		if(x == y)	return 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("metroHeuristic.txt"));
			int counter = 1;
			for(String line; (line = br.readLine()) != null; ) {
				if(x == counter) {
					String[] colums = line.split(" ");
					counter = Integer.valueOf(colums[y - 1]);
					break;
				}
		        counter++;
		    }
			br.close();
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getCost(int departure, int arrive) {
		int x = Math.max(departure, arrive);
		int y = Math.min(departure, arrive);

		if(x == y)	return 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("metroCost.txt"));
			int counter = 1;
			for(String line; (line = br.readLine()) != null; ) {
				if(x == counter) {
					String[] colums = line.split(" ");
					counter = Integer.valueOf(colums[y - 1]);
					break;
				}
		        counter++;
		    }
			br.close();
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<String> getLines(int id) {
		List<String> lines = new LinkedList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("metroLines.txt"));
			for(String line; (line = br.readLine()) != null; ) {
				int counter = 0;
				for(String colum : line.split(" ")) {
					if(counter > 0 && Integer.parseInt(colum) == id) {
						lines.add(line.split(" ")[0]);
						break;
					}
					counter++;
				}
		    }
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines;
	}

}
