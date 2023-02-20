package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

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
    private NeighbourApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        mApiService = DI.getNeighbourApiService();

        mNeighbour = getIntent().getParcelableExtra("Neighbour");//retrieve Neighbour
        //fill in neighbour detail
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

        checkFavoriteStatus();//checks isFavorite status in order to select button image
    }
    @OnClick(R.id.detail_button_favorite)
    void changeFavorite() {//inverts the boolean favorite attribute of current neighbour
        mNeighbour.setIsFavorite(!mNeighbour.getIsFavorite());
        mApiService.changeFavoriteStatus(mNeighbour);
        checkFavoriteStatus();

    }
    void checkFavoriteStatus(){//used to update the favorite button image from empty star to filled star
        FloatingActionButton fab = findViewById(R.id.detail_button_favorite);
        if(mNeighbour.getIsFavorite()){
            fab.setImageResource(R.drawable.ic_star_yellow_24dp);
        }
        else{
            fab.setImageResource(R.drawable.ic_star_border_yellow_24dp);
        }
    }
    @OnClick(R.id.detail_back_arrow)
    void backHome() {
        finish();
    }

}