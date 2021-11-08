package com.example.ca2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class P2FloatingActionButton extends AppCompatActivity {
    FloatingActionButton b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2_floating_action_button);
        b1=findViewById(R.id.cam);
        setTitle("P2FloatingActionButton");



    }

    public void opencamera(View view) {
        final int CAMERA_PIC_REQUEST = 1337;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
    }


    public void opengalery(View view) {
        final int RESULT_GALLERY = 0;

        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent , RESULT_GALLERY );

    }

    public void Sms(View view) {
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String ("8929944156"));
        smsIntent.putExtra("sms_body"  , "");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(P2FloatingActionButton.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    public void addUser(View view) {

        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}