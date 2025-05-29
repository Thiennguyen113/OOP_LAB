package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<>();

    public CompactDisc( String title, String category, String director, int length, float cost, String artist) {

        super( title, category, cost, director, length);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
        } else {
            System.out.println("Track already exists.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
        } else {
            System.out.println("Track not found.");
        }
    }

    public int getTrạckTotalLength() {
        int totalLength = 0;
        for (Track t : tracks) {
            totalLength += t.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (tracks.isEmpty()) {
            throw new PlayerException("ERROR: CD has no tracks!");
        }
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("Total length: " + this.getTrạckTotalLength() + " minutes");

        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException e) {
                throw new PlayerException("Failed to play track: " + track.getTitle() + " - " + e.getMessage());
            }
        }
    }

    @Override
    public String play(boolean visualMode) throws PlayerException {
        if (tracks.isEmpty()) {
            throw new PlayerException("ERROR: CD has no tracks!");
        }
        if (getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

        StringBuilder result = new StringBuilder();
        result.append("Playing CD: ").append(this.getTitle())
                .append(" by ").append(this.artist).append("\n");

        for (Track t : tracks) {
            try {
                result.append(t.play(visualMode)).append("\n");
            } catch (PlayerException e) {
                throw new PlayerException("Error playing track: " + t.getTitle() + " - " + e.getMessage());
            }
        }

        if (visualMode) {
            System.out.println(result.toString());
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return "[CD] " + super.toString() + " - Artist: " + artist + " - Tracks: " + tracks.size();
    }
}