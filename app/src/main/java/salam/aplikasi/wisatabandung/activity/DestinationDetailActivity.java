package salam.aplikasi.wisatabandung.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import mehdi.sakout.fancybuttons.FancyButton;
import salam.aplikasi.wisatabandung.R;

public class DestinationDetailActivity extends AppCompatActivity {

    String googleMap = "com.google.android.apps.maps"; //
    Uri gmmIntentUri;
    Intent mapIntent;
    String dummyLocation = "-6.225857,106.806542";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_detail);

        ImageView image = findViewById(R.id.imageDetail);
        TextView name = findViewById(R.id.txtName);
        TextView location = findViewById(R.id.txtLocation);
        TextView description = findViewById(R.id.txtDescription);
        FancyButton btnDirection = findViewById(R.id.btnDirection);


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String nameDestination = bundle.getString("name");
        final String locationDestination = bundle.getString("location");
        final String descriptionDestination = bundle.getString("description");
        final String imageDestination = bundle.getString("image");
        final String latDestination = bundle.getString("lat");
        final String longDestination = bundle.getString("long");

        name.setText(nameDestination);
        location.setText(locationDestination);
        description.setText(descriptionDestination);

        Glide.with(getApplicationContext())
                .load(imageDestination)
                .fitCenter()
                .into(image);

        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat Uri dari intent string. Gunakan hasilnya untuk membuat Intent.
                gmmIntentUri = Uri.parse("google.navigation:q=" + latDestination + ", " + longDestination);

                // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
                mapIntent.setPackage(googleMap);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(DestinationDetailActivity.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}