package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongTest {
    private Song test1;
    private Song test2;

    @BeforeEach
    public void runBefore() {
        test1 = new Song(1,"24 Hours", "Anthony Russo", "Pop", 150);
        test2 = new Song(2,"Trippin", "Adam Haliday", "Pop", 200);
    }

    @Test
    public void testConstructor() {
        assertEquals("24 Hours", test1.getName());
        assertEquals("Anthony Russo", test1.getArtist());
        assertEquals("Pop", test1.getGenre());
        assertEquals(150, test1.getDuration());
    }

    @Test
    public void multipleSongConstructors() {
        assertEquals("24 Hours", test1.getName());
        assertEquals("Trippin", test2.getName());
        assertEquals(2, test2.getId());
    }
}