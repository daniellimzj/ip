package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.Scanner;
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


    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        Printer.printWelcomeMessage();

        if (!FileHandler.getTasksFile()) {
            FileHandler.createTasksFile();
        } else {
            try {
                FileHandler.processFileContents(tasks);
            } catch (DukeException e) {
                System.out.println("Unable to load values from text file");
            }
        }



        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals(PARAM_BYE)) {

            if (line.equals(PARAM_LIST)) {
                Printer.printList(tasks);

            } else if (line.startsWith(PARAM_DONE)) {
                try {
                    int taskNumber = markTaskAsDone(tasks, line);
                    Printer.printDoneTaskMessage(tasks.get(taskNumber));
                } catch (NumberFormatException e) {
                    Printer.printDoneErrorMessage();
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    Printer.printTaskNumberErrorMessage();
                }

            } else if (line.startsWith(PARAM_DELETE)) {
                try {
                    Task deletedTask = deleteTask(tasks, line);
                    Printer.printDeleteTaskMessage(deletedTask);
                } catch (NumberFormatException e) {
                    Printer.printDoneErrorMessage();
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    Printer.printTaskNumberErrorMessage();
                }

            } else if (line.startsWith(PARAM_TODO)) {
                try {
                    addToDo(tasks, line);
                    Printer.printAddTaskMessage(tasks.get(Task.getTaskCount() - 1));
                } catch (DukeException e) {
                    Printer.printDescriptionErrorMessage(PARAM_TODO);
                }

            } else if (line.startsWith(PARAM_DEADLINE)) {
                try {
                    addDeadline(tasks, line);
                    Printer.printAddTaskMessage(tasks.get(Task.getTaskCount() - 1));
                } catch (StringIndexOutOfBoundsException e) {
                    Printer.printDescriptionErrorMessage(PARAM_DEADLINE);
                }

            } else if (line.startsWith(PARAM_EVENT)) {
                try {
                    addEvent(tasks, line);
                    Printer.printAddTaskMessage(tasks.get(Task.getTaskCount() - 1));
                } catch (StringIndexOutOfBoundsException e) {
                    Printer.printDescriptionErrorMessage(PARAM_EVENT);
                }

            } else {
                Printer.printErrorMessage();
            }

            line = in.nextLine();
        }

        try {
            FileHandler.writeTasksToFile(tasks);
        } catch (IOException e) {
            System.out.println("Oops! something went wrong" + e.getMessage());
        }
        Printer.printByeMessage();
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
