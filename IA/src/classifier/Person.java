package classifier;

public class Person {
	
	private int id;
	private int age;
	private int weight;
	private int height;
	private int salary;
	private int clusterNumber;
	
	public Person(int id, int age, int weight, int height, int salary) {
		this.id = id;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.salary = salary;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClusterNumber() {
		return clusterNumber;
	}

	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", age=" + age + ", weight=" + weight + ", height=" + height + ", salary=" + salary
				+ ", clusterNumber=" + clusterNumber + "]";
	}
	
	

}
