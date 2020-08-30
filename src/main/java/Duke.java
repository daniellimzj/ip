import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String border = "==================================================";
        printWelcomeMessage(border);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {

            if (line.equals("list")) {
                printList(tasks, border);

            } else if (line.startsWith("done ")) {
                int taskNumber = Integer.parseInt(line.substring(5)) - 1;
                // Some basic error checking
                if (taskNumber > Task.taskCount - 1) {
                    break;
                }
                tasks[taskNumber].setIsDone(true);
                System.out.println(border);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber]);
                System.out.println(border);

            } else if (line.startsWith("todo")) {
                String todo = line.substring(5);
                tasks[Task.taskCount] = new ToDo(todo);
                System.out.println(border);
                System.out.println("added " + todo);
                System.out.println(border);

            } else if (line.startsWith("deadline")) {
                String deadline = line.substring(9);
                int indexOfSlash = deadline.indexOf("/by");
                String description = deadline.substring(0, indexOfSlash).trim();
                String by = deadline.substring(indexOfSlash + 4).trim();
                tasks[Task.taskCount] = new Deadline(description, by);
                System.out.println(border);
                System.out.println("added " + deadline);
                System.out.println(border);


            } else if (line.startsWith("event")) {
                System.out.println("EVENT TODO");

            } else {
                System.out.println ("Unknown command!");
            }

            line = in.nextLine();
        }
        printByeMessage(border);
    }

    private static void printWelcomeMessage(String border) {
        System.out.println(border);
        System.out.println(" _________________________ ");
        System.out.println("| Hello! I'm Duke         |");
        System.out.println("| What can I do for you?  |");
        System.out.println("| ________________________|");
        System.out.println("|/");
        System.out.println(border);
    }

    private static void printByeMessage(String border) {
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }

    private static void printList(Task[] tasks, String border) {
        System.out.println(border);
        for (int i = 0; i < Task.taskCount; i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println(border);
    }
}
