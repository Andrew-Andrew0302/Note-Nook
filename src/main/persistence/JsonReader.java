package persistence;

import model.Song;
import model.SongLibrary;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SongLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSongLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses SongLibrary from JSON object and returns it
    private SongLibrary parseSongLibrary(JSONObject jsonObject) {
        String album = jsonObject.getString("album");
        SongLibrary sl = new SongLibrary(album);
        addSongs(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses Songs from JSON object and adds them to SongLibrary
    private void addSongs(SongLibrary sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(sl, nextSong);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses Song from JSON object and adds it to SongLibrary
    private void addSong(SongLibrary sl, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String artist = jsonObject.getString("artist");
        String genre = jsonObject.getString("genre");
        int duration = jsonObject.getInt("duration");
        Song song = new Song(id, name, artist, genre, duration);
        sl.addSong(song);
    }
}
