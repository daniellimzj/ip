package duke;

import duke.task.Task;

public class Printer {

    private static final String border = "==================================================";

    public static void printAddTaskMessage(Task task) {
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.\n", Task.getTaskCount());
        System.out.println(border);
    }

    public static void printByeMessage() {
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }

    public static void printDoneTaskMessage(Task task) {
        System.out.println(border);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(border);
    }

    public static void printErrorMessage() {
        System.out.println(border);
        System.out.println ("Unknown command!");
        System.out.println(border);
    }

    public static void printList(Task[] tasks) {
        System.out.println(border);
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println(border);
    }

    public static void printWelcomeMessage() {
        System.out.println(border);
        System.out.println(" _________________________");
        System.out.println("| Hello! I'm duke.Duke         |");
        System.out.println("| What can I do for you?  |");
        System.out.println("| ________________________|");
        System.out.println("|/");
        System.out.println(border);
    }
    public static void printDescriptionErrorMessage(String task) {
        System.out.println(border);
        System.out.println("Oops! The format of the " + task + " is incorrect!");
        System.out.println(border);
    }

    public static void printTaskNumberErrorMessage() {
        System.out.println(border);
        System.out.println("Oops! Please input a task number that exists!");
        System.out.println(border);
    }

    public static void printDoneErrorMessage() {
        System.out.println(border);
        System.out.println("Oops! Please input a number after done!");
        System.out.println(border);
    }
}