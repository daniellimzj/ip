package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            ui.printGenericErrorMessage();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        ui.printWelcomeMessage();
        parser.runConversation(tasks, ui);
        try {
            storage.writeTasksToFile(tasks);
        } catch (IOException e) {
            ui.printGenericErrorMessage();
        }
        ui.printByeMessage();
    }
}
