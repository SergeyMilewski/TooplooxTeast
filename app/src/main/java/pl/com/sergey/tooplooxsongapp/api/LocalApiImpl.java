package pl.com.sergey.tooplooxsongapp.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

import io.reactivex.Observable;
import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;

/**
 * Created by smilevkiy on 30.11.17.
 */

public class LocalApiImpl implements LocalApi {

    private final Context context;


    public LocalApiImpl(Context context) {
        this.context = context;

    }

    @Override
    public Observable<List<LocalSong>> searchSongsLocally(CharSequence charSequence) {
        return null;


    }



    @Override
    public LocalSong[] getLocalSongs(){
        Scanner sc = null;
        InputStream is = context.getResources().openRawResource(R.raw.local);
        Writer writer = new StringWriter();
        try {
            sc = new Scanner(is, "UTF-8");
            while (sc.hasNextLine()) {
               writer.append(sc.nextLine());

            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (is != null) {

                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return constructUsingGson(writer.toString());

    }
    public LocalSong[] constructUsingGson(String jsonString) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, LocalSong[].class);
    }

}
