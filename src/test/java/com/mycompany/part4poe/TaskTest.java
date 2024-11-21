import org.junit.Task;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TaskTest {

    // Task class
    static class Task {
        private String developer;
        private String taskName;
        private String taskID;
        private int taskDuration;
        private String taskStatus;

        public Task(String developer, String taskName, String taskID, int taskDuration, String taskStatus) {
            this.developer = developer;
            this.taskName = taskName;
            this.taskID = taskID;
            this.taskDuration = taskDuration;
            this.taskStatus = taskStatus;
        }

        public String getDeveloper() {
            return developer;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getTaskID() {
            return taskID;
        }

        public int getTaskDuration() {
            return taskDuration;
        }

        public String getTaskStatus() {
            return taskStatus;
        }
    }

    // TaskManager class
    static class TaskManager {
        private List<Task> tasks = new ArrayList<>();

        public void addTask(String developer, String taskName, String taskID, int taskDuration, String taskStatus) {
            Task newTask = new Task(developer, taskName, taskID, taskDuration, taskStatus);
            tasks.add(newTask);
        }

        public List<String> getAllDevelopers() {
            List<String> developers = new ArrayList<>();
            for (Task task : tasks) {
                developers.add(task.getDeveloper());
            }
            return developers;
        }

        public Task getLongestTask() {
            if (tasks.isEmpty()) return null;

            Task longestTask = tasks.get(0);
            for (Task task : tasks) {
                if (task.getTaskDuration() > longestTask.getTaskDuration()) {
                    longestTask = task;
                }
            }
            return longestTask;
        }

        public Task searchTaskByName(String taskName) {
            for (Task task : tasks) {
                if (task.getTaskName().equalsIgnoreCase(taskName)) {
                    return task;
                }
            }
            return null;
        }

        public List<Task> getTasksByDeveloper(String developer) {
            List<Task> developerTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getDeveloper().equalsIgnoreCase(developer)) {
                    developerTasks.add(task);
                }
            }
            return developerTasks;
        }

        public boolean deleteTaskByName(String taskName) {
            return tasks.removeIf(task -> task.getTaskName().equalsIgnoreCase(taskName));
        }

        public String displayAllTasks() {
            StringBuilder report = new StringBuilder();
            for (Task task : tasks) {
                report.append("Developer: ").append(task.getDeveloper())
                        .append(", Task: ").append(task.getTaskName())
                        .append(", ID: ").append(task.getTaskID())
                        .append(", Duration: ").append(task.getTaskDuration())
                        .append(", Status: ").append(task.getTaskStatus())
                        .append("\n");
            }
            return report.toString();
        }
    }

    // Unit Tests
    @Test
    public void testDeveloperArrayPopulated() {
        TaskManager manager = new TaskManager();
        manager.addTask("Mike Smith", "Create Login", "T001", 5, "To Do");
        manager.addTask("Edward Harrington", "Create Reports", "T002", 8, "Done");
        manager.addTask("Samantha Paulson", "Add Features", "T003", 4, "Doing");
        manager.addTask("Glenda Oberholzer", "Fix Bugs", "T004", 11, "Done");

        List<String> developers = manager.getAllDevelopers();
        assertEquals(List.of("Mike Smith", "Edward Harrington", "Samantha Paulson", "Glenda Oberholzer"), developers);
    }

    @Test
    public void testLongestTask() {
        TaskManager manager = new TaskManager();
        manager.addTask("Mike Smith", "Create Login", "T001", 5, "To Do");
        manager.addTask("Edward Harrington", "Create Reports", "T002", 8, "Done");
        manager.addTask("Glenda Oberholzer", "Fix Bugs", "T003", 11, "Done");

        Task longestTask = manager.getLongestTask();
        assertNotNull(longestTask);
        assertEquals("Glenda Oberholzer", longestTask.getDeveloper());
        assertEquals(11, longestTask.getTaskDuration());
    }

    @Test
    public void testSearchTaskByName() {
        TaskManager manager = new TaskManager();
        manager.addTask("Mike Smith", "Create Login", "T001", 5, "To Do");
        manager.addTask("Edward Harrington", "Create Reports", "T002", 8, "Done");

        Task task = manager.searchTaskByName("Create Login");
        assertNotNull(task);
        assertEquals("Mike Smith", task.getDeveloper());
        assertEquals("Create Login", task.getTaskName());
    }

    @Test
    public void testTasksByDeveloper() {
        TaskManager manager = new TaskManager();
        manager.addTask("Samantha Paulson", "Create Reports", "T001", 8, "Done");
        manager.addTask("Samantha Paulson", "Add Features", "T002", 4, "Doing");
        manager.addTask("Glenda Oberholzer", "Fix Bugs", "T003", 11, "Done");

        List<Task> tasks = manager.getTasksByDeveloper("Samantha Paulson");
        assertEquals(2, tasks.size());
        assertTrue(tasks.stream().anyMatch(task -> task.getTaskName().equals("Create Reports")));
        assertTrue(tasks.stream().anyMatch(task -> task.getTaskName().equals("Add Features")));
    }

    @Test
    public void testDeleteTask() {
        TaskManager manager = new TaskManager();
        manager.addTask("Samantha Paulson", "Create Reports", "T001", 8, "Done");

        assertTrue(manager.deleteTaskByName("Create Reports"));
        assertNull(manager.searchTaskByName("Create Reports"));
    }

    @Test
    public void testDisplayAllTasks() {
        TaskManager manager = new TaskManager();
        manager.addTask("Mike Smith", "Create Login", "T001", 5, "To Do");
        manager.addTask("Edward Harrington", "Create Reports", "T002", 8, "Done");

        String report = manager.displayAllTasks();
        assertTrue(report.contains("Mike Smith"));
        assertTrue(report.contains("Edward Harrington"));
        assertTrue(report.contains("Create Login"));
        assertTrue(report.contains("Create Reports"));
    }
}
