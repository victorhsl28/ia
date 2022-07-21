package machine_learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainML {
	
	List<Flower> flowers;
	Map<String, Integer> confusionMatrix;
	
	public MainML() {
		flowers = new LinkedList<>();
		readFile("iris.csv");
		List<Flower> training = getTrainingFlowers(flowers);
		List<Flower> test = getTestFlowers(flowers);
		int tests = 0, success = 0;
		
		confusionMatrix = new HashMap<>();
		confusionMatrix.put("Iris-setosa,Iris-setosa", 0);
		confusionMatrix.put("Iris-setosa,Iris-versicolor", 0);
		confusionMatrix.put("Iris-setosa,Iris-virginica", 0);
		
		confusionMatrix.put("Iris-versicolor,Iris-versicolor", 0);
		confusionMatrix.put("Iris-versicolor,Iris-setosa", 0);
		confusionMatrix.put("Iris-versicolor,Iris-virginica", 0);
		
		confusionMatrix.put("Iris-virginica,Iris-virginica", 0);
		confusionMatrix.put("Iris-virginica,Iris-setosa", 0);
		confusionMatrix.put("Iris-virginica,Iris-versicolor", 0);
		
		for (int k = 1; k < 150; k += 1) {
			KNN knn = new KNN();
			for(Flower tf : test) {
				String result = knn.knn(tf, training, k);
				String type = tf.getType();
				
				if(result.equalsIgnoreCase(type)) {
					success++;
				}
				
				tests++;
				
				if(result.equalsIgnoreCase(type)) {
					if(result.equalsIgnoreCase("Iris-setosa")) {
						add("Iris-setosa,Iris-setosa");
					} 
					if(result.equalsIgnoreCase("Iris-virginica")) {
						add("Iris-virginica,Iris-virginica");
					} 
					if(result.equalsIgnoreCase("Iris-versicolor")) {
						add("Iris-versicolor,Iris-versicolor");
					}
				} else {
					if(type.equalsIgnoreCase("Iris-setosa")) {
						if(result.equalsIgnoreCase("Iris-versicolor")) {
							add("Iris-setosa,Iris-versicolor");
						} 
						
						if(result.equalsIgnoreCase("Iris-virginica")) {
							add("Iris-setosa,Iris-virginica");
						}
					} 
					if(type.equalsIgnoreCase("Iris-versicolor")) {
						if(result.equalsIgnoreCase("Iris-setosa")) {
							add("Iris-versicolor,Iris-setosa");
						} 
						
						if(result.equalsIgnoreCase("Iris-virginica")) {
							add("Iris-versicolor,Iris-virginica");
						}
					} 
					
					if(type.equalsIgnoreCase("Iris-virginica")) {
						if(result.equalsIgnoreCase("Iris-setosa")) {
							add("Iris-virginica,Iris-setosa");
						} 
						
						if(result.equalsIgnoreCase("Iris-versicolor")) {
							add("Iris-virginica,Iris-versicolor");
						}
					}
				}
			}
		}
		
		System.out.println("Tests: " + tests);
		System.out.println("Success: " + success);
		System.out.println("Accuracy: " + ((success * 100) / tests) + "%");
		
		System.out.println("	setosa | versicolor | virginica");
		System.out.println("setosa " + get("Iris-setosa,Iris-setosa") + " | " + get("Iris-setosa,Iris-versicolor") + " | " + get("Iris-setosa,Iris-virginica"));
		System.out.println("versicolor " + get("Iris-versicolor,Iris-setosa") + " | " + get("Iris-versicolor,Iris-versicolor") + " | " + get("Iris-versicolor,Iris-virginica"));
		System.out.println("virginica " + get("Iris-virginica,Iris-setosa") + " | " + get("Iris-virginica,Iris-versicolor") + " | " + get("Iris-virginica,Iris-virginica"));
	}
	
	private void add(String type) {
		confusionMatrix.replace(type, (confusionMatrix.get(type) + 1));
	}
	
	private int get(String type) {
		return confusionMatrix.get(type);
	}
		
	private void readFile(String path) {
		try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.US_ASCII)) {
			int id = 0;
			for(String line; (line = br.readLine()) != null; ) {
				String[] colums = line.split(",");
				flowers.add(new Flower(id, colums[4], Double.valueOf(colums[0]), Double.valueOf(colums[1]), Double.valueOf(colums[2]), Double.valueOf(colums[3])));
			}
			id++;
		} catch (IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	private List<Flower> getTrainingFlowers(List<Flower> flowers) {
        List<Flower> training = new ArrayList<Flower>();
        for(int i = 0; i < 75; i++) {
        	int random = new Random().nextInt(flowers.size() - 1);
        	training.add(flowers.get(random));
        	flowers.remove(random);
        }
        return training;
    }

	private List<Flower> getTestFlowers(List<Flower> flowers) {
        List<Flower> test = new ArrayList<Flower>();
        test.addAll(flowers.subList(0, 75));
        return test;
    }

}
