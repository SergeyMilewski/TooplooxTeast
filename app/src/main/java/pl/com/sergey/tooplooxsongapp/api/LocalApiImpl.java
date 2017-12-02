package pl.com.sergey.tooplooxsongapp.api;

import android.content.Context;

import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;

/**
 * Created by smilevkiy on 30.11.17.
 */

public class LocalApiImpl implements LocalApi {

    private final Context context;

    private List<LocalSong> localSongsCash;

    private boolean isLoadedToCash;

    private boolean isDisposed;

    public LocalApiImpl(Context context) {
        this.context = context;
    }

    @Override
    public Flowable<LocalSong> getLocalSongs() {
        if (localSongsCash != null && isLoadedToCash) {
            return Flowable.fromIterable(localSongsCash);
        }
        return Flowable.create(e -> {
            localSongsCash = new ArrayList<>();
            try {
                final JsonReader jsonReader = new JsonReader(new InputStreamReader(context.getResources().openRawResource(R.raw.local)));
                e.setDisposable(new Disposable() {
                    @Override
                    public void dispose() {
                        try {
                            isDisposed = true;
                            jsonReader.close();
                        } catch (IOException e1) {
                            //do nothing
                        }
                    }

                    @Override
                    public boolean isDisposed() {
                        return isDisposed;
                    }
                });
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    LocalSong localSong = parseSong(jsonReader);
                    localSongsCash.add(localSong);
                    e.onNext(localSong);
                }
                jsonReader.endArray();
                jsonReader.close();
                isLoadedToCash = true;
                e.onComplete();

            } catch (IOException | NullPointerException err) {
                e.onError(err);
            }
        }, BackpressureStrategy.BUFFER);

    }

    private LocalSong parseSong(JsonReader jsonReader) throws IOException {
        LocalSong.Builder localSong = new LocalSong.Builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if (name.equals("Song Clean")) {
                localSong.songClean(jsonReader.nextString());
            } else if (name.equals("ARTIST CLEAN")) {
                localSong.aRTISTCLEAN(jsonReader.nextString());
            } else if (name.equals("Release Year")) {
                localSong.releaseYear(jsonReader.nextString());
            } else if (name.equals("COMBINED")) {
                localSong.cOMBINED(jsonReader.nextString());
            } else if (name.equals("First?")) {
                localSong.first(jsonReader.nextString());
            } else if (name.equals("Year?")) {
                localSong.year(jsonReader.nextString());
            } else if (name.equals("PlayCount")) {
                localSong.playCount(jsonReader.nextString());
            } else if (name.equals("F*G")) {
                localSong.fG(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return localSong.build();
    }
}
