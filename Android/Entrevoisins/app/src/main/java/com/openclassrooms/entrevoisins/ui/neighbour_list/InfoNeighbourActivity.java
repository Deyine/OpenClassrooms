package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.OnClick;

public class InfoNeighbourActivity extends AppCompatActivity {

    //Find textView and Image

    private DummyNeighbourApiService mApiService;

    private TextView name;
    private TextView phone;
    private TextView auboutMe;
    private TextView adresse;
    private TextView nameTitre;
    private ImageView imageAvatar;
    private ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        getSupportActionBar().hide();
        getIncomingIntent();
        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getIncomingIntent(){
        // Research if we have element in Intent
        if (getIntent().hasExtra("neighbour_AvatarUrl") && getIntent().hasExtra("neighbour_Name") &&  getIntent().hasExtra("neighbour_AboutMe") && getIntent().hasExtra("neighbour_PhoneNumber") && getIntent().hasExtra("neighbour_Adresse")){
            String imageUrl = getIntent().getStringExtra("neighbour_AvatarUrl");
            String neighbourName = getIntent().getStringExtra("neighbour_Name");
            String neighbourAboutMe = getIntent().getStringExtra("neighbour_AboutMe");
            String neighnourPhone = getIntent().getStringExtra("neighbour_PhoneNumber");
            String neighbourAdresse = getIntent().getStringExtra("neighbour_Adresse");


             name = findViewById(R.id.nameNeighbourg);
             nameTitre = findViewById(R.id.textViewName);
             phone = findViewById(R.id.phoneNeighbour);
             auboutMe = findViewById(R.id.textAboutMe);
             adresse = findViewById(R.id.TextAdresseNeighbour);

            // Set Texte and Image
            auboutMe.setText(neighbourAboutMe);
            name.setText(neighbourName);
            nameTitre.setText(neighbourName);
            phone.setText(neighnourPhone);
            adresse.setText(neighbourAdresse);

            imageAvatar = findViewById(R.id.neighbour_Avatar);

            Glide.with(this)
                    .asBitmap()
                    .load(imageUrl)
                    .into(imageAvatar);
        }
    }



}