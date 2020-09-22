# User Guide

## Accessing Duke
### Setting Up in IntelliJ

One way to chat with Duke is through an IDE like IntelliJ.

Prerequisites: JDK 11, update IntelliJ to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into IntelliJ as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
     _________________________
    | Hello! I'm Duke         |
    | What can I do for you?  |
    | ________________________|
    |/
   ```

### Running the JAR File

Another way to chat with Duke is by running the JAR file directly.

Below are the instructions for how to run the JAR file on Windows.

1. Drag the JAR file to an empty folder.
1. Run your command prompt by pressing `Windows + 'R'` or otherwise.
1. Navigate to the folder that the JAR file is in.
1. Type `chcp 65001` and press `Enter`
1. Run the JAR file by using the command `java -Dfile.encoding=UTF-8 -jar ip.jar`. You should see something like below:
   ```
     _________________________
    | Hello! I'm Duke         |
    | What can I do for you?  |
    | ________________________|
    |/
   ```
If the status icons in lists do not display properly, experiment with changing the font used in the command prompt. One font that works is NSimSun.

## Features

#### Add Tasks to Your List
You can add the following types of tasks to your list: Todos, Events and Deadlines.

#### Display Your List
You can view what is in your list at any time.

#### Mark Tasks as Done
You can mark tasks in your list as done when you complete them.

#### Delete Tasks From Your List
You can delete tasks from your list if you no longer want them.

#### Find Tasks with Keywords
You can search your list for tasks that contain a keyword or phrase.

#### Saving the Tasks In Your List
You can save your list of tasks when exiting. They will be automatically loaded next time.

## Commands and Usage

#### Some Things to Note:
- In the following section, all capitalised words in code represent parameters. e.g. `PARAMETER`.
- All `DATETIME` parameters must be in a `dd-mm-yy hh:mm` format.

#### `todo` Command
The `todo` adds a to-do with `DESCRIPTION` to the list.

Usage: `todo DESCRIPTION`

Example: `todo my laundry`

Expected Output: 
```
==================================================
Got it. I've added this task:
[T][✘] buy groceries!!!
Now you have 1 tasks in the list.
==================================================
```

#### `event` Command
The `event` command adds an event with `DESCRIPTION` at `DATETIME` to the list.

Usage: `event DESCRIPTION /at DATETIME`

Example: `event my first midterm exam /at 01-10-20 10:01`

Expected Output: 
```
==================================================
Got it. I've added this task:
  [E][✘] my first midterm exam (at: 01-10-20 10:01)
Now you have 2 tasks in the list.
==================================================
```
 
#### `deadline` Command
The `deadline` command adds a deadline with `DESCRIPTION` to be done by `DATETIME` to the list.

Usage: `deadline DESCRIPTION /by DATETIME`

Example: `deadline my homework /by 31-12-20 23:59`

Expected Output: 
```
==================================================
Got it. I've added this task:
  [D][✘] my homework (by: 31-12-20 23:59)
Now you have 3 tasks in the list.
==================================================
```

#### `list` Command
The `list` command shows all the tasks in the list.

Usage: `list`

Expected Output:
```
==================================================
1.[T][✘] buy groceries!!!
2.[E][✘] my first midterm exam (at: 01-10-20 10:01)
3.[D][✘] my homework (by: 31-12-20 23:59)
==================================================
```

#### `done` Command
The `done` command marks the task in the list at that `INDEX` as done.

Usage: `done INDEX`

Example: `done 1` 

Expected Output: 
```
==================================================
Nice! I've marked this task as done:
[T][✓] buy groceries!!!
==================================================
```

#### `delete` Command
The `delete` command deletes the task in the list at a particular `INDEX`.

Usage: `delete INDEX`

Example: `delete 3`

Expected Output: 
```
==================================================
Nice! I've deleted this task:
[D][✘] my homework (by: 31-12-20 23:59)
Now you have 2 tasks in the list.
==================================================
```

#### `find` Command
The `find` command shows all the tasks in the list containing a `KEYWORD`.

Usage: `find KEYWORD`

Example: `find groceries`

Expected Output: 
```
==================================================
Here are the matching tasks in the list:
1.[T][✘] buy groceries!!!
==================================================
```

### `bye` Command
The `bye` command exits the program and saves your tasks to a file for future usage.

Usage: `bye`

Expected Output: 
```
==================================================
Bye. Hope to see you again soon!
==================================================
```


