package co.edu.uniquindio.estr.jokify.model;

import co.edu.uniquindio.estr.jokify.structures.DoublyLinkedList;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Artist implements Comparable<Artist>, Serializable {

    //Serial ID for Serializable
    private static final long serialVersionUID = 1L;

    //Attributes for the Artist class
    String code;
    String name;
    String Nationality;
    boolean isPartOfBand;
    DoublyLinkedList<Song> songs;

    //Empty constructor for Artist class
    public Artist(){

    }

    //Principal Constructor for the Artist class
    public Artist(String name, String nationality, boolean isPartOfBand) {
        this.code = UUID.randomUUID().toString();
        this.name = name;
        Nationality = nationality;
        this.isPartOfBand = isPartOfBand;
        this.songs = new DoublyLinkedList<Song>();

    }

    //Secondary Contructor for the Artist class
    public Artist(String code, String name, String nationality, boolean isPartOfBand) {
        this.code = code;
        this.name = name;
        Nationality = nationality;
        this.isPartOfBand = isPartOfBand;
        this.songs = new DoublyLinkedList<Song>();

    }

    //getters() & setters() for Artist class
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNationality() {
        return Nationality;
    }
    public void setNationality(String nationality) {
        Nationality = nationality;
    }
    public boolean isPartOfBand() {
        return isPartOfBand;
    }
    public void setPartOfBand(boolean partOfBand) {
        isPartOfBand = partOfBand;
    }
    public DoublyLinkedList<Song> getSongs() {
        return songs;
    }
    public void setSongs(DoublyLinkedList<Song> songs) {
        this.songs = songs;
    }

    //equals() for the Artist class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(getCode(), artist.getCode());
    }

    //hashCode() for the Artist class
    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    //toString() for the Artist class
    @Override
    public String toString() {
        return "Artist{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", isPartOfBand=" + isPartOfBand +
                '}';
    }

    //compareTo() for Artist class
    @Override
    public int compareTo(Artist o) {
        return this.getCode().compareTo(o.getCode());
    }
}
