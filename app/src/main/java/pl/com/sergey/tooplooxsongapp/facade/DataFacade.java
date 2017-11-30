package pl.com.sergey.tooplooxsongapp.facade;

import io.reactivex.Observable;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;
import pl.com.sergey.tooplooxsongapp.pojo.ResultResponse;

/**
 * Created by smilevkiy on 30.11.17.
 */

public interface DataFacade {

    Observable<ResultResponse> getListSongs(CharSequence song);

    void setSource(SourceType source);

    LocalSong [] getLocalSongs();
}
