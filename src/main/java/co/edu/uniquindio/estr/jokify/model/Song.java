package co.edu.uniquindio.estr.jokify.model;

import co.edu.uniquindio.estr.jokify.model.enums.Genre;

import java.util.Objects;
import java.util.UUID;

public class Song implements Comparable<Song> {

    //Attributes for Song class
    String code;
    String name;
    String album;
    String cover;
    int year;
    int durationOnSeconds;
    int likes;
    String youtubeURL;
    Genre genre;
    String artistName;

    //Empty construtor for Song class
    public Song(){

    }

    //Principal Constructor for the Song class
    public Song(String name, String album, String cover, int year, int durationOnSeconds, int likes, String youtubeURL, Genre genre, String artistName) {
        //Generate the code automatically using UUID
        this.code = UUID.randomUUID().toString();
        this.name = name;
        this.album = album;
        this.cover = cover;
        this.year = year;
        this.durationOnSeconds = durationOnSeconds;
        this.likes = likes;
        this.youtubeURL = youtubeURL;
        this.genre = genre;
        this.artistName = artistName;
    }

    //Secondary Constructor for the Song class
    public Song(String code, String name, String album, String cover, int year, int durationOnSeconds, String youtubeURL, Genre genre, String artistName) {
        this.code = code;
        this.name = name;
        this.album = album;
        this.cover = cover;
        this.year = year;
        this.durationOnSeconds = durationOnSeconds;
        this.youtubeURL = youtubeURL;
        this.genre = genre;
        this.artistName = artistName;
    }

    //getters() & setters() for the Song class
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
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getDurationOnSeconds() {
        return durationOnSeconds;
    }
    public void setDurationOnSeconds(int durationOnSeconds) {
        this.durationOnSeconds = durationOnSeconds;
    }
    public String getYoutubeURL() {
        return youtubeURL;
    }
    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    //equals() for the Song class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return Objects.equals(getCode(), song.getCode());
    }

    //hashCode() for the Song class
    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    //toString() for the Song class
    @Override
    public String toString() {
        return "Song{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", cover='" + cover + '\'' +
                ", year=" + year +
                ", durationOnSeconds=" + durationOnSeconds +
                ", youtubeURL='" + youtubeURL + '\'' +
                ", genre=" + genre +
                '}';
    }

    //compareTo() for the Song class()
    @Override
    public int compareTo(Song o) {
        return this.getCode().compareTo(o.getCode());
    }

}
