package co.edu.uniquindio.estr.jokify.model;

import java.util.Stack;

public class CommandManager {

    // Attributes for CommandManager class. (Se supone que pueden ser "final" pero me da miedo que no funcione, fuck intellij)
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    // Methods for CommandManager class

    /**
     * Executes a command and adds it to the undo stack.
     * Commands are executed and added to the undo stack.
     *
     * @param command The command to be executed. Can be of any type of class that implements the command interface. (For now only AddFavoriteSongCommand)
     */
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    /**
     * Undo the last command executed.
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    /**
     * Redo the last command undone.
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    /**
     * Check if there is a command to undo.
     * @return True if there is a command to undo, false otherwise.
     */
    public boolean canUndo(){
        return !undoStack.isEmpty();
    }

    /**
     * Check if there is a command to redo.
     * @return True if there is a command to redo, false otherwise.
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }


}
