package co.edu.uniquindio.estr.jokify.model.commands;

import co.edu.uniquindio.estr.jokify.model.*;

public class AddFavoriteSongCommand implements Command {

    // Atributes for AddFavoriteSongCommand class
    private final User currentUser;
    private final Song song;

    // Constructor for AddFavoriteSongCommand class
    public AddFavoriteSongCommand(User currentUser, Song song) {
        this.currentUser = currentUser;
        this.song = song;
    }

    // Methods for AddFavoriteSongCommand class

    /**
     * Add the song to the user's favorite list, as a command.
     */
    @Override
    public void execute() {
        currentUser.getLinkedSong().addFirst(song);
    }

    /**
     * Delete the song from the user's favorite list, as a command.
     */
    @Override
    public void undo() {
        currentUser.getLinkedSong().delete(song);
    }
}