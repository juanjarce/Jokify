package co.edu.uniquindio.estr.jokify.model;

/**
 * Interface that represents a command behaviour.
 *
 * @version 1.0
 */
public interface Command {

    void execute();

    void undo();
}
