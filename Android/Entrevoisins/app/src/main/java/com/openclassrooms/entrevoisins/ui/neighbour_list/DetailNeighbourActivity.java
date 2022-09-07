package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNeighbourActivity extends AppCompatActivity {


    public static Neighbour neighbour;

    @BindView(R.id.avatarDetail)
    ImageView avatarView;
    @BindView(R.id.nameTitreDetail)
    TextView nameTitreView;
    @BindView(R.id.nameDetail)
    TextView nameView;
    @BindView(R.id.phoneNumberDetail)
    TextView phoneInputView;
    @BindView(R.id.addressDetail)
    TextView addressView;
    @BindView(R.id.aboutMeDetail)
    TextView aboutMeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(avatarView);
        nameTitreView.setText(neighbour.getName());
        nameView.setText(neighbour.getName());
        phoneInputView.setText(neighbour.getPhoneNumber());
        addressView.setText(neighbour.getAddress());
        aboutMeView.setText(neighbour.getAboutMe());
    }

    public static void navigate(FragmentActivity activity, Neighbour neighbourClick) {
        neighbour = neighbourClick;
        Intent intent = new Intent(activity, DetailNeighbourActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    @OnClick(R.id.backButtonDetail)
    void backToListNeighbourActivity() {
        finish();
    }
}
