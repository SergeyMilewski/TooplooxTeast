package pl.com.sergey.tooplooxsongapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by smilevkiy on 29.11.17.
 */

public class LocalSong {

    @SerializedName("Song Clean")
    @Expose
    private String songClean;
    @SerializedName("ARTIST CLEAN")
    @Expose
    private String aRTISTCLEAN;
    @SerializedName("Release Year")
    @Expose
    private int releaseYear;
    @SerializedName("COMBINED")
    @Expose
    private String cOMBINED;
    @SerializedName("First?")
    @Expose
    private int first;
    @SerializedName("Year?")
    @Expose
    private int year;
    @SerializedName("PlayCount")
    @Expose
    private int playCount;
    @SerializedName("F*G")
    @Expose
    private int fG;

    public String getSongClean() {
        return songClean;
    }

    public String getaRTISTCLEAN() {
        return aRTISTCLEAN;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getcOMBINED() {
        return cOMBINED;
    }

    public int getFirst() {
        return first;
    }

    public int getYear() {
        return year;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getfG() {
        return fG;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalSong)) return false;

        LocalSong localSong = (LocalSong) o;

        if (getReleaseYear() != localSong.getReleaseYear()) return false;
        if (getFirst() != localSong.getFirst()) return false;
        if (getYear() != localSong.getYear()) return false;
        if (getPlayCount() != localSong.getPlayCount()) return false;
        if (getfG() != localSong.getfG()) return false;
        if (getSongClean() != null ? !getSongClean().equals(localSong.getSongClean()) : localSong.getSongClean() != null)
            return false;
        if (getaRTISTCLEAN() != null ? !getaRTISTCLEAN().equals(localSong.getaRTISTCLEAN()) : localSong.getaRTISTCLEAN() != null)
            return false;
        return getcOMBINED() != null ? getcOMBINED().equals(localSong.getcOMBINED()) : localSong.getcOMBINED() == null;
    }

    @Override
    public int hashCode() {
        int result = getSongClean() != null ? getSongClean().hashCode() : 0;
        result = 31 * result + (getaRTISTCLEAN() != null ? getaRTISTCLEAN().hashCode() : 0);
        result = 31 * result + getReleaseYear();
        result = 31 * result + (getcOMBINED() != null ? getcOMBINED().hashCode() : 0);
        result = 31 * result + getFirst();
        result = 31 * result + getYear();
        result = 31 * result + getPlayCount();
        result = 31 * result + getfG();
        return result;
    }
}
