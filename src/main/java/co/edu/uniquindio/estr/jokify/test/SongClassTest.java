package co.edu.uniquindio.estr.jokify.test;

import co.edu.uniquindio.estr.jokify.model.Song;
import co.edu.uniquindio.estr.jokify.model.enums.Genre;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongClassTest {

    /**
     * --------------------------------------------------------------------------------------------------------------
     * Test for Song class
     * Every Model class is built the same way, so if all the tests pass for one class, they should pass for the others.
     * --------------------------------------------------------------------------------------------------------------
     */

    // Test for Song constructor, checking if the object is not null.
    @Test
    public void testSongConstructor() {
        Song song = new Song("name", "album", "cover", 2021, 180, "youtubeURL", Genre.ROCK, "artistName");
        assertNotNull(song);
    }

    // Test for Song getters, checking if the values are the same as the ones passed in the constructor.
    @Test
    public void testSongGetters() {
        Song song = new Song("name", "album", "cover", 2021, 180, "youtubeURL", Genre.ROCK, "artistName");
        assertEquals("name", song.getName());
        assertEquals("album", song.getAlbum());
        assertEquals("cover", song.getCover());
        assertEquals(2021, song.getYear());
        assertEquals(180, song.getDurationOnSeconds());
        assertEquals("youtubeURL", song.getYoutubeURL());
        assertEquals(Genre.ROCK, song.getGenre());
        assertEquals("artistName", song.getArtistName());
    }

    // Test for Song setters, checking if the values are the same as the ones passed in the setters.
    @Test
    public void testSongSetters() {
        Song song = new Song("name", "album", "cover", 2021, 180, "youtubeURL", Genre.ROCK, "artistName");
        song.setCode("code");
        song.setName("newName");
        song.setAlbum("newAlbum");
        song.setCover("newCover");
        song.setYear(2022);
        song.setDurationOnSeconds(200);
        song.setYoutubeURL("newYoutubeURL");
        song.setGenre(Genre.POP);
        song.setArtistName("newArtistName");
        assertEquals("code", song.getCode());
        assertEquals("newName", song.getName());
        assertEquals("newAlbum", song.getAlbum());
        assertEquals("newCover", song.getCover());
        assertEquals(2022, song.getYear());
        assertEquals(200, song.getDurationOnSeconds());
        assertEquals("newYoutubeURL", song.getYoutubeURL());
        assertEquals(Genre.POP, song.getGenre());
        assertEquals("newArtistName", song.getArtistName());
    }

    // Test for Song equals, checking if two songs are not equal.
    @Test
    public void testSongNotEquals() {
        Song song1 = new Song("code1","name", "album", "cover", 2021, 180, "youtubeURL", Genre.ROCK, "artistName");
        Song song2 = new Song("code2", "name2", "album2", "cover2", 2022, 200, "youtubeURL2", Genre.POP, "artistName2");
        assertNotEquals(song1, song2);
    }
}
