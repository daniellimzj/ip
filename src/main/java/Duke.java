import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String border = "==================================================";
        System.out.println(border);
        System.out.println(" _________________________ ");
        System.out.println("| Hello! I'm Duke         |");
        System.out.println("| What can I do for you?  |");
        System.out.println("| ________________________|");
        System.out.println("|/");
        System.out.println(border);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        // Check for bye command
        while (!line.equals("bye")) {
            //Check for list command
            if (line.equals("list")) {
                System.out.println(border);
                for (int i = 0; i < Task.taskCount; i++) {
                    System.out.print((i + 1) + ".");
                    System.out.println(tasks[i]);
                }
                System.out.println(border);

            // Check for done command
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

            // If not a command, add a task
            } else {
                tasks[Task.taskCount] = new Task(line);
                System.out.println(border);
                System.out.println("added " + line);
                System.out.println(border);
            }
            line = in.nextLine();
        }
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }
}
