package pl.com.sergey.tooplooxsongapp.search;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.com.sergey.tooplooxsongapp.BasePresenter;
import pl.com.sergey.tooplooxsongapp.UI;
import pl.com.sergey.tooplooxsongapp.api.ItunsApi;

/**
 * Created by sergey on 29.11.17.
 */

public class MainPresenter extends BasePresenter<MainPresenter.MainActivityUI> {


    private final ItunsApi itunsApi;

    @Inject
    public MainPresenter(ItunsApi itunsApi) {
        this.itunsApi = itunsApi;
    }

    @Override
    public void attacheUI(MainActivityUI ui) {
        super.attacheUI(ui);
    }

    @Override
    public void detachUI() {
        super.detachUI();
    }


    public void searchSong(CharSequence charSequence){
        itunsApi.searchBySongs(charSequence.toString(), "music","5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(result -> Observable.fromIterable(result.getResults()))
                .map()
                .toList()
                .subscribe(s -> Log.d("Sergey", "size " + s.getResultCount()));
    }

    public interface MainActivityUI extends UI {
    }
}
