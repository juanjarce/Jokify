package co.edu.uniquindio.estr.jokify.model;

import co.edu.uniquindio.estr.jokify.structures.CircularLinkedList;

import java.util.Objects;

public class User implements Comparable<User> {

    //Atributes for User class
    //There´s only one admin user "user"-"$aDmiN"
    String username;
    String password;
    String email;
    CircularLinkedList <Song> linkedSong;

    //Empty constructor for User class
    public User(){

    }

    //Constructor for User class
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.linkedSong = new CircularLinkedList<Song>();
    }

    //getters() & setters() for User class
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public CircularLinkedList<Song> getLinkedSong() {
        return linkedSong;
    }
    public void setLinkedSong(CircularLinkedList<Song> linkedSong) {
        this.linkedSong = linkedSong;
    }

    //equals() for User class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUsername(), user.getUsername());
    }

    //hashCode() for User class
    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    //toString() for User class
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //comparteTo() for User Class
    @Override
    public int compareTo(User o) {
        return this.getUsername().compareTo(o.getUsername());
    }
}
