package com.android.networkbook;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.networkbook.databinding.FragmentAboutBinding;

public class About extends Fragment {

    private Context context;
    private FragmentAboutBinding binding;

    public About() {
    }

    public static About newInstance() {
        About fragment = new About();
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
        binding=FragmentAboutBinding.inflate(inflater,container,false);
        context=getContext();
        return binding.getRoot();
    }
}