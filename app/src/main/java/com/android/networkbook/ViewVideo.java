package com.android.networkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class ViewVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);
        VideoView videoView = findViewById(R.id.video_view);
//        String videoPath = "file:///android_asset/books/"+getIntent().getStringExtra("video");

        String path = "android.resource://" + getPackageName() + "/" + getResources().getIdentifier(getIntent().getStringExtra("video").split("\\.")[0],
                "raw", getPackageName());;
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();

    }
}