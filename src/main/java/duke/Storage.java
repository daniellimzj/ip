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

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private String directoryPath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.directoryPath = filePath.substring(0, filePath.lastIndexOf("/"));
    }

    public boolean getTasksFile() {
        return Files.exists(Paths.get(filePath));
    }

    public void createTasksFile() {
        try {
            Files.createDirectory(Paths.get(directoryPath));
            Files.createFile(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Oops! something went wrong" + e.getMessage());
        }

    }

    public void processFileContents(ArrayList<Task> tasks) throws DukeException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {

                String line = s.nextLine();
                String[] task = line.split(Task.getSeparator());

                switch (task[0]) {
                case "T":
                    tasks.add(new ToDo(task[2]));
                    tasks.get(Task.getTaskCount() - 1).setIsDone(task[1].equals(Task.getTick()));
                    break;
                case "E":
                    tasks.add(new Event(task[2], task[3]));
                    tasks.get(Task.getTaskCount() - 1).setIsDone(task[1].equals(Task.getTick()));
                    break;
                case "D":
                    tasks.add(new Deadline(task[2], task[3]));
                    tasks.get(Task.getTaskCount() - 1).setIsDone(task[1].equals(Task.getTick()));
                    break;
                default:
                    throw new DukeException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops, something went wrong!" + e.getMessage());
        }
    }

    public void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < Task.getTaskCount(); i++) {
            fw.append(tasks.get(i).printToFile() + "\n");
        }
        fw.close();
    }
}
