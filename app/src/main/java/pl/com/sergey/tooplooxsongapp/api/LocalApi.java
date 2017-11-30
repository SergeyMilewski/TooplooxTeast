package pl.com.sergey.tooplooxsongapp.api;

import java.util.List;

import io.reactivex.Observable;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;

/**
 * Created by smilevkiy on 30.11.17.
 */

public interface LocalApi {

    Observable<List<LocalSong>> searchSongsLocally(CharSequence charSequence);

    LocalSong[] getLocalSongs();

}
