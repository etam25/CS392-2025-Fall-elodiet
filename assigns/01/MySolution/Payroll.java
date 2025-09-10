
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;

    public Payroll() {
        int initialSize = 2;
        people = new Employee[initialSize];
        this.maximum_size = people.length;
        this.current_size = 0;


    }
    /* 
    return how many people are in the array
   This is stored in the current_size variable
    */
    public int size() {
        return current_size;
    }
    
    public void add_employee(Employee newbie) {
        if (this.current_size >= this.maximum_size) { 
            // increase the size of the array
            increasePeopleArraySize();
        }
        // set the employee in the people array 
        people[current_size] = newbie;
        current_size = current_size + 1;

    }

    private void increasePeopleArraySize() {
        // increases the size of the people array by 1
        Employee[] newEmployeeArray = new Employee[maximum_size + 1];

        // copy all values from existing people array to new employee array
        for(int i = 0; i < people.length; i++) {
            Employee exisitingEmployee = people[i];
            newEmployeeArray[i] = exisitingEmployee; 
        }
        this.people = newEmployeeArray;
        // replace people array with new one
        this.maximum_size = people.length;

    }

    public void remove_employee(int i) throws EmployeeIndexException {
        // make sure i < current_size
        if(i >= current_size) {
            // asking for i beyond the current_size of the array
            throw new EmployeeIndexException();
        }
        Employee matchedEmployee = this.people[i];
        if(matchedEmployee == null) {
            // employee does not exist
            throw new EmployeeIndexException();

        }
        this.people[i] = null;
        Employee[] newArray = new Employee[maximum_size];
        for(int j = 0; j < i; j++) {
            newArray[j] = this.people[j];
        }
        for(int j = i + 1; j < maximum_size; j++) {
            newArray[j - 1] = this.people[j]; // shift everything by 1
        }
        this.people = newArray;
        this.current_size = this.current_size - 1;
    }
    
    public int find_employee(String name) throws EmployeeNotFoundException {
        if(name == null) {
            throw new EmployeeNotFoundException();
        }
        for(int i = 0; i < current_size; i++ ) {
            Employee employee = this.people[i];
            if(name.equals(employee.name)) {
                return i;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public void print() {
        for(int i = 0; i < current_size; i++) {
            Employee employee = this.people[i];
            if(employee != null) {
                System.out.println(employee.toString());
            }

        }
    }

    public void add_payroll(Payroll source) {
        int payrollToAddSize = source.size();
        for(int i = 0; i < payrollToAddSize; i++) {
            Employee employeeToAdd = source.people[i];
            this.add_employee(employeeToAdd);
        }
    }

    public void copy_payroll(Payroll source) {
        this.people = source.people;
        this.maximum_size = source.maximum_size;
        this.current_size = source.current_size;
    }

    private Employee people[];
    private int maximum_size;
    private int current_size;
}
