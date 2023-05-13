package com.android.networkbook;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.networkbook.databinding.FragmentBooksBinding;

import java.util.ArrayList;


public class Books extends Fragment {

   private FragmentBooksBinding binding;
   private Context context;

    public Books() {
    }


    // TODO: Rename and change types and number of parameters
    public static Books newInstance() {
        Books fragment = new Books();
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
        context = getContext();
        binding = FragmentBooksBinding.inflate(inflater,container,false);
        setData();
        return binding.getRoot();
    }

    private void setData() {
        ArrayList<ThemeData> themeData= Utils.getThemes(context);
        ArrayList<ThemeData> temp=new ArrayList<>();
        for(ThemeData themeData1: themeData){
            if(themeData1.getTitle().endsWith(".pdf")){
                temp.add(themeData1);
            }
        }
        binding.rec.setAdapter(new ThemeAdapter(temp,context));
        binding.rec.setLayoutManager(new LinearLayoutManager(context));
    }
}