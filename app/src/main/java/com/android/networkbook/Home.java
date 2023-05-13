package com.android.networkbook;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.networkbook.databinding.FragmentHomeBinding;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    private Context context;
    private FragmentHomeBinding binding;

    public Home() {
    }

    public static Home newInstance() {
        Home fragment = new Home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater,container,false);
        context=getContext();
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        setData();
        setVideos();
        return binding.getRoot();
    }

    private void setVideos() {
        AssetManager assetManager = context.getAssets();
        try {
            ArrayList<String> videos = new ArrayList<>();
            String[] files = assetManager.list("books/");
            for (String file : files) {
                if(file.endsWith(".mp4")){
                    videos.add(file);
                }
            }
            binding.rec1.setAdapter(new VideoAdapter(videos,context));
            binding.rec1.setLayoutManager(new LinearLayoutManager(context));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setData() {
        ArrayList<ThemeData> themeData= Utils.getThemes(context);
        binding.rec.setAdapter(new ThemeAdapter(themeData,context));
        binding.rec.setLayoutManager(new LinearLayoutManager(context));
    }
}