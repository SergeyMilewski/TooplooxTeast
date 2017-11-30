package pl.com.sergey.tooplooxsongapp.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.com.sergey.tooplooxsongapp.dto.SongDto;

/**
 * Created by sergey on 29.11.17.
 */

public class MusicAdapter extends RecyclerView.Adapter {


    private final Context context;

    private List<SongDto> songs;

    public MusicAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return songs == null ? 0 : songs.size();
    }


    public void setSongs(List<SongDto> songs){
        this.songs = songs;
        notifyDataSetChanged();
    }

    public static class SongViewHoldet extends RecyclerView.ViewHolder {
        public SongViewHoldet(View itemView) {
            super(itemView);
        }
    }


}
