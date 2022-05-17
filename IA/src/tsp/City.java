package tsp;

import java.io.BufferedReader;
import java.io.FileReader;

public class City {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getDistance(City city) {
		int x = Math.max(id, city.getId());
		int y = Math.min(id, city.getId());

		if(x == y)	return 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("tsp.txt"));
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
	
}
