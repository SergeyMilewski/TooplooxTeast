package pl.com.sergey.tooplooxsongapp.facade;

import io.reactivex.Flowable;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;

/**
 * Created by smilevkiy on 30.11.17.
 */

public interface DataFacade {

    Flowable<SongDto> getListSongs(CharSequence song);

    void setSource(SourceType source);
}
