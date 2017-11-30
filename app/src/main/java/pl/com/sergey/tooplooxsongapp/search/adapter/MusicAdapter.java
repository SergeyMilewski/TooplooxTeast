package pl.com.sergey.tooplooxsongapp.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
import pl.com.sergey.tooplooxsongapp.glade.GlideApp;

import static pl.com.sergey.tooplooxsongapp.util.Utils.getFormattedDate;

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
        return new SongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SongDto song = songs.get(position);
        SongViewHolder songViewHolder = (SongViewHolder)holder;
        songViewHolder.songName.setText(song.getSong());
        songViewHolder.artistName.setText(song.getNameSinger());
        songViewHolder.releaseDate.setText(getFormattedDate(song.getReleaseDate()));
        GlideApp.with(context)
                .load(song.getUrlImg())
                .placeholder(R.drawable.ic_music_note_black)
                .into(songViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return songs == null ? 0 : songs.size();
    }


    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
        notifyDataSetChanged();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songName;
        TextView artistName;
        TextView releaseDate;
        ImageView imageView;

        SongViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            releaseDate = itemView.findViewById(R.id.release_date);
            imageView = itemView.findViewById(R.id.photo);
        }
    }


}
