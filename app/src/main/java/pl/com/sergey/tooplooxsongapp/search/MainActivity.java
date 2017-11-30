package pl.com.sergey.tooplooxsongapp.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import pl.com.sergey.tooplooxsongapp.MyApplication;
import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.search.adapter.MusicAdapter;

/**
 * Created by smilevkiy on 29.11.17.
 */

public class MainActivity extends AppCompatActivity implements MainPresenter.MainActivityUI {
    @Inject
    MainPresenter presenter;
    private EditText editText;
    private Disposable disposable;
    private MusicAdapter musicAdapter;
    private TextView listEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        editText = findViewById(R.id.search_edit_text);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.questions_list);
        musicAdapter = new MusicAdapter(this);
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attacheUI(this);
        disposable = RxTextView.textChanges(editText)
                .debounce(1L, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.length() > 3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sequence -> presenter.searchSong(sequence));

    }

    @Override
    protected void onPause() {
        presenter.detachUI();
        super.onPause();
        if (disposable != null) {
            disposable.dispose();
        }

    }

}
