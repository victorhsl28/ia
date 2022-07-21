package machine_learning;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class KNN {

	public String knn(Flower f, List<Flower> trainingData, int k) {
        Map<Double, Flower> distances = new TreeMap<>();

        for (Flower flower : trainingData)
        	distances.put(distance(f, flower), flower);

        int i = 0;
        Map<String, Integer> types = new TreeMap<>();
        for (Map.Entry<Double, Flower> neighbour : distances.entrySet()) {
            if (i == k) break;

            types.put(neighbour.getValue().getType(), (types.get(neighbour.getValue().getType()) == null ? 0 : types.get(neighbour.getValue().getType())) + 1);
            
            i++;
        }

        return types.entrySet().iterator().next().getKey();
    }
    
    private double distance(Flower f1, Flower f2) {
		return Math.sqrt(Math.pow((f2.getPetalLength() - f1.getPetalLength()), 2) + Math.pow((f2.getPetalWidth() - f1.getPetalWidth()), 2) + 
        		Math.pow((f2.getSepalLength() - f1.getSepalLength()), 2) + Math.pow((f2.getSepalWidth() - f1.getSepalWidth()), 2));
    }
}
