package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NeighbourDetailActivity extends AppCompatActivity {
    private Neighbour mNeighbour;
    @BindView(R.id.detail_avatar)
    ImageView mAvatar;
    @BindView(R.id.detail_avatar_lable_name)
    TextView mAvatarLableName;
    @BindView(R.id.detail_info_text_name)
    TextView mInfoName;
    @BindView(R.id.detail_info_text_address)
    TextView mInfoAddress;
    @BindView(R.id.detail_info_text_url)
    TextView mInfoUrl;
    @BindView(R.id.detail_info_text_phone)
    TextView mInfoPhone;
    @BindView(R.id.detail_info_text_aboutMe)
    TextView mInfoAboutMe;
    /*@BindView(R.id.detail_button_favorite)
    Button mInfoFavorite;*/
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        long neighbourID = getIntent().getLongExtra("Neighbour",0);
        for (Neighbour n : mNeighbours) {//Loops to retrieve the correct Neighbour by ID
            if (n.getId()==neighbourID) {
                mNeighbour = n;
            }
        }
        checkFavoriteStatus();
        mAvatarLableName.setText(mNeighbour.getName());
        mInfoName.setText(mNeighbour.getName());
        mInfoAddress.setText(mNeighbour.getAddress());
        mInfoPhone.setText(mNeighbour.getPhoneNumber());
        mInfoAboutMe.setText(mNeighbour.getAboutMe());
        mInfoUrl.setText("www.facebook.fr/"+mNeighbour.getName());
        Glide.with(this)
                .load(mNeighbour.getAvatarUrl()) // image url
                .centerCrop()
                .into(mAvatar);


        //Toast.makeText(this,"Your are looking at "+mNeighbour.getName(), Toast.LENGTH_SHORT).show();


    }
    @OnClick(R.id.detail_button_favorite)
    void addFavorite() {
        mApiService.changeFavoriteStatus(mNeighbour);
        //mNeighbour.setIsFavorite(!mNeighbour.getIsFavorite());
        checkFavoriteStatus();

    }
    void checkFavoriteStatus(){
        FloatingActionButton fab = findViewById(R.id.detail_button_favorite);
        if(mNeighbour.getIsFavorite()){
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF80AB")));
        }
        else{
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAFAFA")));
        }
    }

}