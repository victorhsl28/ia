package classifier;

public class Cluster {
	
	private int ageCentroid;
	private int weightCentroid;
	private int heightCentroid;
	private int salaryCentroid;
	private int clusterNumber;
	
	public Cluster(int clusterNumber, int ageCentroid, int weightCentroid, int heightCentroid, int salaryCentroid) {
		this.clusterNumber = clusterNumber;
		this.ageCentroid = ageCentroid;
		this.weightCentroid = weightCentroid;
		this.heightCentroid = heightCentroid;
		this.salaryCentroid = salaryCentroid;
	}
	public int getAgeCentroid() {
		return ageCentroid;
	}
	public void setAgeCentroid(int ageCentroid) {
		this.ageCentroid = ageCentroid;
	}
	public int getWeightCentroid() {
		return weightCentroid;
	}
	public void setWeightCentroid(int weightCentroid) {
		this.weightCentroid = weightCentroid;
	}
	public int getHeightCentroid() {
		return heightCentroid;
	}
	public void setHeightCentroid(int heightCentroid) {
		this.heightCentroid = heightCentroid;
	}
	public int getSalaryCentroid() {
		return salaryCentroid;
	}
	public void setSalaryCentroid(int salaryCentroid) {
		this.salaryCentroid = salaryCentroid;
	}
	
	@Override
	public String toString() {
		return "Cluster [ageCentroid=" + ageCentroid + ", weightCentroid=" + weightCentroid + ", heightCentroid="
				+ heightCentroid + ", salaryCentroid=" + salaryCentroid + "]";
	}
	
	public double euclidianDistance(Person p) {
		return Math.sqrt(Math.pow(getAgeCentroid() - p.getAge(), 2) + Math.pow(getWeightCentroid() - p.getWeight(), 2)
		+ Math.pow(getHeightCentroid() - p.getHeight(), 2) + Math.pow(getSalaryCentroid() - p.getSalary(), 2));
	}
	
	public void updateCentroid(Person p) {
		setAgeCentroid((getAgeCentroid() + p.getAge()) / 2);
		setWeightCentroid((getWeightCentroid() + p.getWeight()) / 2);
		setHeightCentroid((getHeightCentroid() + p.getHeight()) / 2);
		setSalaryCentroid((getSalaryCentroid() + p.getSalary()) / 2);
	}
	public int getClusterNumber() {
		return clusterNumber;
	}
	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

}
