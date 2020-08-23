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

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println(border);
                for (int i = 0; i < Task.taskCount; i++) {
                    System.out.print((i + 1) + ".[" + tasks[i].getStatusIcon() + "] ");
                    System.out.println(tasks[i].description);
                }
                System.out.println(border);
                line = in.nextLine();
            } else {
                tasks[Task.taskCount] = new Task(line);
                System.out.println(border);
                System.out.println("added " + line);
                System.out.println(border);
                line = in.nextLine();
            }
        }

        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }
}
