package classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KMeans {
	
	List<Person> persons;
	List<Cluster> clusters;
	Map<Cluster, List<Person>> clusterPersons;
	
	public KMeans() {
		persons = new ArrayList<>();
		clusters = new ArrayList<>();
		clusterPersons = new HashMap<>();
		
		int clusterNumber = 4;
		generatePersons(50);
		initiate(clusterNumber);
		printPersonInformation();
		printClusterInformation();
	}
	
	
	private void generatePersons(int quantity) {
		for(int id = 0; id < quantity; id++) {
			int age = new Random().nextInt(99) + 1;
			int weight  = new Random().nextInt(50) + 50;
			int height  = new Random().nextInt(99) + 101;
			int salary  = new Random().nextInt(9000) + 1000;
			persons.add(new Person(id, age, weight, height, salary));
		}
	}
	
	private void initiate(int clusterNumber) {
		Iterator<Person> iter = persons.iterator();
		int counter = 1;
		Person p = null;
		while(iter.hasNext()) {
			p = iter.next();
			if(counter <= clusterNumber) {
				p.setClusterNumber(counter);
				initializeCluster(counter, p);
				counter++;
			} else {			
				double minDistance = Integer.MAX_VALUE;
				Cluster minCluster = null;
				
				for(Cluster c : clusters) {
					double distance = c.euclidianDistance(p);
					if(minDistance > distance) {
						minDistance = distance;
						minCluster = c;
					}
				}
				
				p.setClusterNumber(minCluster.getClusterNumber());
				minCluster.updateCentroid(p);
				clusterPersons.get(minCluster).add(p);
			}
		}
	}
	
	private void initializeCluster(int clusterNumber, Person p) {
		Cluster c = new Cluster(clusterNumber, p.getAge(), p.getWeight(), p.getHeight(), p.getSalary());
		clusters.add(c);
		List<Person> clusterPerson = new ArrayList<>();
		clusterPerson.add(p);
		clusterPersons.put(c, clusterPerson);
	}
	
	private void printPersonInformation() {
		System.out.println("Persons:");
		for(Person p : persons) {
			System.out.println(p.toString());
		}
		System.out.println("--------------------");
	}

	private void printClusterInformation() {
		System.out.println("Cluster Information: ");
		for(Map.Entry<Cluster, List<Person>> entry : clusterPersons.entrySet())  {
			System.out.println("Cluster: " + entry.getKey().getClusterNumber()); 
			System.out.println(entry.getKey().toString());
			System.out.println();
			System.out.println("Persons:");
			for(Person p : entry.getValue()) {
				System.out.println(p.toString());
			}
			System.out.println("--------------------");
		}
	}
	

}
