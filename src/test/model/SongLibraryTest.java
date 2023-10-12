package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongLibraryTest {
    private SongLibrary list0;
    private SongLibrary list1;
    private SongLibrary list2;
    private Song song1;
    private Song song2;
    private Song song3;



    @BeforeEach
    public void runBefore() {
        list0 = new SongLibrary("Millie");
        list1 = new SongLibrary("Keshi");
        list2 = new SongLibrary("Ripley");
        song1 = new Song(1,"Beside You", "Keshi", "Pop", 167);
        song2 = new Song(2,"Somebody", "Keshi", "Pop", 120);
        song3 = new Song(3,"Less of You", "Keshi", "Pop", 206);
        list1.addSong(song1);
        list2.addSong(song1);
        list2.addSong(song2);
        list2.addSong(song3);


    }

    @Test
    public void testConstructor() {
        assertEquals(0, list0.getSongsSize());
        assertEquals("Keshi",list1.getAlbum());
    }

    @Test
    public void testAddSong() {
        list0.addSong(song1);
        assertEquals(song1, list0.getSong(1));
        assertEquals(1, list0.getSongsSize());
    }

    @Test
    public void testMultipleSong() {
        list0.addSong(song1);
        list0.addSong(song2);
        assertEquals(song1, list0.getSong(1));
        assertEquals(song2, list0.getSong(2));
        assertEquals(2, list0.getSongsSize());
    }

    @Test
    public void testRemoveSong() {
        list1.removeSong("Beside You");
        assertEquals(0, list1.getSongsSize());
    }

    @Test
    public void testMultipleRemoveSong() {
        list2.removeSong("Beside You");
        list2.removeSong("Somebody");
        assertEquals(1, list2.getSongsSize());
        assertEquals(song3, list2.getSong(1));
    }

    @Test
    public void testPlay() {
        assertEquals(song1, list2.play());
        assertEquals(song1, list1.play());
    }

    @Test
    public void testViewSong() {
        SongLibrary testView = new SongLibrary("Krab");
        testView.addSong(song1);
        assertEquals(testView.viewSong(), list1.viewSong());
    }

    @Test
    public void testGetRandomSong() {
        assertEquals(song1, list1.getRandomSong());
    }

    @Test
    public void testRemoveNoSong() {
        list1.removeSong("Less of You");
        assertEquals(1, list1.getSongsSize());
    }



}
