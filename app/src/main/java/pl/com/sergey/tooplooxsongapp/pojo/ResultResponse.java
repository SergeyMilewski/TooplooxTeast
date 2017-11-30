package pl.com.sergey.tooplooxsongapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sergey on 29.11.17.
 */

public class ResultResponse {

    @SerializedName("resultCount")
    @Expose
    private int resultCount;
    @SerializedName("results")
    @Expose
    private List<ItunsSong> results;

    public int getResultCount() {
        return resultCount;
    }

    public List<ItunsSong> getResults() {
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultResponse that = (ResultResponse) o;

        if (getResultCount() != that.getResultCount()) return false;
        return getResults() != null ? getResults().equals(that.getResults()) : that.getResults() == null;
    }

    @Override
    public int hashCode() {
        int result = getResultCount();
        result = 31 * result + (getResults() != null ? getResults().hashCode() : 0);
        return result;
    }
}
