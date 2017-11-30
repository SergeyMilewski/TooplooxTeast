package pl.com.sergey.tooplooxsongapp.search;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.com.sergey.tooplooxsongapp.BasePresenter;
import pl.com.sergey.tooplooxsongapp.UI;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
import pl.com.sergey.tooplooxsongapp.facade.DataFacade;

/**
 * Created by sergey on 29.11.17.
 */

public class MainPresenter extends BasePresenter<MainPresenter.MainActivityUI> {


    private final DataFacade dataFacade;
    private Disposable dis;

    @Inject
    public MainPresenter(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
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


    public void searchSong(CharSequence charSequence) {
     /*   dis = dataFacade.getListSongs(charSequence)
                .flatMap(result -> Observable.fromIterable(result.getResults()))
                .map(SongDto::new)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(songDtos -> {
                    if (ui != null) {
                        ui.setData(songDtos);
                    }
                });*/
    Log.d("Sergey","size " + dataFacade.getLocalSongs().length);

//        itunsApi.searchSongs(charSequence.toString().replaceAll("\\s+", "+"), "music", "100")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(result -> Observable.fromIterable(result.getResults()))
//                .map(SongDto::new)
//                .toList()
//                .subscribe(songDtos -> {
//                    if (ui != null) {
//                        ui.setData(songDtos);
//                    }
//                });
    }

    public interface MainActivityUI extends UI {
        void setData(List<SongDto> songDtos);
    }
}
