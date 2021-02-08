package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourFavorisEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoritesNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoritesNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private NeighbourApiService mFavApiService;

    public MyFavoritesNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mFavApiService = DI.getNeighbourApiService();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        // When we click on deletebutton we send information for remove neighbourg only in list of favorie
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourFavorisEvent(neighbour));
                notifyDataSetChanged();
            }
        });

        // When we click on a personne we send all data for InfoActiviy
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),InfoNeighbourActivity.class);
                intent.putExtra("neighbour_Id",neighbour.getId());
                intent.putExtra("neighbour_Name",neighbour.getName());
                intent.putExtra("neighbour_AboutMe",neighbour.getAboutMe());
                intent.putExtra("neighbour_AvatarUrl",neighbour.getAvatarUrl());
                intent.putExtra("neighbour_PhoneNumber",neighbour.getPhoneNumber());
                intent.putExtra("neighbour_Adresse",neighbour.getAddress());
                intent.putExtra("neighbour_isFavorite",neighbour.isFavorite());
                ActivityCompat.startActivity(view.getContext(), intent,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
