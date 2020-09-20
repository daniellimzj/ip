package duke.task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

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

    private Task removeTask(int i) {
        return tasks.remove(i);
    }

    /**
     * Returns the Task at the specified index of the TaskList.
     * @param taskNumber Index of the required Task.
     * @return Task at specified index.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     * @return Number of Tasks.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns the most recently added Task in the TaskList.
     * @return Most recently added Task.
     */
    public Task getLastTask() {
        return tasks.get(getTaskCount() - 1);
    }

    /**
     * Marks the Task at the specified index of the TaskList as done.
     * @param taskNumber Index of the Task to be deleted.
     */
    public void markTaskAsDone(int taskNumber) {
        getTask(taskNumber).setIsDone(true);
    }

    /**
     * Deletes the Task at the specified index of the TaskList.
     * @param taskNumber Index of the Task to be deleted.
     * @return Deleted Task.
     */
    public Task deleteTask(int taskNumber) {
        Task deletedTask = removeTask(taskNumber);
        taskCount--;
        return deletedTask;
    }

    /**
     * Adds a todo to the task list.
     * @param todo Description of the todo.
     */
    public void addToDo(String todo) {
        addTask(new ToDo(todo));
    }

    /**
     * Adds a Deadline to the TaskList.
     * @param params String array holding the description and 'by' of the DeadLine.
     */
    public void addDeadline(String[] params) {
        addTask(new Deadline(params[DESCRIPTION], params[BY]));
    }

    /**
     * Adds an Event to the TaskList.
     * @param params String array holding the description and 'at' of the Event.
     */
    public void addEvent(String[] params) {
        addTask(new Event(params[DESCRIPTION], params[AT]));
    }

    /**
     * Returns an ArrayList of Tasks containing the keyword searched for.
     * @param keyword Keyword to search for.
     * @return ArrayList of Tasks containing the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.toString().contains(keyword))
                .collect(toList());
    }
}
