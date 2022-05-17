package tsp;

import java.util.List;

public class Route {
	//https://www.youtube.com/watch?v=eGT9RuhclUE
	
	private List<City> cities;
	
	public int getTotalDistance() {
		int size = cities.size();
		return cities.stream().mapToInt(x -> {
			int id = cities.indexOf(x), total = 0;
			if(id < size - 1)
				total = x.getDistance(cities.get(id + 1));
			return total;
		}).sum() + cities.get(size - 1).getDistance(cities.get(0));
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public Route generateChilds(Route route) {
		int id1 = 0, id2 = 0;
		while(id1 == id2) {
			id1 = (int) (route.getCities().size() * Math.random());
			id2 = (int) (route.getCities().size() * Math.random());
		}
		City c1 = route.getCities().get(id1);
		City c2 = route.getCities().get(id2);
		route.getCities().set(id1, c2);
		route.getCities().set(id2, c1);
		return route;
	}

}
