package persistence;

import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSong(int id, String name, String artist, String genre, int duration, Song song) {
        assertEquals(id, song.getId());
        assertEquals(name, song.getName());
        assertEquals(artist, song.getArtist());
        assertEquals(genre, song.getGenre());
        assertEquals(duration, song.getDuration());
    }
}
