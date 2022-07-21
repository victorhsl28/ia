package machine_learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainML {
	
	List<Flower> flowers;
	
	public MainML() {
		flowers = new LinkedList<>();
		readFile("iris.csv");
		List<Flower> training = getTrainingFlowers(flowers);
		List<Flower> test = getTestFlowers(flowers);
		int tests = 0, success = 0;
		for (int k = 1; k < 150; k ++) {
			KNN knn = new KNN();
			for(Flower tf : test) {
				if(knn.knn(tf, training, k).equalsIgnoreCase(tf.getType()))
					success++;
				
				tests++;
			}
		}
		
		System.out.println("Tests: " + tests);
		System.out.println("Success: " + success);
		System.out.println("Accuracy: " + ((success * 100) / tests) + "%");
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
