package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.openclassrooms.entrevoisins.R;

public class InfoNeighbourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        if (getIntent().hasExtra("neighbour_AvatarUrl") && getIntent().hasExtra("neighbour_Name") &&  getIntent().hasExtra("neighbour_AboutMe") && getIntent().hasExtra("neighbour_PhoneNumber")){
            String imageUrl = getIntent().getStringExtra("neighbour_AvatarUrl");
            String neighbourName = getIntent().getStringExtra("neighbour_Name");
            String neighbourAboutMe = getIntent().getStringExtra("neighbour_AboutMe");
            String neighnourPhone = getIntent().getStringExtra("neighbour_PhoneNumber");
        }
    }
}