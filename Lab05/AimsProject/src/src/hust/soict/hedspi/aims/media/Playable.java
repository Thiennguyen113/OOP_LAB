package hust.soict.hedspi.aims.media;
import hust.soict.hedspi.aims.exception.PlayerException;

public interface Playable {
    void play() throws PlayerException;

    default String play(boolean returnDetails) throws PlayerException {
        play();
        return "Media played";
    }
}