package duke.task;

import java.util.ArrayList;

public class TaskList {

    private static final int DESCRIPTION = 0;
    private static final int BY = 1;
    private static final int AT = 1;

    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    private void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task getLastTask() {
        return tasks.get(getTaskCount() - 1);
    }

    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    public void markTaskAsDone(int taskNumber) {
        getTask(taskNumber).setIsDone(true);
    }

    public Task deleteTask(int taskNumber) {
        Task deletedTask = removeTask(taskNumber);
        taskCount--;
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
