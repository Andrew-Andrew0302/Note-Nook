package persistence;

import model.Song;
import model.SongLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            SongLibrary sl = new SongLibrary("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            SongLibrary sl = new SongLibrary("Ripley");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            sl = reader.read();
            assertEquals("Ripley", sl.getAlbum());
            assertEquals(0, sl.getSongsSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            SongLibrary sl = new SongLibrary("Ripley");
            sl.addSong(new Song(1, "Less Of You", "Keshi", "Pop", 200));
            sl.addSong(new Song(2, "Skeletons", "Keshi", "Pop", 250));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            sl = reader.read();
            assertEquals("Ripley", sl.getAlbum());
            List<Song> songs = sl.viewSong();
            assertEquals(2,songs.size());
            checkSong(1, "Less Of You", "Keshi", "Pop", 200, songs.get(0));
            checkSong(2, "Skeletons", "Keshi", "Pop", 250, songs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
