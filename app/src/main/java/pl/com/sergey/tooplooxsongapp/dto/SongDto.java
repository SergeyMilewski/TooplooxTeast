package pl.com.sergey.tooplooxsongapp.dto;

import pl.com.sergey.tooplooxsongapp.pojo.ItunsSong;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;

import static pl.com.sergey.tooplooxsongapp.util.Utils.getLongFromString;

/**
 * Created by sergey on 29.11.17.
 */

public class SongDto {

    private static final String EMPTY_STRING = "";
    final String song;
    final String nameSinger;
    final long releaseDate;
    final String urlImg;



    public SongDto(String song, String nameSinger, long releaseDate, String url) {
        this.song = song;
        this.nameSinger = nameSinger;
        this.releaseDate = releaseDate;
        this.urlImg = "";
    }


    public SongDto(LocalSong localSong){
        song = localSong.getSongClean() == null ? EMPTY_STRING : localSong.getSongClean();
        nameSinger = localSong.getaRTISTCLEAN() == null ? EMPTY_STRING : localSong.getaRTISTCLEAN();
        releaseDate = 0;
        this.urlImg = "";
    }


    public SongDto(ItunsSong itunsSong){
        song = itunsSong.getTrackName() == null ? EMPTY_STRING : itunsSong.getTrackName();
        nameSinger = itunsSong.getArtistName() == null ? EMPTY_STRING : itunsSong.getArtistName();
        releaseDate = itunsSong.getReleaseDate() == null ? 0 : getLongFromString(itunsSong.getReleaseDate());
        urlImg = itunsSong.getArtworkUrl100();
    }

    public String getSong() {
        return song;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public String getUrlImg() {
        return urlImg;
    }
}
