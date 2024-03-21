package co.edu.uniquindio.estr.jokify.model;

import co.edu.uniquindio.estr.jokify.exceptions.ArtistsException;
import co.edu.uniquindio.estr.jokify.exceptions.AttributesException;
import co.edu.uniquindio.estr.jokify.exceptions.SongsException;
import co.edu.uniquindio.estr.jokify.exceptions.UserException;
import co.edu.uniquindio.estr.jokify.model.enums.Genre;
import co.edu.uniquindio.estr.jokify.structures.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Store {

    //Attributes for the Store class
    String name;
    LinkedList<Song> songList;
    BinarySearchTree<Artist> artistList;
    HashMapCustom<String, User> userList;

    //---------------------------------------------------------------------------------------------
    //Attributes for manage searching threads results
     List<Song> resultOrSearch;
     List<Song> resultAndSearch;
    //---------------------------------------------------------------------------------------------

    //Singleton of the class
    private static Store store;

    //Empty constuctor for the Store class
    public Store(){

    }

    /**
     * Private constructor to apply singleton
     * @param name
     */
    private Store(String name) {
        this.name = name;
        this.songList = new LinkedList<Song>();
        this.artistList = new BinarySearchTree<Artist>();
        this.userList = new HashMapCustom<String, User>();

        //Creates the user admin
        User admin = new User("admin", "$aDmiN", "admin@email.com");
        userList.put("admin", admin);
        User joki = new User("joki", "123", "123");
        userList.put("joki", joki);

        //Creates songs and artist just to prove some functions
        Artist artist1 = new Artist("Joji", "Japan", false);
        artistList.insert(artist1);
        Artist artist2 = new Artist("Hozier", "Irland", false);
        artistList.insert(artist2);

        Song song1 = new Song("YEAH RIGHT", "BALLADS 1", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/joji1.png", 2018, 174, "https://www.youtube.com/watch?v=tG7wLK4aAOE", Genre.POP, artist1.getName());
        songList.addFirst(song1);
        Song song2 = new Song("YUKON (INTERLUDE)", "SMITHEREENS", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/joji2.png", 2022, 120, "https://www.youtube.com/watch?v=llFdrwnBwLE", Genre.POP, artist1.getName());
        songList.addFirst(song2);
        Song song3 = new Song("Glimpse of Us", "SMITHEREENS", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/joji3.png", 2022, 120, "https://www.youtube.com/watch?v=NgsWGfUlwJI", Genre.POP, artist1.getName());
        songList.addFirst(song3);
        Song song4 = new Song("RUN", "Single", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/joji4.png", 2022, 120, "https://www.youtube.com/watch?v=K09_5IsgGe8", Genre.POP, artist1.getName());
        songList.addFirst(song4);
        Song song5 = new Song("Sanctuary", "Single", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/joji5.png", 2019, 132, "https://www.youtube.com/watch?v=YWN81V7ojOE", Genre.POP, artist1.getName());
        songList.addFirst(song5);

        Song song6 = new Song("Movement", "Wasteland, Baby!", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/hozier1.png", 2019, 160, "https://www.youtube.com/watch?v=OSye8OO5TkM", Genre.POP, artist2.getName());
        songList.addFirst(song6);
        Song song7 = new Song("The Bones", "Single", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/hozier2.png", 2019, 184, "https://www.youtube.com/watch?v=Zmxfd3Z_wBA", Genre.POP, artist2.getName());
        songList.addFirst(song7);
        Song song8 = new Song("Better Love", "Single", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/hozier3.png", 2016, 190, "https://www.youtube.com/watch?v=Wm4CrOfbHMI", Genre.POP, artist2.getName());
        songList.addFirst(song8);
        Song song9 = new Song("Take Me To Church", "Hozier (Special Edition)", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/hozier4.png", 2014, 240, "https://www.youtube.com/watch?v=PVjiKRfKpPI", Genre.POP, artist2.getName());
        songList.addFirst(song9);
        Song song10 = new Song("Eat Your Young", "Single", "file:/home/joki/Documents/zzzUniversidad/EstructuraDatos/code/Jokify/src/main/resources/covers/hozier5.png", 2023, 248, "https://www.youtube.com/watch?v=e6LM0sIA_Eg", Genre.POP, artist2.getName());
        songList.addFirst(song10);

        //Add Liked songs for an user
        joki.getLinkedSong().addFirst(song1);
        joki.getLinkedSong().addFirst(song2);
        joki.getLinkedSong().addFirst(song3);
        joki.getLinkedSong().addFirst(song4);
        joki.getLinkedSong().addFirst(song5);
    }

    /**
     * Return the instance of the store
     * @return
     */
    public static Store getInstance() {
        if (store == null) {
            store = new Store("Jokify");
        }
        return store;
    }

    //getters() & setters() for the Store class
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LinkedList<Song> getSongList() {
        return songList;
    }
    public void setSongList(LinkedList<Song> songList) {
        this.songList = songList;
    }
    public BinarySearchTree<Artist> getArtistList() {
        return artistList;
    }
    public void setArtistList(BinarySearchTree<Artist> artistList) {
        this.artistList = artistList;
    }
    public HashMapCustom<String, User> getUserList() {
        return userList;
    }
    public void setUserList(HashMapCustom<String, User> userList) {
        this.userList = userList;
    }

    //equals() for Store class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store store)) return false;
        return Objects.equals(getName(), store.getName());
    }

    //hashCode() for Strore class
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    //toString() for Store class
    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                '}';
    }

    //LOGIC IMPLEMENTATION FOR THE STORE ----------------------------------------------------------------

    //CRUD FOR THE USERS --------------------------------------------------------------------------------

    /**
     * Get the instance of a user
     * @param username the usarname of the user
     * @return
     */
    public User getUser(String username) {
        return userList.get(username);
    }

    /**
     * Creates a user if all the data is correct and add it to the usersList
     * @param username username of the new user
     * @param password password of the new user
     * @param email email of the new user
     * @return
     * @throws UserException
     * @throws AttributesException
     */
    public void createUser(String username, String password, String email) throws UserException, AttributesException {
        User user = getUser(username);
        if (user != null) {
            throw new UserException("El nombre de usuario no esta disponible");
        } else {
            if (username == null || username.isBlank()) {
                throw new AttributesException("El nombre de usuario es obligatorio");
            }
            if (password == null || password.isBlank()) {
                throw new AttributesException("La contraseña es obligatoria");
            }
            if (email == null || email.isBlank()) {
                throw new AttributesException("El email is obligatorio");
            }
            User newUser = new User(username, password, email);
            userList.put(username, newUser);
        }
    }

    //FUNCTIONS FOR THE LOGIN ---------------------------------------------------------------------------

    /**
     * Get the User for the corresponding data
     * @param username username of the user
     * @param password password of the user
     * @return
     */
    public User loginUser(String username, String password) throws UserException {
        User u = userList.get(username);
        if(u!=null && u.password.equals(password)){
            return u;
        }
        else{
            throw new UserException("Verifique que el nombre de usuario y la contraseña sean correctas");
        }
    }

    //FUNCTIONS for search rqeuirements ------------------------------------------------------------------

    // 1. ------------------------------------------------------------------------------------------------
    //Artist Search: Because artists are stored in a binary tree, their
    //search is very efficient, therefore, given the name of an Artist it should return its
    //songs list.

    // Method to search for an artist by name and return their list of songs
    public List<Song> searchArtistSongs(String artistName) {
        // Search for the artist by name
        Artist artist = searchArtistByName(artistName);
        // If artist is found, return their list of songs
        if (artist != null) {
            return (List<Song>) artist.getSongs().toList();
        } else {
            // If artist is not found, print an error message and return null
            System.out.println("Artist not found: " + artistName);
            return null;
        }
    }

    // Method to search for an artist by name
    private Artist searchArtistByName(String artistName) {
        // Iterate through the artist list
        Iterator<Artist> iterator = artistList.iterator();
        while (iterator.hasNext()) {
            // Get the next artist
            Artist artist = iterator.next();
            // Check if the artist name matches the given name
            if (artist.getName().equals(artistName)) {
                return artist; // Return the artist if found
            }
        }
        return null; // Return null if artist not found
    }

    // 2. ------------------------------------------------------------------------------------------------

    //Search OR: Given the values of two or more attributes of a Song, return a list
    //with songs with at least one matching attribute.

    // Method for performing OR search for songs, executed in a separate thread
    public List<Song> searchSongsOrAsync(String attribute1, String attribute2) {
        // Create a new thread to perform the search
        Thread searchThread = new Thread(() -> {
            List<Song> result = searchSongsOr(attribute1, attribute2);
            // Store the result in a shared variable
            setResultOr(result);
        });
        // Start the thread
        searchThread.start();
        // Wait for the thread to finish and then return the result
        try {
            searchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getResultOr();
    }

    // Method to set the search result
    private synchronized void setResultOr(List<Song> result) {
        this.resultOrSearch = result;
    }

    // Method to get the search result
    private synchronized List<Song> getResultOr() {
        return resultOrSearch;
    }

    // Method for performing OR search for songs
    private List<Song> searchSongsOr(String attribute1, String attribute2) {
        List<Song> result = new ArrayList<>();
        // Iterate over the list of songs
        for (Song song : songList) {
            // Check if at least one of the attributes matches any of the provided values
            if (songMatchesAttributes(song, attribute1, attribute2)) {
                result.add(song);
            }
        }
        return result;
    }

    // Method to check if at least one of the attributes of a song matches any of the provided values
    private boolean songMatchesAttributes(Song song, String attribute1, String attribute2) {
        return song.getName().equals(attribute1) ||
                song.getAlbum().equals(attribute1) ||
                song.getName().equals(attribute2) ||
                song.getAlbum().equals(attribute2);
    }

    // 3. ------------------------------------------------------------------------------------------------

    // Method for performing AND search for songs, executed in a separate thread
    public List<Song> searchSongsAndAsync(String attribute1, String attribute2) {
        // Create a new thread to perform the search
        Thread searchThread = new Thread(() -> {
            List<Song> result = searchSongsAnd(attribute1, attribute2);
            // Store the result in a shared variable
            setResultAnd(result);
        });
        // Start the thread
        searchThread.start();
        // Wait for the thread to finish and then return the result
        try {
            searchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getResultAnd();
    }

    // Method to set the search result
    private synchronized void setResultAnd(List<Song> result) {
        this.resultAndSearch = result;
    }

    // Method to get the search result
    private synchronized List<Song> getResultAnd() {
        return resultAndSearch;
    }

    // Method for performing AND search for songs
    private List<Song> searchSongsAnd(String attribute1, String attribute2) {
        List<Song> result = new ArrayList<>();
        // Iterate over the list of songs
        for (Song song : songList) {
            // Check if all attributes match the provided values
            if (songMatchesAllAttributes(song, attribute1, attribute2)) {
                result.add(song);
            }
        }
        return result;
    }

    // Method to check if all attributes of a song match the provided values
    private boolean songMatchesAllAttributes(Song song, String attribute1, String attribute2) {
        return song.getName().equals(attribute1) &&
                song.getAlbum().equals(attribute1) &&
                song.getName().equals(attribute2) &&
                song.getAlbum().equals(attribute2);
    }

    //------------------------------------------------------------------------------------------------------------------

    //CRUD for Artist --------------------------------------------------------------------------------------------------

    // Method to check if an artist exists in the artist list
    private boolean artistExists(Artist artist) {
        return artistList.contains(artist);
    }

    // Método para obtener un artista por su código
    public Artist getArtist(String code) {
        Artist artist = new Artist();
        artist.setCode(code);
        if (artistExists(artist)) {
            return artistList.get(artist);
        } else {
            System.out.println("El artista con el código " + code + " no existe.");
            return null;
        }
    }

    // Method to add a new artist
    public void createArtist(Artist artist) throws ArtistsException {
        if (!artistExists(artist)) {
            artistList.insert(artist);
            System.out.println("Artista creado exitosamente: " + artist);
        } else {
            throw new ArtistsException("El artista con el código " + artist.getCode() + " ya existe.");
        }
    }

    // Method to delete an artist by their code
    public void deleteArtist(String code) throws ArtistsException {
        Artist artist = new Artist();
        artist.setCode(code);
        if (artistExists(artist)) {
            //Delete songs from artist -----------------------------------------
            for(Song s : songList){
                if(getArtist(code).getName().equals(s.getArtistName())){
                    songList.remove(s);
                }
            }
            //------------------------------------------------------------------
            artistList.remove(artist);
            System.out.println("Artista eliminado exitosamente: " + artist);
        } else {
            throw new ArtistsException("El artista con el código " + code + " no existe.");
        }
    }

    // Method to update an artist
    public void updateArtist(Artist updatedArtist) throws ArtistsException {
        if (artistExists(updatedArtist)) {
            artistList.remove(updatedArtist);
            artistList.insert(updatedArtist);
            System.out.println("Artista actualizado exitosamente: " + updatedArtist);
        } else {
            throw new ArtistsException("El artista con el código " + updatedArtist.getCode() + " no existe.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //METHODS Songs ----------------------------------------------------------------------------------------------------

    // Method to retrieve a song by its code
    public Song getSong(String code) {
        for (Song song : songList) {
            if (song.getCode().equals(code)) {
                return song;
            }
        }
        return null; // Return null if song with given code is not found
    }

    // Method to add a new song
    public void createSong(Song newSong) throws SongsException {
        songList.addFirst(newSong);
        getArtistByName(newSong.artistName).getSongs().addFirst(newSong);
        System.out.println("Canción creada exitosamente: " + newSong);
    }

    // Method to delete a song by its code
    public void deleteSong(String code) throws SongsException {
        Song s = getSong(code);
        if(s == null)  throw new SongsException("La canción no se encuentra agregada");
        getArtistByName(s.artistName).getSongs().delete(s);
        songList.remove(s);
        System.out.println("La canción "+code+" fue eliminada exitosamente");
    }

    // Method to update a song
    public void updateSong(String code, String name, String album, String cover, int year, int durationOnSeconds, String youtubeURL, Genre genre, String artistName) throws SongsException {
        Song oldSong = getSong(code);
        if(oldSong == null)  throw new SongsException("La canción no se encuentra agregada");
        Song newSong = new Song(code, name, album, cover, year, durationOnSeconds, youtubeURL, genre, artistName);
        Artist a = getArtistByName(oldSong.getArtistName());
        a.getSongs().modifyNode(a.getSongs().getNodePosition(oldSong), newSong);
        songList.setValue(songList.getPosition(getSong(code)), newSong);
        System.out.println("La canción "+code+" fue actualizada correctamente");
    }

    //Method for returning a ObservableList with the names of all the Artist added on the artistList
    public ObservableList<String> getAllArtistNames() {
        ObservableList<String> artistNames = FXCollections.observableArrayList();
        Iterator<Artist> iterator = artistList.iterator();
        while(iterator.hasNext()){
            artistNames.add(iterator.next().getName());
        }
        return artistNames;
    }

    //Method for obtainig an Artist by his name on the artistList
    public Artist getArtistByName(String name) {
        Iterator<Artist> iterator = artistList.iterator();
        while(iterator.hasNext()){
            Artist a = iterator.next();
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //METHODS showJokify menu

    /**
     * Get's the first five favorite songs of an user
     * @param user
     * @return
     */
    public ArrayList<Song> getFiveFavoriteSongs(User user) {
        CircularLinkedList<Song> favoritesSongs = user.getLinkedSong();
        if (favoritesSongs.isEmpty()) {
            return getFiveSongs();
        }
        ArrayList<Song> firstSongs = new ArrayList<>();
        int cont = 0;
        Iterator<Song> iterator = favoritesSongs.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            firstSongs.add(song);
            cont++;
            if (cont >= 5) {
                break;
            }
        }
        return firstSongs;
    }

    /**
     * Get's five songs of the current songs on the store
     * @return
     */
    public ArrayList<Song> getFiveSongs() {
        ArrayList<Song> firstSongs = new ArrayList<>();
        int cont = 0;
        Iterator<Song> iterator = songList.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            firstSongs.add(song);
            cont++;
            if (cont >= 5) {
                break;
            }
        }
        return firstSongs;
    }
}
