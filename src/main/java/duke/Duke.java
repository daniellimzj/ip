package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;


import java.io.IOException;


public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {

        ui.printWelcomeMessage();
        String nextLine = ui.getNextLine();

        while (!parser.isByeCommand(nextLine)) {

            if (parser.isListCommand(nextLine)) {
                ui.printList(tasks);

            } else if (parser.isDoneCommand(nextLine)) {
                try {
                    int taskNumber = parser.getIndexToMarkAsDone(nextLine);
                    tasks.markTaskAsDone(taskNumber);
                    ui.printDoneTaskMessage(tasks.getTask(taskNumber));
                } catch (NumberFormatException e) {
                    ui.printDoneErrorMessage();
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    ui.printTaskNumberErrorMessage();
                }

            } else if (parser.isDeleteCommand(nextLine)) {
                try {
                    int taskNumber = parser.getIndexToDelete(nextLine);
                    Task deletedTask = tasks.deleteTask(taskNumber);
                    ui.printDeleteTaskMessage(deletedTask);
                } catch (NumberFormatException e) {
                    ui.printDoneErrorMessage();
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    ui.printTaskNumberErrorMessage();
                }

            } else if (parser.isAddToDoCommand(nextLine)) {
                try {
                    String todo = parser.getToDoParams(nextLine);
                    tasks.addTask(new ToDo(todo));
                    ui.printAddTaskMessage(tasks.getLastTask());
                } catch (DukeException e) {
                    ui.printDescriptionErrorMessage(Parser.getParam("todo"));
                }

            } else if (parser.isAddDeadlineCommand(nextLine)) {
                try {
                    String[] params = parser.getDeadlineParams(nextLine);
                    tasks.addTask(new Deadline(params[0], params[1]));
                    ui.printAddTaskMessage(tasks.getLastTask());
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printDescriptionErrorMessage(Parser.getParam("deadline"));
                }

            } else if (parser.isAddEventCommand(nextLine)) {
                try {
                    String[] params = parser.getEventParams(nextLine);
                    tasks.addTask(new Event(params[0], params[1]));
                    ui.printAddTaskMessage(tasks.getLastTask());
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printDescriptionErrorMessage(Parser.getParam("event"));
                }

            } else {
                ui.printUnknownMessage();
            }

            nextLine = ui.getNextLine();
        }

        try {
            storage.writeTasksToFile(tasks);
        } catch (IOException e) {
            ui.printGenericErrorMessage();
        }
        ui.printByeMessage();
    }


}
