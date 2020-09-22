package duke.storage;

import duke.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;

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

    private void createTasksFile() throws IOException {
        Files.createDirectory(Paths.get(directoryPath));
        Files.createFile(Paths.get(filePath));
    }

    /**
     * Returns an ArrayList of Tasks that were previously saved to the file.
     *
     * @return ArrayList of previously saved Tasks.
     * @throws DukeException If there is a problem with the contents of the file.
     * @throws FileNotFoundException If there is a problem opening the file.
     */
    private ArrayList<Task> processFileContents() throws DukeException, FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        int i = 0;

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] task = line.split(Task.getSeparator());
            switch (task[0]) {
            case "T":
                tasks.add(new ToDo(task[2]));
                tasks.get(i).setIsDone(task[1].equals(Task.getTick()));
                i++;
                break;
            case "E":
                tasks.add(new Event(task[2], task[3]));
                tasks.get(i).setIsDone(task[1].equals(Task.getTick()));
                i++;
                break;
            case "D":
                tasks.add(new Deadline(task[2], task[3]));
                tasks.get(i).setIsDone(task[1].equals(Task.getTick()));
                i++;
                break;
            default:
                throw new DukeException();
            }
        }
        return tasks;
    }

    /**
     * Returns ArrayList of Tasks that were previously saved.
     * If no tasks were saved, an empty save file is created and an empty ArrayList is returned.
     *
     * @return ArrayList of previously saved Tasks.
     * @throws IOException If there is an error creating the empty save file, or reading from an existing file.
     * @throws DukeException If there is a problem with the file contents.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!getTasksFile()) {
            createTasksFile();
        } else {
            tasks = processFileContents();
        }
        return tasks;
    }

    /**
     * Writes all current Tasks to the save file.
     *
     * @param tasks TaskList of all current Tasks.
     * @throws IOException if there is a problem opening or writing to the file.
     */
    public void writeTasksToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            fw.append(tasks.getTask(i).printToFile() + "\n");
        }
        fw.close();
    }
}
