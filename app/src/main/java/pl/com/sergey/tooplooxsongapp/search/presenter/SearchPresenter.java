package pl.com.sergey.tooplooxsongapp.search.presenter;

import android.support.annotation.VisibleForTesting;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.com.sergey.tooplooxsongapp.BasePresenter;
import pl.com.sergey.tooplooxsongapp.UI;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
import pl.com.sergey.tooplooxsongapp.facade.DataFacade;
import pl.com.sergey.tooplooxsongapp.facade.SourceType;
import pl.com.sergey.tooplooxsongapp.search.Filter;

/**
 * Created by sergey on 29.11.17.
 */

public class SearchPresenter extends BasePresenter<SearchPresenter.MainActivityUI> {


    private final DataFacade dataFacade;
    private Disposable dis;

    private Filter filter = Filter.NONE;
    private CharSequence lastSearch;
    private Scheduler mainScheduler;
    private Scheduler backGroundScheduler;


    @Inject
    public SearchPresenter(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
        mainScheduler = AndroidSchedulers.mainThread();
        backGroundScheduler = Schedulers.io();

    }

    @VisibleForTesting
    public SearchPresenter(DataFacade dataFacade, Scheduler main, Scheduler back) {
        this.dataFacade = dataFacade;
        mainScheduler = main;
        backGroundScheduler = back;
    }

    @Override
    public void attacheUI(MainActivityUI ui) {
        super.attacheUI(ui);
    }

    @Override
    public void detachUI() {
        super.detachUI();
        if (dis != null) {
            dis.dispose();
        }
    }

    @VisibleForTesting
    public void setLastSearch(CharSequence charSequence) {
        this.lastSearch = charSequence;
    }

    public void setSource(SourceType source) {
        dataFacade.setSource(source);
        if (lastSearch != null) {
            searchSong(lastSearch);
        }
    }

    public void setSortedBy(int position) {
        filter = Filter.values()[position];
        if (lastSearch != null) {
            searchSong(lastSearch);
        }
    }

    public void starDetailsActivity(SongDto songDto) {
        if (ui != null) {
            ui.showDetails(songDto);
        }
    }

    public void clickSource() {
        ui.showSourceDialog();

    }

    public void searchSong(final CharSequence charSequence) {
        lastSearch = charSequence;
        dis = dataFacade.getListSongs(charSequence)
                .filter(songDto -> filteredBy(songDto, charSequence))
                .toSortedList((o1, o2) -> o1.getSong().compareTo(o2.getSong()))
                .subscribeOn(backGroundScheduler)
                .observeOn(mainScheduler)
                .subscribe(data -> {
                    if (ui != null) {
                        ui.setData(data);
                    }
                }, this::handleError);
    }

    private void handleError(Throwable throwable) {
        if (ui != null) {
            if (throwable instanceof UnknownHostException) {
                ui.showErrorService();
                return;
            }
            ui.showCommonError();
        }
    }

    private boolean filteredBy(SongDto songDto, CharSequence charSequence) {
        switch (filter) {
            case NAME:
                return songDto.getSong().toLowerCase().contains(charSequence.toString().toLowerCase());
            case ARTIST:
                return songDto.getNameSinger().toLowerCase().contains(charSequence.toString().toLowerCase());
            case RELEASE:
                return songDto.getReleaseDate().toLowerCase().contains(charSequence.toString().toLowerCase());
            case NONE:
            default:
                return true;
        }
    }

    public interface MainActivityUI extends UI {
        void setData(List<SongDto> songDtos);

        void showDetails(SongDto songDto);

        void showSourceDialog();

        void showErrorService();

        void showCommonError();
    }
}
