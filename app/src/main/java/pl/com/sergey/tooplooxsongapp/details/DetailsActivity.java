package pl.com.sergey.tooplooxsongapp.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
import pl.com.sergey.tooplooxsongapp.glade.GlideApp;

/**
 * Created by sergey on 01.12.17.
 */

public class DetailsActivity extends AppCompatActivity {
    private static final String SONGDTO = "song_dto";

    public static void startDetailsActivity(Context context, SongDto songDto) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(SONGDTO, songDto);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SongDto songDto = getIntent().getParcelableExtra(SONGDTO);
        GlideApp.with(this)
                .load(songDto.getUrlImg())
                .transform(new CircleCrop())
                .placeholder(R.drawable.ic_music_note_black)
                .into((ImageView) findViewById(R.id.image));
        ((TextView) findViewById(R.id.singer)).setText(songDto.getNameSinger());
        ((TextView) findViewById(R.id.song)).setText(songDto.getSong());
        ((TextView) findViewById(R.id.release)).setText(songDto.getReleaseDate());

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}
