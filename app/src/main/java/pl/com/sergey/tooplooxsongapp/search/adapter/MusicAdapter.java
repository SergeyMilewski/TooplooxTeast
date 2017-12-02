package pl.com.sergey.tooplooxsongapp.search.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.com.sergey.tooplooxsongapp.R;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
import pl.com.sergey.tooplooxsongapp.glade.GlideApp;
import pl.com.sergey.tooplooxsongapp.search.presenter.SearchPresenter;

/**
 * Created by sergey on 29.11.17.
 */

public class MusicAdapter extends RecyclerView.Adapter {


    private final Context context;

    private List<SongDto> songs;

    private final SearchPresenter presenter;

    private final String local;

    public MusicAdapter(Context context, SearchPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        local = context.getResources().getStringArray(R.array.source)[0];
    }

    private interface SongClickListener {

        void onClickItem(SongDto songDto);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SongDto song = songs.get(position);
        SongViewHolder songViewHolder = (SongViewHolder) holder;
        songViewHolder.songName.setText(song.getSong());
        songViewHolder.artistName.setText(song.getNameSinger());
        songViewHolder.releaseDate.setText(song.getReleaseDate());
        String source = song.getSource();
        songViewHolder.sourceLabel.setTextColor(source.equals(local) ? ContextCompat.getColor(context, R.color.colorPrimary) : ContextCompat.getColor(context, R.color.colorAccent));
        songViewHolder.sourceLabel.setText(source);
        songViewHolder.songDto = song;
        songViewHolder.songClickListener = presenter::starDetailsActivity;
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

    static class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView songName, artistName, releaseDate, sourceLabel;
        ImageView imageView;
        SongDto songDto;
        SongClickListener songClickListener;

        SongViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            releaseDate = itemView.findViewById(R.id.release_date);
            sourceLabel = itemView.findViewById(R.id.source_label);
            imageView = itemView.findViewById(R.id.photo);
            itemView.findViewById(R.id.card_view).setOnClickListener(SongViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            if (songClickListener != null) {
                songClickListener.onClickItem(songDto);
            }
        }
    }
}
