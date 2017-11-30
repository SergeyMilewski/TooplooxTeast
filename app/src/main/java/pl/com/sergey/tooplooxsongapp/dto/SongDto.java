package pl.com.sergey.tooplooxsongapp.dto;

import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;

/**
 * Created by sergey on 29.11.17.
 */

public class SongDto {

    public SongDto(String song, String nameSinger, long releaseDate) {
        this.song = song;
        this.nameSinger = nameSinger;
        this.releaseDate = releaseDate;
    }


    public SongDto(LocalSong localSong){
        song = localSong.getSongClean() == null ? "" : localSong.getSongClean();
        nameSinger = localSong.getaRTISTCLEAN() == null ? "" : localSong.getaRTISTCLEAN();
        releaseDate =
    }

    final String song;
    final String nameSinger;
    final long releaseDate;
}
