package duke.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public Task getLastTask() {
        return tasks.get(Task.getTaskCount() - 1);
    }

    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAsDone(int taskNumber) {
        getTask(taskNumber).setIsDone(true);
    }

    public Task deleteTask(int taskNumber) {
        Task deletedTask = removeTask(taskNumber);
        Task.decreaseTaskCount();
        return deletedTask;
    }
}
