package model;

import persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

// Represents the songs in the song library that has an Album name
public class SongLibrary implements Writable {
    private final List<Song> songs;
    private final String album;

    // REQUIRES: album has a non-zero length
    // EFFECTS: name of album is set as album
    public SongLibrary(String album) {
        songs = new ArrayList<>();
        this.album = album;
    }

    // EFFECTS: Get the length of the song list
    public int getSongsSize() {
        return songs.size();
    }

    // REQUIRES: 0 < i < songs.size()
    // EFFECTS: get the song based on where it is on the list (without 0th indexing)
    public Song getSong(int i) {
        return songs.get(i - 1);
    }

    // MODIFIES: this
    // EFFECTS: add a song to the song library if the song is not already in the library.
    public void addSong(Song song) {
        songs.add(song);
    }

    // EFFECTS: returns the list of songs in the song library
    public List<Song> viewSong() {
        return songs;
    }

    // REQUIRES: songs.size() > 0
    // MODIFIES: this
    // EFFECTS: remove a song from the song library
    public void removeSong(String name) {
        for (Song s : songs) {
            if (Objects.equals(s.getName(),name)) {
                songs.remove(s);
                break;
            }
        }
    }

    // REQUIRES: songs.size() > 0
    // EFFECTS: Returns a random song from the library
    public Song getRandomSong() {
        Random random = new Random();
        int randomIndex = random.nextInt(songs.size());
        return songs.get(randomIndex);
    }


    // REQUIRES: songs.size() > 0
    // EFFECTS: Picks a random song to play in the song library and add it
    // to a list and that would be the order the songs are played in
    // public List<Song> shuffle() {
    //    Random ran = new Random();
    //    List<Song> playedSongs = new ArrayList<>();
     //   while (songs.size() > 0) {
      //      int pick = ran.nextInt(songs.size());
      //      songs.remove(pick);
      //      playedSongs.add(songs.get(pick));
      //  }
      //  return playedSongs;
 //   }


    // REQUIRES: songs.size() > 0
    // EFFECTS: plays the first song in the song library
    public String play() {
        Song s = songs.get(0);
        return s.getName();
    }

    public String getAlbum() {
        return this.album;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("album", album);
        json.put("Songs", songsToJson());
        return json;
    }

    // EFFECTS: returns songs in this SongLibrary as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : songs) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
