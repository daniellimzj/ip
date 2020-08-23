import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String[] tasks = new String[100];
        int taskCount = 0;

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
                for (int i = 0; i < taskCount; i++) {
                    System.out.print((i + 1) + ". ");
                    System.out.println(tasks[i]);
                }
            }
            else {
                tasks[taskCount] = line;
                taskCount++;
            }

            System.out.println(border);
            System.out.println("added " + line);
            System.out.println(border);
            line = in.nextLine();
        }

        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }
}
