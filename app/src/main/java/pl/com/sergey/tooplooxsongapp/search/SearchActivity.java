package pl.com.sergey.tooplooxsongapp.search;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import pl.com.sergey.tooplooxsongapp.MyApplication;
import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.details.DetailsActivity;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
import pl.com.sergey.tooplooxsongapp.facade.SourceType;
import pl.com.sergey.tooplooxsongapp.search.adapter.MusicAdapter;
import pl.com.sergey.tooplooxsongapp.search.presenter.SearchPresenter;

/**
 * Created by smilevkiy on 29.11.17.
 */

public class SearchActivity extends AppCompatActivity implements SearchPresenter.MainActivityUI {

    @Inject
    SearchPresenter presenter;

    @Inject
    SharedPreferences sharedPreferences;

    private AppCompatEditText editText;
    private Disposable disposable;
    private MusicAdapter musicAdapter;
    private TextView listEmpty;
    private TextView sourceTextView;
    private Set<String> sourceSet;
    private static final String SAVED_FILTER = "saved_filter";
    private static final String SAVED_SOURCE = "saved_source";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        AppCompatSpinner filterSpinner = findViewById(R.id.filter_spinner);
        listEmpty = findViewById(R.id.empty_list);
        progressBar = findViewById(R.id.progress);
        ArrayAdapter<String> filterAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.filter));
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);
        filterSpinner.setSelection(sharedPreferences.getInt(SAVED_FILTER, 0));
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setSortedBy(position);
                sharedPreferences.edit().putInt(SAVED_FILTER, position).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editText = findViewById(R.id.search_edit_text);
        RecyclerView recyclerView = findViewById(R.id.questions_list);
        sourceTextView = findViewById(R.id.filter_txt);
        sourceTextView.setOnClickListener(v -> presenter.clickSource());
        musicAdapter = new MusicAdapter(this, presenter);
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sourceSet = sharedPreferences.getStringSet(SAVED_SOURCE, new HashSet<>());
        if (sourceSet.isEmpty()) {
            sourceSet.add(getResources().getStringArray(R.array.source)[0]);
        }
        setSourceToView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attacheUI(this);
        disposable = RxTextView.textChanges(editText)
                .debounce(1L, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.length() > 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sequence -> {
                    presenter.searchSong(sequence);
                    setProgress(true);
                });

    }

    @Override
    protected void onPause() {
        presenter.detachUI();
        super.onPause();
        if (disposable != null) {
            disposable.dispose();
        }

    }

    private void setProgress(boolean isShouldBe) {
        progressBar.setVisibility(isShouldBe ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setData(List<SongDto> songDtos) {
        setProgress(false);
        listEmpty.setVisibility(songDtos.isEmpty() ? View.VISIBLE : View.GONE);
        musicAdapter.setSongs(songDtos);
    }

    @Override
    public void showDetails(SongDto songDto) {
        DetailsActivity.startDetailsActivity(this, songDto);
    }

    @Override
    public void showErrorService() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.error_alert_title))
                .setMessage(R.string.error_alert_message)
                .setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    setLocalSearch();
                    presenter.searchSong(editText.getText().subSequence(0, editText.getText().toString().length()));
                }).show();
    }

    private void setLocalSearch() {
        sourceSet.clear();
        sourceSet.add(getResources().getStringArray(R.array.source)[0]);
        sharedPreferences.edit().remove(SAVED_SOURCE).apply();
        sharedPreferences.edit().putStringSet(SAVED_SOURCE, sourceSet).apply();
        setSourceToView();
    }

    @Override
    public void showCommonError() {
        Toast.makeText(this, getString(R.string.common_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSourceDialog() {
        final ViewGroup dialogView = getViewForDialog();
        new AlertDialog.Builder(this).setTitle(getString(R.string.choose_source))
                .setCancelable(false)
                .setView(dialogView)
                .setPositiveButton(getString(R.string.ok), (dialog, whichButton) -> {
                    sharedPreferences.edit().remove(SAVED_SOURCE).apply();
                    sharedPreferences.edit().putStringSet(SAVED_SOURCE, sourceSet).apply();
                    setSourceToView();
                    dialogView.removeAllViews();
                    dialog.dismiss();
                })
                .show();
    }


    private ViewGroup getViewForDialog() {
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, getResources().getDisplayMetrics()), 0, 0);
        for (int i = 0; i < 2; i++) {
            final AppCompatCheckBox checkBox = new AppCompatCheckBox(this);
            checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            final String source = getResources().getStringArray(R.array.source)[i];
            checkBox.setText(source);
            checkBox.setTag(i);
            checkBox.setChecked(sourceSet.contains(source));
            linearLayout.addView(checkBox, lp);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    sourceSet.add(getResources().getStringArray(R.array.source)[(int) buttonView.getTag()]);
                } else {
                    sourceSet.remove(getResources().getStringArray(R.array.source)[(int) buttonView.getTag()]);
                    if (sourceSet.isEmpty()) {
                        AppCompatCheckBox compatCheckBox = (AppCompatCheckBox) linearLayout.getChildAt((int) buttonView.getTag() == 0 ? 1 : 0);
                        compatCheckBox.setChecked(true);
                    }
                }

            });
        }
        return linearLayout;
    }

    private void setSourceToView() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String src : sourceSet) {
            stringBuilder.append(src);
            stringBuilder.append(" ");
        }
        sourceTextView.setText(stringBuilder.toString());
        setSource();
    }

    private void setSource() {
        if (sourceSet.size() > 1) {
            presenter.setSource(SourceType.BOTH);
            return;
        }
        if (sourceSet.contains(getResources().getStringArray(R.array.source)[0])) {
            presenter.setSource(SourceType.LOCAL);
            return;
        }
        presenter.setSource(SourceType.REMOTE);
    }

}
