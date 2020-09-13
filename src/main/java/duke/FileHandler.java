package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.Scanner;

public class FileHandler {

    private static final String FILE_PATH = "data/tasks.txt";
    private static final String DIRECTORY_PATH = "data";

    public static boolean getTasksFile() {
        return Files.exists(Paths.get(FILE_PATH));
    }

    public static void createTasksFile() {
        try {
            Files.createDirectory(Paths.get(DIRECTORY_PATH));
            Files.createFile(Paths.get(FILE_PATH));
        } catch (IOException e) {
            System.out.println("Oops! something went wrong" + e.getMessage());
        }

    }

    public static void processFileContents(Task[] tasks) throws DukeException{
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {

                String line = s.nextLine();
                String[] task = line.split(Task.getSeparator());

                switch (task[0]) {
                case "T":
                    tasks[Task.getTaskCount()] = new ToDo(task[2]);
                    tasks[Task.getTaskCount() - 1].setIsDone(task[1].equals(Task.getTick()));
                    break;
                case "E":
                    tasks[Task.getTaskCount()] = new Event(task[2], task[3]);
                    tasks[Task.getTaskCount() - 1].setIsDone(task[1].equals(Task.getTick()));
                    break;
                case "D":
                    tasks[Task.getTaskCount()] = new Deadline(task[2], task[3]);
                    tasks[Task.getTaskCount() - 1].setIsDone(task[1].equals(Task.getTick()));
                    break;
                default:
                    throw new DukeException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops, something went wrong!" + e.getMessage());
        }
    }

    public static void writeTasksToFile(Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < Task.getTaskCount(); i++) {
            fw.append(tasks[i].printToFile() + "\n");
        }
        fw.close();
    }
}
