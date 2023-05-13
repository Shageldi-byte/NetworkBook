package com.android.networkbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.networkbook.databinding.ActivityMainBinding;
import com.android.networkbook.databinding.FragmentHomeBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    private Context context=this;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
    }

    private void setListener() {
        setFragment(Home.newInstance());
        binding.menu.setItemSelected(R.id.home,true);
        binding.menu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.home:
                        setFragment(Home.newInstance());
                        break;
                    case R.id.quiz:
                        setFragment(Quiz.newInstance());
                        break;
                    case R.id.about:
                        setFragment(About.newInstance());
                        break;
                    case R.id.book:
                        setFragment(Books.newInstance());
                        break;

                }
            }
        });
    }

    private void setFragment(Fragment newInstance) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, newInstance,newInstance.getClass().getSimpleName()).commit();
    }
}