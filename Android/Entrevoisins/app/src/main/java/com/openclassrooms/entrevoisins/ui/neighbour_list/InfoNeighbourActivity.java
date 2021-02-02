package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class InfoNeighbourActivity extends AppCompatActivity {

    //Find textView and Image
    private TextView name;
    private TextView phone;
    private TextView auboutMe;
    private TextView adresse;
    private TextView nameTitre;
    private TextView facebook;
    private ImageView imageAvatar;
    private ImageButton btnBack;
    private FloatingActionButton btnFavorie;
    private Boolean isFavorite;
    private NeighbourApiService mFavApiService;
    private Neighbour neighbour;
    private String imageUrl;
    private static final int NOTIF_ID = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        getSupportActionBar().hide();
        getIncomingIntent();
        fabOnclickListner();
        mFavApiService = DI.getNeighbourApiService();
    }

    private void getIncomingIntent(){
        // Research if we have element in Intent
        if (getIntent().hasExtra("neighbour_AvatarUrl") && getIntent().hasExtra("neighbour_Name") &&  getIntent().hasExtra("neighbour_AboutMe") && getIntent().hasExtra("neighbour_PhoneNumber") && getIntent().hasExtra("neighbour_Adresse")){
            Long id = getIntent().getLongExtra("neighbour_Id",System.currentTimeMillis());
            String neighbourName = getIntent().getStringExtra("neighbour_Name");
            imageUrl = getIntent().getStringExtra("neighbour_AvatarUrl");
            String neighbourAdresse = getIntent().getStringExtra("neighbour_Adresse");
            String neighnourPhone = getIntent().getStringExtra("neighbour_PhoneNumber");
            String neighbourAboutMe = getIntent().getStringExtra("neighbour_AboutMe");
            isFavorite = getIntent().getBooleanExtra("neighbour_isFavorite",true);

            neighbour = new Neighbour(id,neighbourName,imageUrl,neighbourAdresse,neighnourPhone,neighbourAboutMe,isFavorite);

            name = findViewById(R.id.textViewNameNeighbourg);
            nameTitre = findViewById(R.id.textViewName);
            phone = findViewById(R.id.textViewphoneNeighbour);
            auboutMe = findViewById(R.id.textAboutMe);
            adresse = findViewById(R.id.textAdresseNeighbour);
            facebook = findViewById(R.id.textViewFaceBook);

            // Set Texte and Image
            auboutMe.setText(neighbourAboutMe);
            name.setText(neighbourName);
            nameTitre.setText(neighbourName);
            phone.setText(neighnourPhone);
            adresse.setText(neighbourAdresse);
            facebook.setText("www.Facebook.com/" + name.getText());

            imageAvatar = findViewById(R.id.neighbour_Avatar);

            Glide.with(this)
                    .asBitmap()
                    .load(imageUrl)
                    .into(imageAvatar);



            btnFavorie = findViewById(R.id.floatingButtonFavorie);
            if (isFavorite) {
                btnFavorie.setImageResource(R.drawable.ic_baseline_star_yellow_24);
                btnFavorie.hide();
                btnFavorie.show();
            } else {
                btnFavorie.setImageResource(R.drawable.ic_baseline_star_border_yellow_24);
                btnFavorie.hide();
                btnFavorie.show();
            }
            btnBack = findViewById(R.id.buttonBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { finish(); }
            });
        }
    }

    private void fabOnclickListner(){
        btnFavorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFavorite){
                    btnFavorie.setImageResource(R.drawable.ic_baseline_star_yellow_24);
                    btnFavorie.hide();
                    btnFavorie.show();
                    addFavoriteNeighbour(v);
                }else{
                    btnFavorie.setImageResource(R.drawable.ic_baseline_star_border_yellow_24);
                    btnFavorie.hide();
                    btnFavorie.show();
                    deleteFavoriteNeighbour(v);
                }
            }
        });
    }

    private void addFavoriteNeighbour(View view) {

        Context context = InfoNeighbourActivity.this;

        mFavApiService.addFavoriteNeighbour(neighbour);
        isFavorite = true;
        Snackbar.make(view, "Vous venez d'ajouter " + name.getText() + " à vos voisins favoris !", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Ajout Favorie")
                .setContentText( name.getText() + " fait maintenant partie de vos favoris" )
                .build();

        NotificationManager notifManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify( NOTIF_ID, notification );
    }

    private void deleteFavoriteNeighbour(View view) {
        Context context = InfoNeighbourActivity.this;

        Snackbar.make(view, "Vous venez de retirer " + name.getText() + " de vos voisins favoris !", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        isFavorite = false;
        mFavApiService.deleteFavoriteNeighbour(neighbour);

        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Suppression Favorie")
                .setContentText( name.getText() + " ne fait plus partie de vos favoris" )
                .build();

        NotificationManager notifManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify( NOTIF_ID, notification );
    }


}