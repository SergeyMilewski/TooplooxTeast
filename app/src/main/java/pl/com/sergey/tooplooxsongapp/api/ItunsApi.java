package pl.com.sergey.tooplooxsongapp.api;

import io.reactivex.Observable;
import pl.com.sergey.tooplooxsongapp.pojo.ResultResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sergey on 29.11.17.
 */

public interface ItunsApi {


    @GET("search?")
    Observable<ResultResponse> searchSongs(@Query("term") String songs, @Query("media") String type, @Query("limit") String limit);


}
