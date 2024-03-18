package co.edu.uniquindio.estr.jokify.model;

import co.edu.uniquindio.estr.jokify.exceptions.AttributesException;
import co.edu.uniquindio.estr.jokify.exceptions.UserException;
import co.edu.uniquindio.estr.jokify.structures.BinarySearchTree;
import co.edu.uniquindio.estr.jokify.structures.HashMapCustom;
import co.edu.uniquindio.estr.jokify.structures.LinkedList;

import java.util.Iterator;
import java.util.Objects;

public class Store {

    //Attributes for the Store class
    String name;
    LinkedList<Song> songList;
    BinarySearchTree<Artist> artistList;
    HashMapCustom<String, User> userList;

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

}
