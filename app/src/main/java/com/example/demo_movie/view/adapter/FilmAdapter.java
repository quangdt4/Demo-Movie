package com.example.demo_movie.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo_movie.R;
import com.example.demo_movie.model.FilmResModel;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {
    private final List<FilmResModel.Result> listFilm;
    private final Context mContext;
    private OnItemClick mCallback;
    private FilmResModel.Result selectedFilm;

    public FilmAdapter(List<FilmResModel.Result> listFilm, Context mContext) {
        this.listFilm = listFilm;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_film, parent, false);
        return new FilmHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
        FilmResModel.Result item = listFilm.get(position);
        Glide.with(mContext).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + item.getPosterPath())
                //.useAnimationPool(true)
                .into(holder.ivPoster);
        holder.tvTitle.setText(item.getTitle());
        holder.tvTime.setText(item.getTime());
        holder.tvOverView.setText(item.getOverview());
        holder.pos = position;
        holder.film = item;
        holder.lnItemFilm.setBackgroundResource(
                item == selectedFilm && item.isSelected() ? R.color.grayDark : R.color.white);
    }

    public interface OnItemClick {
        void onItemClick(FilmResModel.Result film, int position);
    }

    public void setmCallback(OnItemClick mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    public class FilmHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle, tvTime, tvOverView;
        LinearLayout lnItemFilm;
        private FilmResModel.Result film;
        private int pos;

        public FilmHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvOverView = itemView.findViewById(R.id.tv_overview);
            lnItemFilm = itemView.findViewById(R.id.ln_item_film);
            itemView.setOnClickListener(v -> {
                if (selectedFilm != null) {
                    selectedFilm.setSelected(false);
                }
                film.setSelected(true);
                notifyDataSetChanged();
                selectedFilm = film;

                if (mCallback != null) {
                    mCallback.onItemClick(film, pos);
                }
            });
        }
    }
}
