public class EmployeeManagementSystem {
    static class Employee {
        int employeeId;
        String name;
        String position;
        double salary;

        public Employee(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return employeeId + " - " + name + " - " + position + " - Rs." + salary;
        }
    }

    static final int MAX = 100;
    static Employee[] employees = new Employee[MAX];
    static int size = 0;

    public static void addEmployee(int id, String name, String position, double salary) {
        if (size < MAX) {
            employees[size++] = new Employee(id, name, position, salary);
            System.out.println("Employee added.");
        } else {
            System.out.println("Employee array is full.");
        }
    }

    public static Employee searchEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == id) {
                return employees[i];
            }
        }
        return null;
    }

    public static void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    public static void deleteEmployee(int id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                employees[i] = employees[i + 1];
            }
            employees[--size] = null;
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void main(String[] args) {
        addEmployee(101, "Alice", "Manager", 75000);
        addEmployee(102, "Bob", "Engineer", 55000);
        addEmployee(103, "Charlie", "Designer", 60000);

        System.out.println("\nAll Employees:");
        traverseEmployees();

        System.out.println("\nSearching for Employee ID 102:");
        Employee e = searchEmployee(102);
        System.out.println(e != null ? "Found: " + e : "Not found");

        System.out.println("\nDeleting Employee ID 102:");
        deleteEmployee(102);

        System.out.println("\nAll Employees After Deletion:");
        traverseEmployees();
    }
}

