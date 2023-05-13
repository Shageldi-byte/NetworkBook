package com.android.networkbook;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.android.networkbook.databinding.ActivityReadBookBinding;

import java.util.ArrayList;

public class ReadBook extends AppCompatActivity {
    private ActivityReadBookBinding binding;
    private Context context=this;
    public static ThemeData themeData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityReadBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setData();
        binding.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData() {
        binding.title.setText(themeData.getTitle());
        ArrayList<Integer> pages=new ArrayList<>();
        for(int i=themeData.getBook_start_position()-1;i<themeData.getBook_end_position();i++){
            pages.add(i);
        }
        binding.pdfView.fromAsset(themeData.getTitle().endsWith(".pdf")?"books/set/"+themeData.getBook_name():""+themeData.getBook_name())
                .pages(Utils.convertIntegers(pages))
                .load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        themeData=null;
    }
}