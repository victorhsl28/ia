package machine_learning;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

public class KNN {
	
	Map<Integer, Double> distances;
	
	public KNN() {
		distances = new HashMap<>();
	}

    public String knn(Flower test, List<Flower> trainingFlowers, int kn) {
        for(Flower training : trainingFlowers) 
            distances.put(training.getId(), distance(training, test));
        
        Map<Integer, Double> sortedDistances = distances.entrySet().stream().sorted(Map.Entry.comparingByValue())
        		.collect(Collectors.toMap(f -> f.getKey(), f -> f.getValue(), (f1, f2) -> f2, LinkedHashMap::new));

        int i = 0;
        Vector<String> types = new Vector<>();
        for (Map.Entry<Integer, Double> entry : sortedDistances.entrySet()) {
            if (i == kn)	break;

            if (entry.getKey() < trainingFlowers.size()) {
            	types.addElement(trainingFlowers.get(entry.getKey()).getType());
                i++;
            }
        }

        return types.stream().collect(Collectors.groupingBy(o -> o, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).
        		get().getKey();
	}
    
    private double distance(Flower f1, Flower f2) {
		return Math.sqrt(Math.pow((f2.getPetalLength() - f1.getPetalLength()), 2) + Math.pow((f2.getPetalWidth() - f1.getPetalWidth()), 2) + 
        		Math.pow((f2.getSepalLength() - f1.getSepalLength()), 2) + Math.pow((f2.getSepalWidth() - f1.getSepalWidth()), 2));
    }
}
