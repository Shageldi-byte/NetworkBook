package com.android.networkbook;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.networkbook.databinding.FragmentQuizBinding;

import java.util.ArrayList;


public class Quiz extends Fragment {

    private Context context;
    private FragmentQuizBinding binding;

    public Quiz() {
    }


    public static Quiz newInstance() {
        Quiz fragment = new Quiz();
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
        // Inflate the layout for this fragment
        context = getContext();
        binding=FragmentQuizBinding.inflate(inflater,container,false);
        setData();
        return binding.getRoot();
    }

    private void setData() {
        ArrayList<ThemeData> themeData= Utils.getThemes(context);
        themeData.remove(0);
        ArrayList<ThemeData> temp = new ArrayList<>();
        for(ThemeData theme: themeData){
            if(theme.getTitle().equals("1587201011.pdf")){
                break;
            } else {
                temp.add(theme);
            }
        }
        binding.rec.setAdapter(new ThemeAdapter(temp,context,1));
        binding.rec.setLayoutManager(new LinearLayoutManager(context));
    }
}