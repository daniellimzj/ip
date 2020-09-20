package duke.task;

import java.util.ArrayList;

public class TaskList {

    private static final int DESCRIPTION = 0;
    private static final int BY = 1;
    private static final int AT = 1;

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void addTask(Task task) {
        tasks.add(task);
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

    public void markTaskAsDone(int taskNumber) {
        getTask(taskNumber).setIsDone(true);
    }

    public Task deleteTask(int taskNumber) {
        Task deletedTask = removeTask(taskNumber);
        Task.decreaseTaskCount();
        return deletedTask;
    }

    public void addToDo(String todo) {
        addTask(new ToDo(todo));
    }

    public void addDeadline(String[] params) {
        addTask(new Deadline(params[DESCRIPTION], params[BY]));
    }

    public void addEvent(String[] params) {
        addTask(new Event(params[DESCRIPTION], params[AT]));
    }
}
