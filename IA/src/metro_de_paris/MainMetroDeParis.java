package metro_de_paris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class MainMetroDeParis {
	
	//public static Map<Integer, Station> stations;
	public static Table t;
	public List<Station> unread;
	public int time;
		
	public MainMetroDeParis() {
		//stations = new HashMap<>();
		t = new Table();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int departure, arrive;
		System.out.println("Digite as estações(embarque desembarque):");
		try {
			String[] ids = reader.readLine().split(" ");
			departure = Integer.valueOf(ids[0]);
			arrive = Integer.valueOf(ids[1]);
			if(departure > 0 && departure < 15 && arrive > 0 && arrive < 15) {
				aStar(departure, arrive);
			} else {
				System.out.println("Estacoes erradas!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*for(int i = 1; i < 15; i++) {
			for(int j = 1; j < 15; j++) {
				System.out.println(i + " -> " + j);
				aStar(i, j);
				System.out.println("\n");
			}
		}*/
	}
	
	private void aStar(int departure, int arrive) {
		if(departure == arrive) {
			return;
		}
		unread = new LinkedList<>();
		List<Integer> read = new LinkedList<>();
		time = 0;
		unread.add(new Station(departure, t.getHeuristic(departure, arrive), 0));
		System.out.println("Partindo da estacao: " + unread.get(0).getId());
		time += (unread.get(0).getHeuristic() / 0.5);
		boolean reading = true;
		
		while(reading) {
			Station current = unread.get(unread.size() - 1);
			int lowerFn = Integer.MAX_VALUE;
			Station lowerStation = null;
			for(Station child : current.generateChilds(arrive)) {
				if(!read.contains(child.getId())) {
					read.add(child.getId());
					int fn = child.getHeuristic() + child.getCost();
					if(fn < lowerFn) {
						lowerFn = fn;
						lowerStation = child;
					}
				}
			}
			if(lowerStation == null) {
				aStar(arrive, departure);
				return;
			}
			
			if(lowerStation.getId() == arrive) {
				reading = false;
				unread.add(lowerStation);
				break;
			}
			
			unread.add(lowerStation);
		}
		
		for(int i = 0; i < unread.size(); i++) {
			if(i == unread.size() - 1) {
				check(i);
				time += (unread.get(i).getHeuristic() / 0.5);
				System.out.println("Chegado na estacao de destino: " + arrive);
				int hours = time / 60; //since both are ints, you get an int
				int minutes = time % 60;
				System.out.printf("Tempo total: %dh:%02dmin!", hours, minutes);
			} else if(i > 0) {
				check(i);
				time += (unread.get(i).getHeuristic() / 0.5);
				System.out.println("Passado pela estacao: " + unread.get(i).getId());
			}
		}
	}
	
	private void check(int i) {
		if(i > 1) {
			if(unread.get(i).getLines().size() > 1) { //atual tem 2 linhas
				
				if(unread.get(i - 2).getLines().size() == 1) {//avo tem 1 linha
					grandLength1(i, 2);
				} else { //avo tem 2 linhas
					grandLength2(i, 2);
				}
				
			} else { // atual tem uma linha
				
				if(unread.get(i - 2).getLines().size() == 1) {//avo tem 1 linha
					grandLength1(i, 1);
				} else { //avo tem 2 linhas
					grandLength2(i, 1);
				}
				
			}		
		}
	}
	
	private void grandLength1(int i, int sizeCurrent) {
		
		if(sizeCurrent == 1) {
			if(!unread.get(i).getLines().get(0).equalsIgnoreCase(unread.get(i - 2).getLines().get(0))) {
				System.out.println("Mudado para a linha: " + unread.get(i).getLines().get(0));
				time += 4.0;
			}
		} else { //sizeCurrent > 1
			
			boolean same = false;
			for(int j = 0; j <= 1; j++) {
				if(unread.get(i).getLines().get(j).equalsIgnoreCase(unread.get(i - 2).getLines().get(0))) {
					same = true;
					break;
				}
			}
			
			if(!same) {
				int number = 0;
				for(int j = 0; j <= 1; j++) {
					if(unread.get(i - 1).getLines().get(j).equalsIgnoreCase(unread.get(i - 2).getLines().get(0))) {//pega a cor que o pai e o avo tem em comum
						if(j == 1) {
							number = 1;
							break;
						}
					}
				}
				
				if(number == 0) {
					number = 1;
				} else {
					number = 0;
				}
				
				System.out.println("Mudado para a linha: " + unread.get(i - 1).getLines().get(number)); //linha diferente entre o pai e o avo
				time += 4.0;
			}
			
		}
	}
	
	private void grandLength2(int i, int sizeCurrent) {
		if(sizeCurrent == 1) {
			boolean same = false;
			for(int j = 0; j <= 1; j++) {
				if(unread.get(i).getLines().get(0).equalsIgnoreCase(unread.get(i - 2).getLines().get(j))) {
					same = true;
					break;
				}
			}
			if(!same) {
				System.out.println("Mudado para a linha: " + unread.get(i).getLines().get(0));
				time += 4.0;
			}
		} else { //sizeCurrent > 1
			
			boolean same = false;
			for(int j = 0; j <= 1; j++) {
				for(int k = 0; k <= 1; k++) {
					if(unread.get(i).getLines().get(j).equalsIgnoreCase(unread.get(i - 2).getLines().get(k))) {
						same = true;
						break;
					}
				}
			}
			
			if(!same) {
				int number = 0;
				for(int j = 0; j <= 1; j++) {
					for(int k = 0; k <= 1; k++) {
						if(unread.get(i - 1).getLines().get(j).equalsIgnoreCase(unread.get(i - 2).getLines().get(k))) {//pega a cor que o pai e o avo tem em comum
							if(j == 1) {
								number = 1;
							}
						}
					}
				}
				
				if(number == 0) {
					number = 1;
				} else {
					number = 0;
				}
				
				System.out.println("Mudado para a linha: " + unread.get(i - 1).getLines().get(number)); //linha diferente entre o pai e o avo
				time += 4.0;
			}
			
		}
	}

}