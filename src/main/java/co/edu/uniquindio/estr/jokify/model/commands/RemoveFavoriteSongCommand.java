package co.edu.uniquindio.estr.jokify.model.commands;

import co.edu.uniquindio.estr.jokify.model.*;

public class RemoveFavoriteSongCommand implements Command{

    // Attributes for RemoveFavoriteSongCommand class
    private final User currentUser;
    private final Song song;

    // Constructor for RemoveFavoriteSongCommand class
    public RemoveFavoriteSongCommand(User currentUser, Song song) {
        this.currentUser = currentUser;
        this.song = song;
    }

    // Methods for RemoveFavoriteSongCommand class

    /**
     * Delete the song from the user's favorite list, as a command.
     */
    @Override
    public void execute() {
        currentUser.getLinkedSong().delete(song);
    }

    /**
     * Add the song to the user's favorite list, as a command.
     */
    @Override
    public void undo() {
        currentUser.getLinkedSong().addFirst(song);
    }
}
