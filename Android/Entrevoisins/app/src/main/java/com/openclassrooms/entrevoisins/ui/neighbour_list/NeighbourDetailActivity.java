package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);
        mNeighbour = (Neighbour) getIntent().getParcelableExtra("Neighbour");
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
}