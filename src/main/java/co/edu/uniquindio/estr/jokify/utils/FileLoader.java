package co.edu.uniquindio.estr.jokify.utils;

import co.edu.uniquindio.estr.jokify.exceptions.ArtistsException;
import co.edu.uniquindio.estr.jokify.exceptions.SongsException;
import co.edu.uniquindio.estr.jokify.model.Artist;
import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.Store;
import co.edu.uniquindio.estr.jokify.model.enums.Genre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    /**
     * Load artists from a file.
     * @param filePath Path of the file to load the artists.
     * @return List of artists loaded from the file.
     */
    public static List<Artist> loadArtists(String filePath) {
        List<Artist> artists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean readingArtists = false;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#Artistas")) {
                    readingArtists = true;
                } else if (line.startsWith("#Canciones")) {
                    break; // Terminamos de leer los artistas
                } else if (readingArtists) {
                    String[] parts = line.split(";");
                    if (parts.length == 4) {
                        String code = parts[0];
                        String name = parts[1];
                        String nationality = parts[2];
                        boolean partOfBand = Boolean.parseBoolean(parts[3]);
                        Artist artist = new Artist(code, name, nationality, partOfBand);
                        artists.add(artist);
                    }
                }
            }
        } catch (IOException ignored) {

        }
        return artists;
    }

    /**
     * Load songs from a file.
     * @param filePath Path of the file to load the songs.
     * @return List of songs loaded from the file.
     */
    public static List<Song> loadSongs(String filePath) {
        List<Song> songs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean readingSongs = false;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#Canciones")) {
                    readingSongs = true;
                } else if (readingSongs) {
                    String[] parts = line.split(";");
                    if (parts.length == 9) {
                        String artistName = parts[0];
                        String songName = parts[1];
                        String album = parts[2];
                        String cover = parts[3];
                        int year = Integer.parseInt(parts[4]);
                        int duration = Integer.parseInt(parts[5]);
                        int likes = Integer.parseInt(parts[6]);
                        Genre genre = Genre.valueOf(parts[7]);
                        String youtubeURL = parts[8];
                        Song song = new Song(songName, album, cover, year, duration, likes, youtubeURL, genre, artistName);
                        songs.add(song);
                    }
                }
            }
        } catch (IOException ignored) {
        }
        return songs;
    }

    public static void loadArtistsAndSongs(Store store, String filePath){
        List<Artist> artists = loadArtists(filePath);
        List<Song> songs = loadSongs(filePath);

        // Add artist from file to the Store
        artists.forEach(a -> {
            try {
                store.createArtist(a);
            } catch (ArtistsException e) {
                throw new RuntimeException(e);
            }
        });

        for (Song song : songs) {
            Artist artist = store.getArtistByName(song.getArtistName());
            if (artist != null) {
                try {
                    store.createSong(song);
                } catch (SongsException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}


