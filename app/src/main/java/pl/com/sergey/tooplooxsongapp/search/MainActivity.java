package pl.com.sergey.tooplooxsongapp.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import pl.com.sergey.tooplooxsongapp.MyApplication;
import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
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
    private TextView filterTextView;
    private AppCompatSpinner filterSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        filterSpinner = findViewById(R.id.filter_spinner);
        listEmpty = findViewById(R.id.empty_list);
        ArrayAdapter<String> filterAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.filter));
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);


        editText = findViewById(R.id.search_edit_text);
        RecyclerView recyclerView = findViewById(R.id.questions_list);
        filterTextView = findViewById(R.id.filter_txt);
        filterTextView.setOnClickListener(v -> showFilterDialog());
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
                .filter(charSequence -> charSequence.length() > 2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sequence -> presenter.searchSong(sequence));

    }


    @Override
    public void setData(List<SongDto> songDtos) {
        listEmpty.setVisibility(songDtos.isEmpty() ? View.VISIBLE : View.GONE);
        musicAdapter.setSongs(songDtos);
    }

    public void showFilterDialog() {

        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, getResources().getDisplayMetrics()), 0, 0);
        linearLayout.removeAllViews();
        for (int i = 0; i < 3 ; i++) {
            final AppCompatCheckBox checkBox = new AppCompatCheckBox(this);
            checkBox.setText(getResources().getStringArray(R.array.filter)[i]);
            checkBox.setTag(i);
            checkBox.setChecked(false);
            linearLayout.addView(checkBox, lp);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            });
        }
        new AlertDialog.Builder(this).setTitle(getString(R.string.choose_filter))
                .setCancelable(false)
                .setView(linearLayout)
                .setPositiveButton(getString(R.string.ok), (dialog, whichButton) -> {
                    linearLayout.removeAllViews();
                    dialog.dismiss();
                })
                .show();
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
