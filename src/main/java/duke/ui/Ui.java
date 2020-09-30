package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String BORDER = "==================================================";
    private Scanner conversation;

    public Ui() {
        this.conversation = new Scanner(System.in);
    }

    /**
     * Returns a String of the user's next input line.
     *
     * @return User's next input line.
     */
    public String getNextLine() {
        return conversation.nextLine();
    }

    /**
     * Prints a message telling the user a Task has been added.
     *
     * @param tasks TaskList the Task has been added to.
     */
    public void printAddTaskMessage(TaskList tasks) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.getLastTask());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.getTaskCount());
        System.out.println(BORDER);
    }

    /**
     * Prints a message to say goodbye to the user.
     */
    public void printByeMessage() {
        System.out.println(BORDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BORDER);
    }

    /**
     * Prints a message telling the user a Task has been deleted.
     *
     * @param task Deleted Task.
     * @param tasks TaskList the Task has been deleted from.
     */
    public void printDeleteTaskMessage(Task task, TaskList tasks) {
        System.out.println(BORDER);
        System.out.println("Nice! I've deleted this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.getTaskCount());
        System.out.println(BORDER);
    }

    /**
     * Prints a message telling the user a Task has been marked as done.
     *
     * @param task Task marked as done.
     */
    public void printDoneTaskMessage(Task task) {
        System.out.println(BORDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(BORDER);
    }

    /**
     * Prints a message for when the user inputs an invalid command.
     */
    public void printUnknownMessage() {
        System.out.println(BORDER);
        System.out.println ("Unknown command!");
        System.out.println(BORDER);
    }

    /**
     * Prints a list of all Tasks in the TaskList.
     *
     * @param tasks TaskList to be printed.
     */
    public void printList(TaskList tasks) {
        System.out.println(BORDER);

        if (tasks.getTaskCount() == 0) {
            System.out.println("There are no tasks in the list!");
            System.out.println(BORDER);
            return;
        }

       for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.getTask(i));
        }

        System.out.println(BORDER);
    }

    /**
     * Prints a message to welcome the user.
     */
    public void printWelcomeMessage() {
        System.out.println(BORDER);
        System.out.println(" _________________________");
        System.out.println("| Hello! I'm Duke         |");
        System.out.println("| What can I do for you?  |");
        System.out.println("| ________________________|");
        System.out.println("|/");
        System.out.println(BORDER);
    }

    /**
     * Prints a message when the user inputs an incorrectly formatted command to add a Task.
     *
     * @param task Task which has the incorrect format.
     */
    public void printDescriptionErrorMessage(String task) {
        System.out.println(BORDER);
        System.out.println("Oops! The format of the " + task + " is incorrect!");
        switch(task) {
        case ("todo"):
            System.out.println("Format for todo: todo DESCRIPTION");
            break;
        case ("event"):
            System.out.println("Format for event: event DESCRIPTION /at dd-mm-yy hh:mm");
            break;
        case ("deadline"):
            System.out.println("Format for deadline: deadline DESCRIPTION /by dd-mm-yy hh:mm");
            break;
        default:
            break;
        }
        System.out.println(BORDER);
    }

    /**
     * Prints a message when the user inputs a Task number that does not exist.
     */
    public void printTaskNumberErrorMessage() {
        System.out.println(BORDER);
        System.out.println("Oops! Please input a task number that exists!");
        System.out.println(BORDER);
    }

    /**
     * Prints a message when the user inputs an incorrectly formatted command to mark a Task as done.
     */
    public void printDoneErrorMessage() {
        System.out.println(BORDER);
        System.out.println("Oops! Please input a number after done!");
        System.out.println(BORDER);
    }

    /**
     * Prints a generic error message for all other errors.
     */
    public void printGenericErrorMessage() {
        System.out.println(BORDER);
        System.out.println("Oops! Something went wrong!");
        System.out.println(BORDER);
    }

    /**
     * Prints a message when the user inputs an incorrectly formatted date or time.
     */
    public void printDateTimeParseErrorMessage() {
        System.out.println(BORDER);
        System.out.println("Oops! Please make sure your date and time is in the format dd-mm-yy hh:mm!");
        System.out.println(BORDER);
    }

    /**
     * Prints an ArrayList of Tasks.
     *
     * @param tasks ArrayList to be printed.
     */
    public void printFilteredList(ArrayList<Task> tasks) {
        System.out.println(BORDER);

        if (tasks.size() == 0) {
            System.out.println("There are no matching tasks!");
            System.out.println(BORDER);
            return;
        }

        System.out.println("Here are the matching tasks in the list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println(BORDER);
    }
}
