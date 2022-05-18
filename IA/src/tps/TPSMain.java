package tps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TPSMain {
	
	private int[][] map;
	
	public TPSMain() {
		map = new int[10][10];
		initializeMap();
		hillClimbing();
	}
	
	public void hillClimbing() {
		List<Integer> currentRoute = randomRoute();
		int currentLength = getRouteLength(currentRoute);
		List<List<Integer>> childs = generateChilds(currentRoute);
		List<Integer> bestChild = getBestChild(childs);
		int bestChildsLength = getRouteLength(bestChild);
		
		while(bestChildsLength < currentLength) {
			currentRoute = bestChild;
			currentLength = bestChildsLength;
			childs = generateChilds(currentRoute);
			bestChild = getBestChild(childs);
			bestChildsLength = getRouteLength(bestChild);
		}
		
		for(Integer id : currentRoute) {
			System.out.println("C" + id);
		}
		
		System.out.println("Custo total: " + currentLength);
		
	}
	
	private List<Integer> getBestChild(List<List<Integer>> childs) {
		if(childs.size() < 1) {
			return null;
		}
		
		int bestChildLength = getRouteLength(childs.get(0));
		List<Integer> bestChild = childs.get(0);
		for(List<Integer> child : childs) {
			int currentChildLength = getRouteLength(child);
			if(currentChildLength < bestChildLength) {
				bestChild = child;
				bestChildLength = currentChildLength;
			}
		}
		
		return bestChild;
	}
	
	private List<List<Integer>> generateChilds(List<Integer> route) {
		List<List<Integer>> childs = new LinkedList<>();
		for(int i = 0; i < route.size(); i++) {
			for(int j = i + 1; j < route.size(); j++) {
				List<Integer> child = route;
				child.set(i, route.get(j));
				child.set(j, route.get(i));
				
				if(isValid(child)) {
					childs.add(child);
				} else {
					i += 1;
				}
			}
		}
		
		return childs;
	}
	
	private int getRouteLength(List<Integer> route) {
		int length = 0;
		for(Integer id : route) {
			length += map[id - 1][id];
		}
		return length;
	}
	
	private List<Integer> randomRoute() {
		List<Integer> randomRoute = new LinkedList<>();
		List<Integer> cities = new LinkedList<>();
		for(int i = 1; i <= 10; i++) {
			cities.add(i);
		}
		for(int i = 0; i < map.length; i++) {
			if(cities.size() > 1) {
				int randomID = new Random().nextInt(cities.size() - 1);
				int randomCity = cities.get(randomID);
				randomRoute.add(randomCity);
				cities.remove(randomID);
			}
		}
		
		if(isValid(randomRoute)) {
			return randomRoute;
		}
		
		return randomRoute();
	}
	
	private boolean isValid(List<Integer> route) {
		for(int i = 0; i < route.size() - 1; i++) {
			if(map[route.get(i)][route.get(i + 1)] != -1) {
				i += 1;
			} else {
				return false;
			}
		}
		return true;
	}
	
	private void initializeMap() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("tsp.txt"));
			int i = 0;
			for(String line; (line = br.readLine()) != null; ) {
				int j = 0;
				String[] colums = line.split(" ");
				for(String colum : colums) {
					map[i][j] = Integer.valueOf(colum);
					j++;
				}
				i++;
		    }
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
