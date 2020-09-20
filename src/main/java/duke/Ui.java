package duke;

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

    public String getNextLine() {
        return conversation.nextLine();
    }

    public void printAddTaskMessage(TaskList tasks) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.getLastTask());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.getTaskCount());
        System.out.println(BORDER);
    }

    public void printByeMessage() {
        System.out.println(BORDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BORDER);
    }

    public void printDeleteTaskMessage(Task task, TaskList tasks) {
        System.out.println(BORDER);
        System.out.println("Nice! I've deleted this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.getTaskCount());
        System.out.println(BORDER);
    }

    public void printDoneTaskMessage(Task task) {
        System.out.println(BORDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(BORDER);
    }

    public void printUnknownMessage() {
        System.out.println(BORDER);
        System.out.println ("Unknown command!");
        System.out.println(BORDER);
    }

    public void printList(TaskList tasks) {
        System.out.println(BORDER);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.getTask(i));
        }
        System.out.println(BORDER);
    }

    public void printWelcomeMessage() {
        System.out.println(BORDER);
        System.out.println(" _________________________");
        System.out.println("| Hello! I'm Duke         |");
        System.out.println("| What can I do for you?  |");
        System.out.println("| ________________________|");
        System.out.println("|/");
        System.out.println(BORDER);
    }
    public void printDescriptionErrorMessage(String task) {
        System.out.println(BORDER);
        System.out.println("Oops! The format of the " + task + " is incorrect!");
        System.out.println(BORDER);
    }

    public void printTaskNumberErrorMessage() {
        System.out.println(BORDER);
        System.out.println("Oops! Please input a task number that exists!");
        System.out.println(BORDER);
    }

    public void printDoneErrorMessage() {
        System.out.println(BORDER);
        System.out.println("Oops! Please input a number after done!");
        System.out.println(BORDER);
    }

    public void printGenericErrorMessage() {
        System.out.println(BORDER);
        System.out.println("Oops! Something went wrong!");
        System.out.println(BORDER);
    }

    public void printDateTimeParseErrorMessage() {
        System.out.println(BORDER);
        System.out.println("Oops! Please make sure your date and time is in the format dd-mm-yy hh-mm!");
        System.out.println(BORDER);
    }

    public void printFilteredList(ArrayList<Task> tasks) {
        System.out.println(BORDER);
        if (tasks.size() == 0) {
            System.out.println("There are no matching tasks!");
        } else {
            System.out.println("Here are the matching tasks in the list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print((i + 1) + ".");
                System.out.println(tasks.get(i));
            }
        }
        System.out.println(BORDER);
    }
}
