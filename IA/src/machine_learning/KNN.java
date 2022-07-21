package machine_learning;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class KNN {

	public String knn(Flower f, List<Flower> trainingData, int k) {
        Map<Double, Flower> neighbourDistance = new TreeMap<>();

        for (Flower flower : trainingData) {
            neighbourDistance.put(distance(f, flower), flower);
        }

        int counter = 0;
        Map<String, Integer> irisTypes = new TreeMap<>();

        for (Map.Entry<Double, Flower> neighbour : neighbourDistance.entrySet()) {
            if (counter == k) break;

            irisTypes.put(neighbour.getValue().getType(),
                    (irisTypes.get(neighbour.getValue().getType()) == null ?
                            0 : irisTypes.get(neighbour.getValue().getType())) + 1);

            counter++;
        }

        return irisTypes.entrySet().iterator().next().getKey();
    }
    
    private double distance(Flower f1, Flower f2) {
		return Math.sqrt(Math.pow((f2.getPetalLength() - f1.getPetalLength()), 2) + Math.pow((f2.getPetalWidth() - f1.getPetalWidth()), 2) + 
        		Math.pow((f2.getSepalLength() - f1.getSepalLength()), 2) + Math.pow((f2.getSepalWidth() - f1.getSepalWidth()), 2));
    }
}
