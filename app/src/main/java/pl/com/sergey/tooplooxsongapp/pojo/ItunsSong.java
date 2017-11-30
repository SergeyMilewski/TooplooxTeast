package pl.com.sergey.tooplooxsongapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergey on 29.11.17.
 */

public class ItunsSong {
    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("artistId")
    @Expose
    private int artistId;
    @SerializedName("collectionId")
    @Expose
    private int collectionId;
    @SerializedName("trackId")
    @Expose
    private int trackId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private double collectionPrice;
    @SerializedName("trackPrice")
    @Expose
    private double trackPrice;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("trackExplicitness")
    @Expose
    private String trackExplicitness;
    @SerializedName("discCount")
    @Expose
    private int discCount;
    @SerializedName("discNumber")
    @Expose
    private int discNumber;
    @SerializedName("trackCount")
    @Expose
    private int trackCount;
    @SerializedName("trackNumber")
    @Expose
    private int trackNumber;
    @SerializedName("trackTimeMillis")
    @Expose
    private int trackTimeMillis;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("isStreamable")
    @Expose
    private boolean isStreamable;

    public String getWrapperType() {
        return wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public int getTrackId() {
        return trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public double getCollectionPrice() {
        return collectionPrice;
    }

    public double getTrackPrice() {
        return trackPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public int getDiscCount() {
        return discCount;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public int getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public boolean isStreamable() {
        return isStreamable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItunsSong itunsSong = (ItunsSong) o;

        if (getArtistId() != itunsSong.getArtistId()) return false;
        if (getCollectionId() != itunsSong.getCollectionId()) return false;
        if (getTrackId() != itunsSong.getTrackId()) return false;
        if (Double.compare(itunsSong.getCollectionPrice(), getCollectionPrice()) != 0) return false;
        if (Double.compare(itunsSong.getTrackPrice(), getTrackPrice()) != 0) return false;
        if (getDiscCount() != itunsSong.getDiscCount()) return false;
        if (getDiscNumber() != itunsSong.getDiscNumber()) return false;
        if (getTrackCount() != itunsSong.getTrackCount()) return false;
        if (getTrackNumber() != itunsSong.getTrackNumber()) return false;
        if (getTrackTimeMillis() != itunsSong.getTrackTimeMillis()) return false;
        if (isStreamable() != itunsSong.isStreamable()) return false;
        if (getWrapperType() != null ? !getWrapperType().equals(itunsSong.getWrapperType()) : itunsSong.getWrapperType() != null)
            return false;
        if (getKind() != null ? !getKind().equals(itunsSong.getKind()) : itunsSong.getKind() != null)
            return false;
        if (getArtistName() != null ? !getArtistName().equals(itunsSong.getArtistName()) : itunsSong.getArtistName() != null)
            return false;
        if (getCollectionName() != null ? !getCollectionName().equals(itunsSong.getCollectionName()) : itunsSong.getCollectionName() != null)
            return false;
        if (getTrackName() != null ? !getTrackName().equals(itunsSong.getTrackName()) : itunsSong.getTrackName() != null)
            return false;
        if (getCollectionCensoredName() != null ? !getCollectionCensoredName().equals(itunsSong.getCollectionCensoredName()) : itunsSong.getCollectionCensoredName() != null)
            return false;
        if (getTrackCensoredName() != null ? !getTrackCensoredName().equals(itunsSong.getTrackCensoredName()) : itunsSong.getTrackCensoredName() != null)
            return false;
        if (getArtistViewUrl() != null ? !getArtistViewUrl().equals(itunsSong.getArtistViewUrl()) : itunsSong.getArtistViewUrl() != null)
            return false;
        if (getCollectionViewUrl() != null ? !getCollectionViewUrl().equals(itunsSong.getCollectionViewUrl()) : itunsSong.getCollectionViewUrl() != null)
            return false;
        if (getTrackViewUrl() != null ? !getTrackViewUrl().equals(itunsSong.getTrackViewUrl()) : itunsSong.getTrackViewUrl() != null)
            return false;
        if (getPreviewUrl() != null ? !getPreviewUrl().equals(itunsSong.getPreviewUrl()) : itunsSong.getPreviewUrl() != null)
            return false;
        if (getArtworkUrl30() != null ? !getArtworkUrl30().equals(itunsSong.getArtworkUrl30()) : itunsSong.getArtworkUrl30() != null)
            return false;
        if (getArtworkUrl60() != null ? !getArtworkUrl60().equals(itunsSong.getArtworkUrl60()) : itunsSong.getArtworkUrl60() != null)
            return false;
        if (getArtworkUrl100() != null ? !getArtworkUrl100().equals(itunsSong.getArtworkUrl100()) : itunsSong.getArtworkUrl100() != null)
            return false;
        if (getReleaseDate() != null ? !getReleaseDate().equals(itunsSong.getReleaseDate()) : itunsSong.getReleaseDate() != null)
            return false;
        if (getCollectionExplicitness() != null ? !getCollectionExplicitness().equals(itunsSong.getCollectionExplicitness()) : itunsSong.getCollectionExplicitness() != null)
            return false;
        if (getTrackExplicitness() != null ? !getTrackExplicitness().equals(itunsSong.getTrackExplicitness()) : itunsSong.getTrackExplicitness() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(itunsSong.getCountry()) : itunsSong.getCountry() != null)
            return false;
        if (getCurrency() != null ? !getCurrency().equals(itunsSong.getCurrency()) : itunsSong.getCurrency() != null)
            return false;
        return getPrimaryGenreName() != null ? getPrimaryGenreName().equals(itunsSong.getPrimaryGenreName()) : itunsSong.getPrimaryGenreName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getWrapperType() != null ? getWrapperType().hashCode() : 0;
        result = 31 * result + (getKind() != null ? getKind().hashCode() : 0);
        result = 31 * result + getArtistId();
        result = 31 * result + getCollectionId();
        result = 31 * result + getTrackId();
        result = 31 * result + (getArtistName() != null ? getArtistName().hashCode() : 0);
        result = 31 * result + (getCollectionName() != null ? getCollectionName().hashCode() : 0);
        result = 31 * result + (getTrackName() != null ? getTrackName().hashCode() : 0);
        result = 31 * result + (getCollectionCensoredName() != null ? getCollectionCensoredName().hashCode() : 0);
        result = 31 * result + (getTrackCensoredName() != null ? getTrackCensoredName().hashCode() : 0);
        result = 31 * result + (getArtistViewUrl() != null ? getArtistViewUrl().hashCode() : 0);
        result = 31 * result + (getCollectionViewUrl() != null ? getCollectionViewUrl().hashCode() : 0);
        result = 31 * result + (getTrackViewUrl() != null ? getTrackViewUrl().hashCode() : 0);
        result = 31 * result + (getPreviewUrl() != null ? getPreviewUrl().hashCode() : 0);
        result = 31 * result + (getArtworkUrl30() != null ? getArtworkUrl30().hashCode() : 0);
        result = 31 * result + (getArtworkUrl60() != null ? getArtworkUrl60().hashCode() : 0);
        result = 31 * result + (getArtworkUrl100() != null ? getArtworkUrl100().hashCode() : 0);
        temp = Double.doubleToLongBits(getCollectionPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getTrackPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getReleaseDate() != null ? getReleaseDate().hashCode() : 0);
        result = 31 * result + (getCollectionExplicitness() != null ? getCollectionExplicitness().hashCode() : 0);
        result = 31 * result + (getTrackExplicitness() != null ? getTrackExplicitness().hashCode() : 0);
        result = 31 * result + getDiscCount();
        result = 31 * result + getDiscNumber();
        result = 31 * result + getTrackCount();
        result = 31 * result + getTrackNumber();
        result = 31 * result + getTrackTimeMillis();
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        result = 31 * result + (getPrimaryGenreName() != null ? getPrimaryGenreName().hashCode() : 0);
        result = 31 * result + (isStreamable() ? 1 : 0);
        return result;
    }
}
