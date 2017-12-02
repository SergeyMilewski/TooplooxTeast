package pl.com.sergey.tooplooxsongapp.api;

import io.reactivex.Flowable;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;

/**
 * Created by smilevkiy on 30.11.17.
 */

public interface LocalApi {

    Flowable<LocalSong> getLocalSongs();

}
