package duke;

import duke.task.*;

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

    private boolean getTasksFile() {
        return Files.exists(Paths.get(filePath));
    }

    private void createTasksFile() {
        try {
            Files.createDirectory(Paths.get(directoryPath));
            Files.createFile(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Oops! something went wrong" + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!getTasksFile()) {
            createTasksFile();
        } else {
            try {
                tasks = processFileContents();
            } catch (DukeException e) {
                System.out.println("Unable to load values from text file");
            }
        }
        return tasks;
    }


    private ArrayList<Task> processFileContents() throws DukeException {

        ArrayList<Task> tasks = new ArrayList<>();

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
        return tasks;
    }

    public void writeTasksToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < Task.getTaskCount(); i++) {
            fw.append(tasks.getTask(i).printToFile() + "\n");
        }
        fw.close();
    }
}
