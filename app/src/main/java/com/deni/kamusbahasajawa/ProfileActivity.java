package com.deni.kamusbahasajawa;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {

    ImageButton ibProfileFb, ibProfileYt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        ibProfileFb = findViewById(R.id.ib_profile_fb);
        ibProfileYt = findViewById(R.id.ib_profile_yt);

        ibProfileFb.setOnClickListener(goToFb);
        ibProfileYt.setOnClickListener(goToYt);

    }

    View.OnClickListener goToFb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent goToFbIntent = new Intent(Intent.ACTION_VIEW);
            goToFbIntent.setData(Uri.parse("https://www.facebook.com/deni.ace.5"));
            startActivity(goToFbIntent);
        }
    };

    View.OnClickListener goToYt = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent goToFbIntent = new Intent(Intent.ACTION_VIEW);
            goToFbIntent.setData(Uri.parse("https://www.youtube.com/c/deniace"));
            startActivity(goToFbIntent);
        }
    };
}
