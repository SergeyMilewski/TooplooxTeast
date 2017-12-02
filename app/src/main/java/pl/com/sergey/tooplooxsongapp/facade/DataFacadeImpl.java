package pl.com.sergey.tooplooxsongapp.facade;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.api.ItunsApi;
import pl.com.sergey.tooplooxsongapp.api.LocalApi;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;

/**
 * Created by smilevkiy on 30.11.17.
 */

public class DataFacadeImpl implements DataFacade {

    private SourceType sourceType = SourceType.BOTH;

    private final ItunsApi itunsApi;

    private final LocalApi localApi;

    private final Context context;

    private List<SongDto> remoteSongsCashed;

    private String latestSearch;

    private boolean isCashCompleted;

    @Inject
    public DataFacadeImpl(ItunsApi itunsApi, LocalApi localApi, Context context) {
        this.itunsApi = itunsApi;
        this.localApi = localApi;
        this.context = context;
    }

    @Override
    public Flowable<SongDto> getListSongs(CharSequence song) {
        switch (sourceType) {
            case BOTH:
                return Flowable.merge(getRemoteSongs(song), getLocalSongs());
            case REMOTE:
                return getRemoteSongs(song);
            case LOCAL:
            default:
                return getLocalSongs();
        }
    }

    private Flowable<SongDto> getRemoteSongs(CharSequence search) {
        String searchSongs = search.toString();
        if (remoteSongsCashed == null) {
            remoteSongsCashed = new ArrayList<>(200);
        }
        if (searchSongs.equals(latestSearch) && !remoteSongsCashed.isEmpty() && isCashCompleted) {
            return Flowable.fromIterable(remoteSongsCashed);
        }
        latestSearch = searchSongs;
        remoteSongsCashed.clear();
        isCashCompleted = false;
        return itunsApi.searchSongs(searchSongs.replaceAll("\\s+", "+"), "music", "200")
                .flatMap(result -> Observable.fromIterable(result.getResults()))
                .map(s -> new SongDto(s, context.getResources().getStringArray(R.array.source)[1]))
                .doOnNext(songsToCash -> remoteSongsCashed.add(songsToCash))
                .doOnComplete(() -> isCashCompleted = true)
                .toFlowable(BackpressureStrategy.BUFFER);
    }


    private Flowable<SongDto> getLocalSongs() {
        return localApi.getLocalSongs().map(s -> new SongDto(s, context.getResources().getStringArray(R.array.source)[0]));
    }

    @Override
    public void setSource(SourceType source) {
        this.sourceType = source;
    }
}
