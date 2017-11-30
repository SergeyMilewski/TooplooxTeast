package pl.com.sergey.tooplooxsongapp.facade;

import javax.inject.Inject;

import io.reactivex.Observable;
import pl.com.sergey.tooplooxsongapp.api.ItunsApi;
import pl.com.sergey.tooplooxsongapp.api.LocalApi;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;
import pl.com.sergey.tooplooxsongapp.pojo.ResultResponse;

/**
 * Created by smilevkiy on 30.11.17.
 */

public class DataFacadeImpl implements DataFacade {

    private SourceType sourceType = SourceType.LOCAL;

    private final ItunsApi itunsApi;

    private final LocalApi localApi;

    private ResultResponse resultResponse;

    @Inject
    public DataFacadeImpl(ItunsApi itunsApi, LocalApi localApi) {
        this.itunsApi = itunsApi;
        this.localApi = localApi;
    }

    @Override
    public Observable<ResultResponse> getListSongs(CharSequence song) {
        Observable<ResultResponse> observable;
//        switch (sourceType) {
//            case LOCAL:
//                break;
//            case REMOTE:
        observable = itunsApi.searchSongs(song.toString().replaceAll("\\s+", "+"), "music", "100")
                .doOnNext(resp -> resultResponse = resp);
//                break;
//            case BOTH:
//            default:
//                break;

//        }
        return observable;
    }


    @Override
    public LocalSong[] getLocalSongs() {
        return localApi.getLocalSongs();
    }

    @Override
    public void setSource(SourceType source) {
        this.sourceType = source;
    }
}
