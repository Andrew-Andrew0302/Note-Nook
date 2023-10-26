package persistence;

import model.Song;
import model.SongLibrary;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SongLibrary sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySongLibrary.json");
        try {
            SongLibrary sl = reader.read();
            assertEquals("Ripley", sl.getAlbum());
            assertEquals(0, sl.getSongsSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSongLibrary.json");
        try {
            SongLibrary sl = reader.read();
            List<Song> song = sl.viewSong();
            assertEquals("Ripley", sl.getAlbum());
            assertEquals(2, sl.getSongsSize());
            checkSong(1, "Skeletons","Keshi","Pop",250, song.get(0));
            checkSong(2, "Less Of You","Keshi","Pop",200, song.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
