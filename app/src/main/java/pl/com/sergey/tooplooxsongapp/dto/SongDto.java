package pl.com.sergey.tooplooxsongapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import pl.com.sergey.tooplooxsongapp.pojo.ItunsSong;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;

import static pl.com.sergey.tooplooxsongapp.util.Utils.getLongFromString;

/**
 * Created by sergey on 29.11.17.
 */

public class SongDto implements Parcelable {

    private static final String EMPTY_STRING = "";
    final String song;
    final String nameSinger;
    final String releaseDate;
    final String urlImg;
    final String source;


    public SongDto(String song, String nameSinger, String releaseDate, String url, String source) {
        this.song = song;
        this.nameSinger = nameSinger;
        this.releaseDate = releaseDate;
        this.urlImg = url;
        this.source = source;
    }


    public SongDto(LocalSong localSong, String source) {
        this.song = localSong.getSongClean() == null ? EMPTY_STRING : localSong.getSongClean();
        this.nameSinger = localSong.getaRTISTCLEAN() == null ? EMPTY_STRING : localSong.getaRTISTCLEAN();
        this.releaseDate = localSong.getReleaseYear();
        this.urlImg = null;
        this.source = source;
    }

    public SongDto(ItunsSong itunsSong, String source) {
        this.song = itunsSong.getTrackName() == null ? EMPTY_STRING : itunsSong.getTrackName();
        this.nameSinger = itunsSong.getArtistName() == null ? EMPTY_STRING : itunsSong.getArtistName();
        this.releaseDate = itunsSong.getReleaseDate() == null ? EMPTY_STRING : getLongFromString(itunsSong.getReleaseDate());
        this.urlImg = itunsSong.getArtworkUrl100();
        this.source = source;
    }

    protected SongDto(Parcel in) {
        song = in.readString();
        nameSinger = in.readString();
        releaseDate = in.readString();
        urlImg = in.readString();
        source = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(song);
        dest.writeString(nameSinger);
        dest.writeString(releaseDate);
        dest.writeString(urlImg);
        dest.writeString(source);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SongDto> CREATOR = new Creator<SongDto>() {
        @Override
        public SongDto createFromParcel(Parcel in) {
            return new SongDto(in);
        }

        @Override
        public SongDto[] newArray(int size) {
            return new SongDto[size];
        }
    };

    public String getSong() {
        return song;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public String getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongDto songDto = (SongDto) o;

        if (getSong() != null ? !getSong().equals(songDto.getSong()) : songDto.getSong() != null)
            return false;
        if (getNameSinger() != null ? !getNameSinger().equals(songDto.getNameSinger()) : songDto.getNameSinger() != null)
            return false;
        if (getReleaseDate() != null ? !getReleaseDate().equals(songDto.getReleaseDate()) : songDto.getReleaseDate() != null)
            return false;
        if (getUrlImg() != null ? !getUrlImg().equals(songDto.getUrlImg()) : songDto.getUrlImg() != null)
            return false;
        return getSource() != null ? getSource().equals(songDto.getSource()) : songDto.getSource() == null;
    }

    @Override
    public int hashCode() {
        int result = getSong() != null ? getSong().hashCode() : 0;
        result = 31 * result + (getNameSinger() != null ? getNameSinger().hashCode() : 0);
        result = 31 * result + (getReleaseDate() != null ? getReleaseDate().hashCode() : 0);
        result = 31 * result + (getUrlImg() != null ? getUrlImg().hashCode() : 0);
        result = 31 * result + (getSource() != null ? getSource().hashCode() : 0);
        return result;
    }
}
