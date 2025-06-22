class Task {
    int taskId;
    String taskName;
    String status;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskManagementSystem {
    private TaskNode head;

    // Add task at the end
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Task added: " + task.taskName);
    }

    // Traverse tasks
    public void viewTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        TaskNode current = head;
        while (current != null) {
            System.out.println("Task ID: " + current.task.taskId +
                               ", Name: " + current.task.taskName +
                               ", Status: " + current.task.status);
            current = current.next;
        }
    }

    // Search by task ID
    public void searchTask(int id) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.taskId == id) {
                System.out.println("Found Task -> Name: " + current.task.taskName + ", Status: " + current.task.status);
                return;
            }
            current = current.next;
        }
        System.out.println("Task with ID " + id + " not found.");
    }

    // Delete by task ID
    public void deleteTask(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        if (head.task.taskId == id) {
            head = head.next;
            System.out.println("Task deleted with ID: " + id);
            return;
        }

        TaskNode current = head;
        while (current.next != null && current.next.task.taskId != id) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("Task deleted with ID: " + id);
        } else {
            System.out.println("Task not found with ID: " + id);
        }
    }
}

public class TaskManage {
    public static void main(String[] args) {
        TaskManagementSystem system = new TaskManagementSystem();

        system.addTask(new Task(1, "Design module", "Pending"));
        system.addTask(new Task(2, "Implement logic", "In Progress"));
        system.addTask(new Task(3, "Testing", "Pending"));

        System.out.println("\nAll Tasks:");
        system.viewTasks();

        System.out.println("\nSearch Task ID 2:");
        system.searchTask(2);

        System.out.println("\nDelete Task ID 1:");
        system.deleteTask(1);

        System.out.println("\nAll Tasks After Deletion:");
        system.viewTasks();
    }
}
