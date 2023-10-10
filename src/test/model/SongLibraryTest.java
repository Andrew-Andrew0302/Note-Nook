package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongLibraryTest {
    private SongLibrary list1;
    private Song song1;
    private Song song2;
    private Song song3;


    @BeforeEach
    public void runBefore() {
        list1 = new SongLibrary("Keshi");
        song1 = new Song("Beside You", "Keshi", "Pop", 167);
        song2 = new Song("Somebody", "Keshi", "Pop", 120);
        song3 = new Song("Less of You", "Keshi", "Pop", 206);

    }

    @Test
    public void testConstructor() {
        assertEquals(0, list1.getSongsSize());
    }

    @Test
    public void testAddSong() {
        list1.addSong(song1);
        assertEquals(song1, list1.getSong(1));
        assertEquals(1, list1.getSongsSize());
    }

    @Test
    public void testMultipleSong() {
        list1.addSong(song1);
        list1.addSong(song2);
        assertEquals(song1, list1.getSong(1));
        assertEquals(song2, list1.getSong(2));
        assertEquals(2, list1.getSongsSize());
    }

    @Test
    public void testRemoveSong() {
        list1.addSong(song1);
        list1.removeSong(song1);
        assertEquals(0, list1.getSongsSize());
    }

    @Test
    public void testMultipleRemoveSong() {
        list1.addSong(song1);
        list1.addSong(song2);
        list1.addSong(song3);
        list1.removeSong(song1);
        list1.removeSong(song2);
        assertEquals(1, list1.getSongsSize());
        assertEquals(song3, list1.getSong(1));
    }

    @Test
    public void testPlay() {
        list1.addSong(song1);
        list1.addSong(song2);
        list1.addSong(song3);
        assertEquals(song1, list1.play());
    }

}
