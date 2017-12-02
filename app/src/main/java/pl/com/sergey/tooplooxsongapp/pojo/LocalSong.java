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
    private String releaseYear;
    @SerializedName("COMBINED")
    @Expose
    private String cOMBINED;
    @SerializedName("First?")
    @Expose
    private String first;
    @SerializedName("Year?")
    @Expose
    private String year;
    @SerializedName("PlayCount")
    @Expose
    private String playCount;
    @SerializedName("F*G")
    @Expose
    private String fG;

    private LocalSong(Builder builder) {
        songClean = builder.songClean;
        aRTISTCLEAN = builder.aRTISTCLEAN;
        releaseYear = builder.releaseYear;
        cOMBINED = builder.cOMBINED;
        first = builder.first;
        year = builder.year;
        playCount = builder.playCount;
        fG = builder.fG;
    }

    public String getSongClean() {
        return songClean;
    }

    public String getaRTISTCLEAN() {
        return aRTISTCLEAN;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getcOMBINED() {
        return cOMBINED;
    }

    public String getFirst() {
        return first;
    }

    public String getYear() {
        return year;
    }

    public String getPlayCount() {
        return playCount;
    }

    public String getfG() {
        return fG;
    }


    public static final class Builder {
        private String songClean;
        private String aRTISTCLEAN;
        private String releaseYear;
        private String cOMBINED;
        private String first;
        private String year;
        private String playCount;
        private String fG;

        public Builder() {
        }

        public Builder songClean(String val) {
            songClean = val;
            return this;
        }

        public Builder aRTISTCLEAN(String val) {
            aRTISTCLEAN = val;
            return this;
        }

        public Builder releaseYear(String val) {
            releaseYear = val;
            return this;
        }

        public Builder cOMBINED(String val) {
            cOMBINED = val;
            return this;
        }

        public Builder first(String val) {
            first = val;
            return this;
        }

        public Builder year(String val) {
            year = val;
            return this;
        }

        public Builder playCount(String val) {
            playCount = val;
            return this;
        }

        public Builder fG(String val) {
            fG = val;
            return this;
        }

        public LocalSong build() {
            return new LocalSong(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalSong localSong = (LocalSong) o;

        if (getSongClean() != null ? !getSongClean().equals(localSong.getSongClean()) : localSong.getSongClean() != null)
            return false;
        if (getaRTISTCLEAN() != null ? !getaRTISTCLEAN().equals(localSong.getaRTISTCLEAN()) : localSong.getaRTISTCLEAN() != null)
            return false;
        if (getReleaseYear() != null ? !getReleaseYear().equals(localSong.getReleaseYear()) : localSong.getReleaseYear() != null)
            return false;
        if (getcOMBINED() != null ? !getcOMBINED().equals(localSong.getcOMBINED()) : localSong.getcOMBINED() != null)
            return false;
        if (getFirst() != null ? !getFirst().equals(localSong.getFirst()) : localSong.getFirst() != null)
            return false;
        if (getYear() != null ? !getYear().equals(localSong.getYear()) : localSong.getYear() != null)
            return false;
        if (getPlayCount() != null ? !getPlayCount().equals(localSong.getPlayCount()) : localSong.getPlayCount() != null)
            return false;
        return getfG() != null ? getfG().equals(localSong.getfG()) : localSong.getfG() == null;
    }

    @Override
    public int hashCode() {
        int result = getSongClean() != null ? getSongClean().hashCode() : 0;
        result = 31 * result + (getaRTISTCLEAN() != null ? getaRTISTCLEAN().hashCode() : 0);
        result = 31 * result + (getReleaseYear() != null ? getReleaseYear().hashCode() : 0);
        result = 31 * result + (getcOMBINED() != null ? getcOMBINED().hashCode() : 0);
        result = 31 * result + (getFirst() != null ? getFirst().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getPlayCount() != null ? getPlayCount().hashCode() : 0);
        result = 31 * result + (getfG() != null ? getfG().hashCode() : 0);
        return result;
    }
}
