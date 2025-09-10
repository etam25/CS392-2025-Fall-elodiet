
public class Employee {
	public String name;
	public int ID;
	public double salary;

	@Override
	public String toString() {
		return "name: " + this.name + ", ID: " + this.ID + ", salary: " + this.salary;
	}
}
