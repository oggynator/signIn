package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.authentication.Repository.AuthRepo;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class RandomImage extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image);

        imageView = findViewById(R.id.imageView);

    }

    //just a small random image generator
    public void onRandomImagePressed(View view) {
        int[] images = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};
        Random rand = new Random();
        imageView.setImageResource(images[rand.nextInt(images.length)]);
    }


   //Signs out and removes the token
    public void onSignOutPressed(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "User signed out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
