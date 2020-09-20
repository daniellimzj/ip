package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;


public class Duke {

    private static final String PARAM_BY = "/by";
    private static final String PARAM_AT = "/at";
    private static final String PARAM_EVENT = "event";
    private static final String PARAM_DEADLINE = "deadline";
    private static final String PARAM_TODO = "todo";
    private static final String PARAM_DONE = "done";
    private static final String PARAM_LIST = "list";
    private static final String PARAM_BYE = "bye";
    private static final String PARAM_DELETE = "delete";

    private Ui ui;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {

        ArrayList<Task> tasks = storage.load();

        ui.printWelcomeMessage();
        String nextLine = ui.getNextLine();

        while (!nextLine.equals(PARAM_BYE)) {

            if (nextLine.equals(PARAM_LIST)) {
                ui.printList(tasks);

            } else if (nextLine.startsWith(PARAM_DONE)) {
                try {
                    int taskNumber = markTaskAsDone(tasks, nextLine);
                    ui.printDoneTaskMessage(tasks.get(taskNumber));
                } catch (NumberFormatException e) {
                    ui.printDoneErrorMessage();
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    ui.printTaskNumberErrorMessage();
                }

            } else if (nextLine.startsWith(PARAM_DELETE)) {
                try {
                    Task deletedTask = deleteTask(tasks, nextLine);
                    ui.printDeleteTaskMessage(deletedTask);
                } catch (NumberFormatException e) {
                    ui.printDoneErrorMessage();
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    ui.printTaskNumberErrorMessage();
                }

            } else if (nextLine.startsWith(PARAM_TODO)) {
                try {
                    addToDo(tasks, nextLine);
                    ui.printAddTaskMessage(tasks.get(Task.getTaskCount() - 1));
                } catch (DukeException e) {
                    ui.printDescriptionErrorMessage(PARAM_TODO);
                }

            } else if (nextLine.startsWith(PARAM_DEADLINE)) {
                try {
                    addDeadline(tasks, nextLine);
                    ui.printAddTaskMessage(tasks.get(Task.getTaskCount() - 1));
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printDescriptionErrorMessage(PARAM_DEADLINE);
                }

            } else if (nextLine.startsWith(PARAM_EVENT)) {
                try {
                    addEvent(tasks, nextLine);
                    ui.printAddTaskMessage(tasks.get(Task.getTaskCount() - 1));
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printDescriptionErrorMessage(PARAM_EVENT);
                }

            } else {
                ui.printErrorMessage();
            }

            nextLine = ui.getNextLine();
        }

        try {
            storage.writeTasksToFile(tasks);
        } catch (IOException e) {
            System.out.println("Oops! something went wrong" + e.getMessage());
        }
        ui.printByeMessage();
    }

    private static void addDeadline(ArrayList<Task> tasks, String line) {

        String deadline = line.substring(line.indexOf(PARAM_DEADLINE) + PARAM_DEADLINE.length());
        int firstIndexOfByCommand = deadline.indexOf(PARAM_BY);
        int lastIndexOfByCommand = firstIndexOfByCommand + PARAM_BY.length();

        String description = deadline.substring(0, firstIndexOfByCommand).trim();
        String by = deadline.substring(lastIndexOfByCommand).trim();
        tasks.add(new Deadline(description, by));
    }

    private static void addEvent(ArrayList<Task> tasks, String line) {

        String event = line.substring(line.indexOf(PARAM_EVENT) + PARAM_EVENT.length());
        int firstIndexOfAtCommand = event.indexOf(PARAM_AT);
        int lastIndexOfAtCommand = firstIndexOfAtCommand + PARAM_AT.length();

        String description = event.substring(0, firstIndexOfAtCommand).trim();
        String at = event.substring(lastIndexOfAtCommand).trim();
        tasks.add(new Event(description, at));
    }

    private static void addToDo(ArrayList<Task> tasks, String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < 2) {
            throw new DukeException();
        }

        String todo = line.substring(line.indexOf(PARAM_TODO) + PARAM_TODO.length()).trim();
        tasks.add(new ToDo(todo));
    }

    private static Task deleteTask(ArrayList<Task> tasks, String line) {
        int taskNumber = Integer.parseInt(line.substring(PARAM_DELETE.length()).trim()) - 1;
        Task deletedTask = tasks.remove(taskNumber);
        Task.decreaseTaskCount();
        return deletedTask;
    }

    private static int markTaskAsDone(ArrayList<Task> tasks, String line) {
        int taskNumber = Integer.parseInt(line.substring(PARAM_DONE.length()).trim()) - 1;
        tasks.get(taskNumber).setIsDone(true);
        return taskNumber;
    }


}
