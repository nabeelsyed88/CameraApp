package com.example.nabeel.cameraapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;
    VideoView videoView;
    boolean videoTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.vv_main);

        videoTaken = false;
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(takeVideoIntent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            videoView.setVideoURI(videoUri);
            videoTaken = true;
        }
    }

    public void takeVideo(View view) {
        dispatchTakeVideoIntent();
    }

    public void pauseVideo(View view) {
        if(videoTaken) {
            if(videoView.isPlaying())
                videoView.pause();
        } else {
            Toast.makeText(this, "Video not taken yet", Toast.LENGTH_SHORT).show();
        }
    }

    public void playVideo(View view) {
        if(videoTaken) {
            if(!videoView.isPlaying())
                videoView.start();
        } else {
            Toast.makeText(this, "Video not taken yet", Toast.LENGTH_SHORT).show();
        }
    }
}
