package co.edu.uniquindio.estr.jokify.model;

import co.edu.uniquindio.estr.jokify.exceptions.*;
import co.edu.uniquindio.estr.jokify.model.enums.Genre;
import co.edu.uniquindio.estr.jokify.serialization.Persistence;
import co.edu.uniquindio.estr.jokify.serialization.threads.LoadBinaryResource;
import co.edu.uniquindio.estr.jokify.structures.*;
import co.edu.uniquindio.estr.jokify.structures.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Store implements Serializable {

    //Serial ID for Serializable
    @Serial
    private static final long serialVersionUID = 1L;

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

    //Empty constructor for the Store class
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
    }

    /**
     * Return the instance of the store
     * @return
     */
    public static Store getInstance() {
        if (store == null) {
            store = new Store("Jokify");
            //------------------------------------------------------------------------------
            //loadBinaryResource()
            LoadBinaryResource t1 = new LoadBinaryResource();
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //------------------------------------------------------------------------------
        }
        return store;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Addresses for serialization files
    public static final String ADDRESS_FILE_STOREDAT = "src/main/resources/store.dat";

    // Methods to serialize and deserialize the information of the global class miSubastas ----------------------------------------------------------------------------------------------------------------------

    // saveBinaryResource()
    public static void serializeBinary() {
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Data is stored in files
        Persistence.serializeBinary(ADDRESS_FILE_STOREDAT, store);
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
    }

    // loadBinaryResource()
    public static void deserializeBinary() {
        if (Persistence.deserializeBinary(ADDRESS_FILE_STOREDAT) != null) {
            store = Persistence.deserializeBinary(ADDRESS_FILE_STOREDAT);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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

    /**
     * Add a song to the favorite list of the user
     * @param song
     * @param user
     */
    public void addSongToFavorites(User user, Song song) {
        user.getLinkedSong().addFirst(song);
    }

    /**
     * Remove a song from the davorite list of the user
     * @param song
     * @param user
     */
    public void removeSongFromFavorites(User user, Song song) {
        user.getLinkedSong().delete(song);
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

    /**
     * Return a list of songs related to the userInput
     * @param input
     * @return
     */
    public List<Song> getSongsSearch(String input) throws SearchException {
        input.toLowerCase();
        String[] keywords = input.split("&");
        System.out.println(Arrays.toString(keywords));
        System.out.println("Length: " + keywords.length);
        List<Song> searchSongs = new ArrayList<>();
        if (keywords.length == 0) {
            throw new SearchException("No se encontraron canciones que satisfagan tú busqueda");
        } if (keywords.length == 1) {
            searchSongs.addAll(searchArtistSongs(keywords[0]));
            searchSongs.addAll(searchSong(keywords[0]));
        } if (keywords.length == 2) {
            //Asume that the users search with OR
            searchSongs.addAll(searchSongsOrAsync(keywords[0], keywords[2]));
        } else if (keywords.length == 3) {
            if (keywords[1].equals("y")) {
                searchSongs.addAll(searchSongsAndAsync(keywords[0], keywords[2]));
            } else if (keywords[1].equals("o")) {
                searchSongs.addAll(searchSongsOrAsync(keywords[0], keywords[2]));
            }
        }
        if (searchSongs.isEmpty()) {
            throw new SearchException("No se encontraron canciones que satisfagan tú busqueda");
        }
        return searchSongs;
    }

    /**
     * Reurn a list of artist related to some songs
     * @param songs
     * @return
     */
    public List<Artist> getArtistsSearch(List<Song> songs) {
        Set<Artist> artistSet = new HashSet<>();
        for (Song song : songs) {
            artistSet.add(getArtistByName(song.artistName));
        }
        return new ArrayList<>(artistSet);
    }

    /**
     * Return a list of songs that has a specific parameter
     * @param song
     * @return
     */
    private List<Song> searchSong(String song) {
        List<Song> songs = new ArrayList<>();
        for (Song current : songList) {
            if (current.getName().toLowerCase().equals(song) || current.album.toLowerCase().equals(song)) {
                songs.add(current);
            }
        }
        return songs;
    }

    // 1. ------------------------------------------------------------------------------------------------
    //Artist Search: Because artists are stored in a binary tree, their
    //search is very efficient, therefore, given the name of an Artist it should return its
    //songs list.

    /**
     * Method to search for an artist by his name and return their list of songs
     * @param artistName
     * @return
     */
    private List<Song> searchArtistSongs(String artistName) {
        // Search for the artist by name
        Artist artist = searchArtistByName(artistName);
        // If artist is found, return their list of songs
        if (artist != null) {
            return (List<Song>) artist.getSongs().toList();
        } else {
            // If artist is not found, print an error message and return an empty List
            System.out.println("Artist not found: " + artistName);
            return new ArrayList<>();
        }
    }

    /**
     * Method to search an artist by name
     * @param artistName
     * @return
     */
    private Artist searchArtistByName(String artistName) {
        // Iterate through the artist list
        Iterator<Artist> iterator = artistList.iterator();
        while (iterator.hasNext()) {
            // Get the next artist
            Artist artist = iterator.next();
            // Check if the artist name matches the given name
            if (artist.getName().toLowerCase().equals(artistName)) {
                return artist; // Return the artist if found
            }
        }
        return null; // Return null if artist not found
    }

    // 2. ------------------------------------------------------------------------------------------------

    //Search OR: Given the values of two or more attributes of a Song, return a list
    //with songs with at least one matching attribute.

    /**
     * Method for performing OR search for songs, executed in a separate thread
     * @param attribute1
     * @param attribute2
     * @return
     */
    private List<Song> searchSongsOrAsync(String attribute1, String attribute2) {
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

    /**
     * Method to set the search result
     * @param result
     */
    private synchronized void setResultOr(List<Song> result) {
        this.resultOrSearch = result;
    }

    /**
     * Method to get the search result
     * @return
     */
    private synchronized List<Song> getResultOr() {
        return resultOrSearch;
    }

    /**
     * Method for performing OR search for songs
     * @param attribute1
     * @param attribute2
     * @return
     */
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

    /**
     * Method to check if at least one of the attributes of a song matches any of the provided values
     * @param song
     * @param attribute1
     * @param attribute2
     * @return
     */
    private boolean songMatchesAttributes(Song song, String attribute1, String attribute2) {
        return song.getName().toLowerCase().equals(attribute1) ||
                song.getAlbum().toLowerCase().equals(attribute1) ||
                song.getName().toLowerCase().equals(attribute2) ||
                song.getAlbum().toLowerCase().equals(attribute2) ||
                song.getArtistName().toLowerCase().equals(attribute1) ||
                song.getArtistName().toLowerCase().equals(attribute2);
    }

    // 3. ------------------------------------------------------------------------------------------------

    /**
     * Method for performing AND search for songs, executed in a separate thread
     * @param attribute1
     * @param attribute2
     * @return
     */
    private List<Song> searchSongsAndAsync(String attribute1, String attribute2) {
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

    /**
     * Method to set the search result
     * @param result
     */
    private synchronized void setResultAnd(List<Song> result) {
        this.resultAndSearch = result;
    }

    /**
     * Method to get the search result
     * @return
     */
    private synchronized List<Song> getResultAnd() {
        return resultAndSearch;
    }

    /**
     * Method for performing AND search for songs
     * @param attribute1
     * @param attribute2
     * @return
     */
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

    /**
     * Method to check if all attributes of a song match the provided values
     * @param song
     * @param attribute1
     * @param attribute2
     * @return
     */
    private boolean songMatchesAllAttributes(Song song, String attribute1, String attribute2) {
        return (song.getName().toLowerCase().equals(attribute1) || song.getAlbum().toLowerCase().equals(attribute1)) &&
                (song.getName().toLowerCase().equals(attribute2) || song.getAlbum().toLowerCase().equals(attribute2));
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
        if(getArtist(updatedArtist.getCode()).getSongs().isEmpty()){
            if (artistExists(updatedArtist)) {
                Artist oldArtist = getArtist(updatedArtist.getCode());
                artistList.remove(updatedArtist);
                artistList.insert(updatedArtist);
                System.out.println("Artista actualizado exitosamente: " + updatedArtist);
            } else {
                throw new ArtistsException("El artista con el código " + updatedArtist.getCode() + " no existe.");
            }
        }
        else{
            throw new ArtistsException("El artista no puede ser modificado, ya que tiene canciones agregadas");
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

    // FUNCTIONS FOR THE STATISTICS -----------------------------------------------------------------------

    /**
     * Method to find the most repeated genre
     * @return
     */
    public Genre findMostRepeatedGenre() {
        if (songList.isEmpty()) {
            return null;
        }
        Genre mostRepeatedGenre = null;
        int maxCount = 0;
        Iterator<Song> iterator = songList.iterator();
        while (iterator.hasNext()) {
            Genre currentGenre = iterator.next().getGenre();
            int currentCount = 1;
            Iterator<Song> innerIterator = songList.iterator();
            while (innerIterator.hasNext()) {
                if (innerIterator.next().getGenre() == currentGenre) {
                    currentCount++;
                }
            }
            if (currentCount > maxCount) {
                mostRepeatedGenre = currentGenre;
                maxCount = currentCount;
            }
        }
        return mostRepeatedGenre;
    }

    /**
     * Method to find the most liked song
     * @return
     */
    public Song findMostLikedSong() {
        if (songList.isEmpty()) {
            System.out.println("Lista de canciones vacía");
            return null;
        }
        Song mostLikedSong = null;
        int maxLikes = Integer.MIN_VALUE;
        Iterator<Song> iterator = songList.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            int likes = song.getLikes();
            if (likes > maxLikes) {
                mostLikedSong = song;
                maxLikes = likes;
            }
        }
        return mostLikedSong;
    }

}
