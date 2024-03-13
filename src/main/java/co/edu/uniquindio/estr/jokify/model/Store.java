package co.edu.uniquindio.estr.jokify.model;

import co.edu.uniquindio.estr.jokify.structures.LinkedList;

import java.util.Objects;

public class Store {

    //Attributes for the Store class
    String name;
    LinkedList<Song> songList;
    LinkedList<Artist> artistList;
    LinkedList<User> userList;

    //Empty constuctor for the Store class
    public Store(){

    }

    //Constructor for the Store class
    public Store(String name) {
        this.name = name;
        this.songList = new LinkedList<Song>();
        this.artistList = new LinkedList<Artist>();
        this.userList = new LinkedList<User>();
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
    public LinkedList<Artist> getArtistList() {
        return artistList;
    }
    public void setArtistList(LinkedList<Artist> artistList) {
        this.artistList = artistList;
    }
    public LinkedList<User> getUserList() {
        return userList;
    }
    public void setUserList(LinkedList<User> userList) {
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

}
