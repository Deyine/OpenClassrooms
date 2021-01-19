package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;

public class InfoNeighbourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getIncomingIntent();
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

            //Find textView and Image
            TextView name = findViewById(R.id.nameNeighbourg);
            TextView phone = findViewById(R.id.phoneNeighbour);
            TextView auboutMe = findViewById(R.id.textAboutMe);
            TextView adresse = findViewById(R.id.TextAdresseNeighbour);

            // Set Texte and Image
            auboutMe.setText(neighbourAboutMe);
            name.setText(neighbourName);
            phone.setText(neighnourPhone);
            adresse.setText(neighbourAdresse);

            ImageView imageView = findViewById(R.id.neighbour_Avatar);

            Glide.with(this)
                    .asBitmap()
                    .load(imageUrl)
                    .into(imageView);
        }
    }


}