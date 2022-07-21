package machine_learning;

public class Flower {
	
	private int id;
	private String type;
	private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    
	public Flower(int id, String type, double sepalLength, double sepalWidth, double petalLength, double petalWidth) {
		this.id = id;
		this.type = type;
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSepalLength() {
		return sepalLength;
	}
	public void setSepalLength(double sepalLength) {
		this.sepalLength = sepalLength;
	}
	public double getSepalWidth() {
		return sepalWidth;
	}
	public void setSepalWidth(double sepalWidth) {
		this.sepalWidth = sepalWidth;
	}
	public double getPetalLength() {
		return petalLength;
	}
	public void setPetalLength(double petalLength) {
		this.petalLength = petalLength;
	}
	public double getPetalWidth() {
		return petalWidth;
	}
	public void setPetalWidth(double petalWidth) {
		this.petalWidth = petalWidth;
	}

	@Override
	public String toString() {
		return "Flower [id=" + id + ", type=" + type + ", sepalLength=" + sepalLength + ", sepalWidth=" + sepalWidth
				+ ", petalLength=" + petalLength + ", petalWidth=" + petalWidth + ", getType()=" + getType()
				+ ", getSepalLength()=" + getSepalLength() + ", getSepalWidth()=" + getSepalWidth()
				+ ", getPetalLength()=" + getPetalLength() + ", getPetalWidth()=" + getPetalWidth() + ", getId()="
				+ getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
