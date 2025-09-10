public class TestPayroll {
    public static void main(String [] args) {
        Payroll payroll = new Payroll();
        int size = payroll.size(); // size should be 0
        if(size != 0) {
            throw new RuntimeException("Expected size should be zero");
        }
        // add new employee
        Employee employee = new Employee();
        employee.name = "Elodie";
        employee.ID = 123;
        employee.salary = 12456789.123;

        payroll.add_employee(employee);
        size = payroll.size();
        if(size != 1) {
            throw new RuntimeException("Expected size should be 1");
        }
        // should print one employee
        payroll.print();

        System.out.println("\n### add new employees");
        for(int i = 0; i < 5; i++) {
            Employee newEmployee = new Employee();
            newEmployee.name = "Bob" + i;
            newEmployee.ID = 123 + i;
            newEmployee.salary = 123456789.123 + i;

            payroll.add_employee(newEmployee);
        }
        payroll.print();

        size = payroll.size();
        if(size != 6) {
            throw new RuntimeException("Expected size should be 6");
        }
        System.out.println("have " + size + " employees now");

        System.out.println("\n### Remove employee 0");
        try {
            payroll.remove_employee(0);

        } 
        catch (EmployeeIndexException e) {
            // this should not happen 
            throw new RuntimeException("Should not have caught an exception removing this item");
        }
        size = payroll.size();
        if(size != 5) {
            throw new RuntimeException("Expected size should be 5, got " + size);
        }

        System.out.println("have " + size + " employees now");

        System.out.println("\n### Find employee 'Bob 3");
        payroll.print();

        try {
        String personToFind = "Bob 3";
        int position = payroll.find_employee(personToFind);

        if(position != 3) {
            throw new RuntimeException("Did not match position expected of " + position);
        }
        } 
        catch(EmployeeNotFoundException e) {
            // should not have thrown exception
            throw new RuntimeException("Should not have thrown exception");
        }  

        System.out.println("\n### Add payroll");
        Payroll newPayroll = new Payroll();
        Employee employeeToAdd = new Employee();
        employeeToAdd.name = "Tony";
        employeeToAdd.ID = 25;
        employeeToAdd.salary = 12353311.1;
        newPayroll.add_employee(employeeToAdd);

        payroll.add_payroll(newPayroll);

        //Should be Tony in here
        payroll.print();

        System.out.println("\n### Copy payroll");
        //copy payroll to newPayroll
        newPayroll.copy_payroll(payroll);
        if(newPayroll.size() != 6) {
            throw new RuntimeException("expected newPayroll to have 6 employees");
        }
        newPayroll.print();
    }
}
